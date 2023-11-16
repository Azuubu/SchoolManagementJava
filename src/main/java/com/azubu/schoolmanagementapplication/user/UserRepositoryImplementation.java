package com.azubu.schoolmanagementapplication.user;

import com.azubu.schoolmanagementapplication.exception.ApiRequestException;
import com.azubu.schoolmanagementapplication.roles.RoleRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

import static com.azubu.schoolmanagementapplication.roles.RoleType.ROLE_USER;
import static com.azubu.schoolmanagementapplication.user.queries.UserQueries.*;
import static com.azubu.schoolmanagementapplication.verification.VerificationType.ACCOUNT;


@Repository
public class UserRepositoryImplementation implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;


    public UserRepositoryImplementation(JdbcTemplate jdbcTemplate, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public User createUser(User user) {
        System.out.println(user.getEmail());
        System.out.println(ROLE_USER.getRoleNumber());

        if(checkIfEmailExists(user.getEmail().trim().toLowerCase())) throw new ApiRequestException(
                "The email provided is already used. Please use a different email."
        );

        try {
            SqlParameterSource parameters = getSqlParameterSource(user);
            jdbcTemplate.update(ADD_NEW_USER_QUERY, parameters);

            int userId = roleRepository.getUserId(user.getEmail());
            roleRepository.addUserRole(userId, ROLE_USER.getRoleNumber());

            String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());
            addVerificationUrlToDb(userId, verificationUrl);

            user.setActivated(false);
            user.setLocked(false);
            user.setExpired(false);
            user.setCredentialsExpired(false);

            return user;

        } catch (EmptyResultDataAccessException e) {
            throw new ApiRequestException(
                    "Couldn't add the requested role to the user."
            );
        } catch (Exception e) {
            throw new ApiRequestException(
                    "An unexpected error occurred. Please try again."
            );
        }
    }

    @Override
    public List<User> selectAllUsers() {
        return jdbcTemplate.query(SELECT_ALL_USERS_QUERY, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public User selectUserByEmail(String email) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_EMAIL_QUERY, BeanPropertyRowMapper.newInstance(User.class), email);
    }

    @Override
    public User selectUserById(int id) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_ID_QUERY, BeanPropertyRowMapper.newInstance(User.class), id);
    }

    @Override
    public List<User> selectUsersByRole(String role) {
        return jdbcTemplate.query(SELECT_USERS_BY_ROLE_QUERY, BeanPropertyRowMapper.newInstance(User.class), role);
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(UPDATE_USER_QUERY, user.getFirstName(),
                user.getEmail(), user.getPassword(), user.getId());
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update(DELETE_USER_QUERY, id);
    }

    private Boolean checkIfEmailExists(String email) {
         return jdbcTemplate.queryForObject(CHECK_IF_EMAIL_EXISTS_QUERY, new Object[] { email }, Boolean.class);
    }

//    private void addUser(User user) {
//         jdbcTemplate.update(ADD_NEW_USER_QUERY, user.getFirstName(), user.getLastName(),
//                user.getEmail(), user.getPassword());
//    }


    private String getVerificationUrl(String key, String type) {
        // this must return the FRONTEND URL in the end, but for now it can have the BACKEND URL for "testing" purposes
        // sth like FRONTEND URL + "/user/verify/" + type + "/" + key
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/" + type + "/" + key).toUriString();
    }

    private void addVerificationUrlToDb(int userId, String verificationUrl) {
        jdbcTemplate.update(ADD_ACCOUNT_VERIFICATION_URL_QUERY, userId, verificationUrl);
    }

    private SqlParameterSource getSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("password", encoder.encode(user.getPassword()))
                .addValue("address", user.getAddress())
                .addValue("phoneNumber", user.getPhoneNumber());
    }




}

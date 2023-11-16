package com.azubu.schoolmanagementapplication.roles;

import com.azubu.schoolmanagementapplication.user.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.azubu.schoolmanagementapplication.roles.queries.RoleQueries.*;

@Repository
public class RoleRepositoryImplementation implements RoleRepository {

    private final JdbcTemplate jdbcTemplate;

    public RoleRepositoryImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Integer getUserId(String email) {
        return jdbcTemplate.queryForObject(GET_USER_ID_BY_EMAIL, Integer.class ,email);
    }

    @Override
    public List<UserRoles> getAllUserRoles() {
        return jdbcTemplate.query(GET_ALL_USER_ROLES_QUERY, BeanPropertyRowMapper.newInstance(UserRoles.class));
    }

    @Override
    public void addUserRole(int userId, int roleId) {
        jdbcTemplate.update(ADD_ROLE_TO_NEW_USER_QUERY, userId, roleId);
    }

    @Override
    public UserRoles getUserRole(int userId) {
        return jdbcTemplate.queryForObject(GET_ROLES_OF_USER_QUERY, BeanPropertyRowMapper.newInstance(UserRoles.class), userId);
    }

    @Override
    public List<Roles> getUserRoles(int userId) {
        return jdbcTemplate.query(GET_USER_ROLES_IMPORTANT_QUERY, BeanPropertyRowMapper.newInstance(Roles.class), userId);
    }

}

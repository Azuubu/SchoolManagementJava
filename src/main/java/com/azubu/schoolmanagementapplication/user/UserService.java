package com.azubu.schoolmanagementapplication.user;

import com.azubu.schoolmanagementapplication.roles.RoleRepository;
import com.azubu.schoolmanagementapplication.roles.UserRoles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User registerNewUser(User user) {
        return userRepository.createUser(user);
    }


    public List<User> getAllUsers() {
        return userRepository.selectAllUsers();
    }

    public User getUserById(int id) {
        return userRepository.selectUserById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.selectUserByEmail(email);
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.selectUsersByRole(role);
    }


    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    public List<UserRoles> getAllUserRoles() {
       return roleRepository.getAllUserRoles();
    }


}

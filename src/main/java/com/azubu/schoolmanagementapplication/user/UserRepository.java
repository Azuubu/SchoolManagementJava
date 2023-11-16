package com.azubu.schoolmanagementapplication.user;


import com.azubu.schoolmanagementapplication.roles.Roles;

import java.util.List;

public interface UserRepository {
    // if it doesn't work, add <User> to the UserRepository

    User createUser(User user);

    List<User> selectAllUsers();

    User selectUserById(int id);

    User selectUserByEmail(String email);

    List<User> selectUsersByRole(String role);

    void updateUser(User user);

    void deleteUser(int id);





}

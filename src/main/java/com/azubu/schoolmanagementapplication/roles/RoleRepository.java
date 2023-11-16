package com.azubu.schoolmanagementapplication.roles;

import com.azubu.schoolmanagementapplication.user.User;

import java.util.List;

public interface RoleRepository {

    void addUserRole(int userId, int roleId);

    List<UserRoles> getAllUserRoles();

    Integer getUserId(String email);

    UserRoles getUserRole(int userId);

    List<Roles> getUserRoles(int userId);



}

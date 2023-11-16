package com.azubu.schoolmanagementapplication.roles;

public enum RoleType {
    ROLE_ADMIN("admin",1),
    ROLE_TEACHER("teacher", 2),
    ROLE_STUDENT("student", 3),
    ROLE_USER("guest", 4);

    private final String roleName;

    private final int roleNumber;

    RoleType(String roleName, int roleNumber) {
        this.roleName = roleName;
        this.roleNumber = roleNumber;
    }

    public String getRoleName() {
        return roleName;
    }

    public int getRoleNumber() {
        return roleNumber;
    }
}

package com.azubu.schoolmanagementapplication.roles;

public class Roles {

    private int id;

    private String name;

    private String permissions;

    public Roles() {
    }

    public Roles(int id, String name, String permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}

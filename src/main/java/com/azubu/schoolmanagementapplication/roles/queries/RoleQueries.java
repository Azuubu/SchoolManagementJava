package com.azubu.schoolmanagementapplication.roles.queries;

public class RoleQueries {

    public static final String GET_ALL_USER_ROLES_QUERY = "SELECT id, user_id, role_id FROM userroles";

    public static final String ADD_ROLE_TO_NEW_USER_QUERY = "INSERT INTO userroles (user_id, role_id) VALUES (?, ?)";

    public static final String GET_USER_ID_BY_EMAIL = "SELECT users.id FROM users WHERE users.email = ?";

    public static final String GET_ROLES_OF_USER_QUERY = "SELECT userroles.id, user_id, role_id FROM userroles WHERE user_id = ?";

    public static final String GET_USER_PERMISSIONS_QUERY = "SELECT permissions FROM roles " +
            "INNER JOIN userroles ON roles.id = userroles.role_id WHERE userroles.user_id = ?";


    public static final String GET_USER_ROLES_IMPORTANT_QUERY = "SELECT roles.name FROM roles " +
            "INNER JOIN userroles ON roles.id = userroles.role_id WHERE user_id = ?";

}

package com.azubu.schoolmanagementapplication.user.queries;

public class UserQueries {

    public static final String CHECK_IF_EMAIL_EXISTS_QUERY = "SELECT EXISTS (SELECT 1 FROM users WHERE email = ?)";

    public static final String ADD_NEW_USER_QUERY = "INSERT INTO users (first_name, last_name, email, password, address, phone_number) VALUES(?, ?, ?, ?, ?, ?)";

    public static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id=?";

    public static final String UPDATE_USER_QUERY = "UPDATE users SET first_name=?, email=?, password=? WHERE id=?";

    public static final String SELECT_USER_BY_ID_QUERY = "SELECT id, first_name, email, password FROM users WHERE id = ?";

    public static final String SELECT_USER_BY_EMAIL_QUERY = "SELECT id, first_name, email, password FROM users WHERE email = ?";

    public static final String SELECT_ALL_USERS_QUERY = "SELECT users.id, users.first_name, users.last_name, users.email, users.address FROM users";

    public static final String SELECT_USERS_BY_ROLE_QUERY = "SELECT users.id, users.first_name, users.last_name, users.email, users.address FROM users " +
            "INNER JOIN userroles ON users.id = userroles.user_id " +
            "INNER JOIN roles ON userroles.role_id = roles.id WHERE roles.name = ?";

    public static final String ADD_ACCOUNT_VERIFICATION_URL_QUERY = "INSERT INTO accountverification (user_id, verification_url) VALUES(?, ?)";
    // public static final String GET_TEACHER_CODE_PASS_QUERY = "SELECT secured_pass_code FROM security";


}

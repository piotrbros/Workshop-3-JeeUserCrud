package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_BY_ID_QUERY =
            "SELECT * FROM users WHERE id = ?";
    private static final String READ_USER_BY_EMAIL_QUERY =
            "SELECT * FROM users WHERE email = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_QUERY =
            "SELECT * FROM users";
    private static final String[] USERS_COLUMN =
            {"id", "email", "username", "password"};

    // Create user class
    public User create(User user) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement prepStmt =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            prepStmt.setString(1, user.getUserName());
            prepStmt.setString(2, user.getEmail());
            prepStmt.setString(3, hashPassword(user.getPassword()));
            prepStmt.executeUpdate();
            ResultSet resultSet = prepStmt.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int id) throws SQLException {
        try(Connection conn = DbUtil.getConnection()) {
            PreparedStatement prepStmt =
                    conn.prepareStatement(READ_USER_BY_ID_QUERY);
            prepStmt.setInt(1, id);
            ResultSet resultSet = prepStmt.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                int i = 0;
                user.setId(resultSet.getInt(USERS_COLUMN[i++]));
                user.setEmail(resultSet.getString(USERS_COLUMN[i++]));
                user.setUserName(resultSet.getString(USERS_COLUMN[i++]));
                user.setPassword(resultSet.getString(USERS_COLUMN[i]));
                return user;
            }
            return null;
        }
    }

    public User read(String email) throws SQLException {
        try(Connection conn = DbUtil.getConnection()) {
            PreparedStatement prepStmt =
                    conn.prepareStatement(READ_USER_BY_ID_QUERY);
            prepStmt.setString(1, email);
            ResultSet resultSet = prepStmt.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                int i = 0;
                user.setId(resultSet.getInt(USERS_COLUMN[i++]));
                user.setEmail(resultSet.getString(USERS_COLUMN[i++]));
                user.setUserName(resultSet.getString(USERS_COLUMN[i++]));
                user.setPassword(resultSet.getString(USERS_COLUMN[i]));
                return user;
            }
            return null;
        }
    }

    //TO DO UPDATE -> CHECK DIFF USER VS read(USER)
    public void update(User user) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement prepStmt = conn.prepareStatement(UPDATE_USER_QUERY);
            // Hashing new password
            String updatedPass = hashPassword(user.getPassword());
            prepStmt.setString(1, user.getEmail());
            prepStmt.setString(2, user.getUserName());
            prepStmt.setString(3, updatedPass);
            prepStmt.setInt(4, user.getId());
            prepStmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement prepStmt = conn.prepareStatement(DELETE_USER_QUERY);
            prepStmt.setInt(1, id);
            prepStmt.executeUpdate();
        }
    }

    public User[] findAll() throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement prepStmt = conn.prepareStatement(FIND_ALL_QUERY);
            prepStmt.executeQuery();
            ResultSet resultSet = prepStmt.getResultSet();

            //Building result array
            User[] allUsers = new User[0];
            while (resultSet.next()) {
                User user = new User();
                int i = 0;
                user.setId(resultSet.getInt(USERS_COLUMN[i++]));
                user.setEmail(resultSet.getString(USERS_COLUMN[i++]));
                user.setUserName(resultSet.getString(USERS_COLUMN[i++]));
                user.setPassword(resultSet.getString(USERS_COLUMN[i]));
                allUsers = addUser(user, allUsers);
            }
            return allUsers;
        }
    }

    private User[] addUser(User user, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = user;
        return tmpUsers;
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}

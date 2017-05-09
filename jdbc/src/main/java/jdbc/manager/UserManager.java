package jdbc.manager;

import jdbc.exceptions.GenericDaoException;
import jdbc.model.User;
import jdbc.model.UserRole;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {
    private final static String SELECT_BY_LOGIN = "SELECT U.ID, U.NAME, U.LASTNAME, U.PASSWORD, U.EMAIL, U.USER_ROLE_ID, UR.ROLE FROM USERS U JOIN USER_ROLES UR ON U.USER_ROLE_ID = UR.ID WHERE U.LOGIN = ?";
    private DataSource dataSource;

    public UserManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User getUserByLogin(String login) {
        try (Connection con = dataSource.getConnection()) {
            User user = new User();
            PreparedStatement stmt = con.prepareStatement(SELECT_BY_LOGIN);
            stmt.setString(1, login);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setEmail(resultSet.getString("EMAIL"));
                UserRole userRole = new UserRole();
                userRole.setId(resultSet.getLong("USER_ROLE_ID"));
                userRole.setRole(resultSet.getString("ROLE"));
                user.setUserRole(userRole);
                if (resultSet.next()) {
                    throw new GenericDaoException("More than 1 user was found with unique field login = " + "login");
                }
                return user;
            }
        } catch (SQLException e) {
            throw new GenericDaoException("Error reading user from DB");
        }
        throw new GenericDaoException("Error reading "
                +  " user from DB - no user was found with login = " + login);
    }

}

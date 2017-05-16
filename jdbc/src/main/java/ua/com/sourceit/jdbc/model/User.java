package ua.com.sourceit.jdbc.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends BaseEntity<Long> {
    private String name;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private UserRole userRole;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public void prepareCreateStatement(PreparedStatement stmt) throws SQLException {
       stmt.setString(1, name);
       stmt.setString(2, lastName);
       stmt.setString(3, login);
       stmt.setString(4, password);
       stmt.setString(5, email);
       stmt.setLong(6, userRole.getId());
    }

    @Override
    public void prepareReadStatement(PreparedStatement stmt, Long id) throws SQLException {
        stmt.setLong(1, id);
    }

    @Override
    public void setDataFromResultSet(ResultSet resultSet) throws SQLException {
        name = resultSet.getString(2);
        lastName = resultSet.getString(3);
        login = resultSet.getString(4);
        password = resultSet.getString(5);
        email = resultSet.getString(6);
        userRole = new UserRole(); // !!!!
        userRole.setId(resultSet.getLong(7));
        userRole.setRole(resultSet.getString(8));
    }

    @Override
    public void prepareUpdateStatement(PreparedStatement stmt) throws SQLException {
        stmt.setString(1, name);
        stmt.setString(2, lastName);
        stmt.setString(3, login);
        stmt.setString(4, password);
        stmt.setString(5, email);
        stmt.setLong(6, userRole.getId());
        stmt.setLong(7, id);
    }

    @Override
    public void prepareDeleteStatement(PreparedStatement stmt) throws SQLException {
        stmt.setLong(1, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof User)) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return userRole != null ? userRole.equals(user.userRole) : user.userRole == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}

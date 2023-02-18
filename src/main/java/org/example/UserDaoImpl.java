package org.example;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private static final String GET_BY_NAME_QUERY = "SELECT * FROM USERDATA U WHERE U.NAME = ?;";
    private static final String INSERT_USER_QUERY = "INSERT INTO USERDATA(NAME, AGE) VALUES(?,?);";

    @Override
    public User getByName(String name) {
        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement(GET_BY_NAME_QUERY);
            selectStatement.setString(1, name);
            ResultSet result = selectStatement.executeQuery();
            result.next();

            User user = new User();
            user.setName(result.getString("name"));
            user.setAge(result.getInt("age"));

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(User user) {
        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement insertStatement = connection.prepareStatement(INSERT_USER_QUERY);
            insertStatement.setString(1, user.getName());
            insertStatement.setInt(2, user.getAge());
            insertStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example.dao;

import org.example.model.Director;
import org.example.utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectorDao {

    private static final String INSERT_QUERY = "INSERT INTO DIRECTOR(NAME) VALUES(?);";
    private static final String GET_BY_NAME_QUERY = "SELECT * FROM DIRECTOR WHERE NAME = ?;";
    private static final String UPDATE_QUERY = "UPDATE DIRECTOR SET NAME = ? WHERE ID = ?;";
    private static final String DELETE_QUERY = "DELETE FROM DIRECTOR WHERE ID = ?;";

    /**
     * Сохранение режисёра
     *
     * @param director данные режисёра
     */
    public void create(Director director) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, director.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получение режисёра по имени
     *
     * @param name имя режисёра
     * @return данные режисёра
     */
    public Director getByName(String name) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME_QUERY)) {

            preparedStatement.setString(1, name);
            ResultSet result = preparedStatement.executeQuery();

            Director director = null;

            if (result.next()) {
                director = new Director(result.getInt("id"), result.getString("name"));
            }

            return director;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Обновление данных режисёра по идентификатору
     *
     * @param director новые данные режсёра
     */
    public void update(Director director) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, director.getName());
            preparedStatement.setInt(2, director.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Удаление данных режисёра по идентификатору
     *
     * @param id идентификатор режисёра
     */
    public void delete(int id) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

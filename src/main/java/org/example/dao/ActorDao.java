package org.example.dao;

import org.example.model.Actor;
import org.example.utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActorDao {

    private static final String INSERT_QUERY = "INSERT INTO ACTOR(NAME) VALUES(?);";
    private static final String GET_BY_NAME_QUERY = "SELECT * FROM ACTOR WHERE NAME = ?;";
    private static final String UPDATE_QUERY = "UPDATE ACTOR SET NAME = ? WHERE ID = ?";
    private static final String DELETE_QUERY = "DELETE FROM ACTOR WHERE ID = ?";

    /**
     * Сохранение актёра
     *
     * @param actor данные актёра
     */
    public void create(Actor actor) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, actor.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получение актёра по имени
     *
     * @param name имя актёра
     * @return данные актёра
     */
    public Actor getByName(String name) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME_QUERY)) {

            preparedStatement.setString(1, name);
            ResultSet result = preparedStatement.executeQuery();

            Actor actor = null;

            if (result.next()) {
                actor = new Actor(result.getInt("id"), result.getString("name"));
            }

            return actor;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Обновление данных актёра по идентификатору
     *
     * @param actor новые данные режсёра
     */
    public void update(Actor actor) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, actor.getName());
            preparedStatement.setInt(2, actor.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Удаление данных актёра по идентификатору
     *
     * @param id идентификатор актёра
     */
    public void delete(int id) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

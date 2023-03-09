package org.example.dao;

import org.example.model.Film;
import org.example.utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmDao {

    private static final String INSERT_QUERY = "INSERT INTO FILM(TITLE, DIRECTOR_ID) VALUES (?,?);";
    private static final String GET_BY_NAME_QUERY = "SELECT * FROM FILM WHERE TITLE = ?;";
    private static final String UPDATE_QUERY = "UPDATE FILM SET TITLE = ?, DIRECTOR_ID = ? WHERE ID = ?;";
    private static final String DELETE_QUERY = "DELETE FROM FILM WHERE ID = ?;";

    /**
     * Сохранение фильма
     *
     * @param film данные режисёра
     */
    public void create(Film film) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setObject(2, film.getDirector());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получение фильма по названию
     *
     * @param title название фильма
     * @return данные фильма
     */
    public Film getByTitle(String title) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME_QUERY)) {

            preparedStatement.setString(1, title);
            ResultSet result = preparedStatement.executeQuery();

            Film film = null;

            if (result.next()) {
                film = new Film(result.getInt("id"), result.getString("title"), (Integer) result.getObject("director_id"));
            }

            return film;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Обновление данных фильма
     *
     * @param film данные фильма
     */
    public void update(Film film) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setObject(2, film.getDirector());
            preparedStatement.setInt(3, film.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Удаление данных фильма по идентификатору
     *
     * @param id идентификатр фильма
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

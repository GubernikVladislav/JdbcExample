import org.apache.commons.lang3.RandomStringUtils;
import org.example.dao.DirectorDao;
import org.example.dao.FilmDao;
import org.example.model.Director;
import org.example.model.Film;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit-тесты для Dao-класса фильмов
 */
public class FilmDaoTest {

    private final FilmDao filmDao = new FilmDao();
    private final DirectorDao directorDao = new DirectorDao();

    @Test
    public void createAndGetTest() {
        //Создание режисёра
        String directorName = RandomStringUtils.randomAlphabetic(10);
        Director director = new Director(directorName);
        directorDao.create(director);
        //Получение данных режисёра для получения его идентификатора
        Director createdDirector = directorDao.getByName(directorName);

        String filmTitle = RandomStringUtils.randomAlphabetic(10);
        Film film = new Film(filmTitle, createdDirector.getId());

        //Сохранение фильма и проверка, что не возникает исключение
        Assertions.assertDoesNotThrow(() -> filmDao.create(film));

        //Получение данных фильма
        Film createdFilm = filmDao.getByTitle(filmTitle);

        //Проверка, что фильм найден
        Assertions.assertNotNull(createdFilm);

        //Проверка корректного заполнения всех полей
        Assertions.assertEquals(createdFilm.getTitle(), filmTitle);
        Assertions.assertTrue(createdFilm.getId() > 0);
        Assertions.assertEquals(createdFilm.getDirector(), createdDirector.getId());
    }

    @Test
    public void createWithNullTitleTest() {
        String filmTitle = RandomStringUtils.randomAlphabetic(10);
        Film film = new Film(filmTitle, null);
        Assertions.assertDoesNotThrow(() -> filmDao.create(film));
    }

    /**
     * Проверка сохранения фильма без режисёра. Например на случай, когда режисёр еще не назначен
     */
    @Test
    public void createWithNullDirectorTest() {
        String filmTitle = RandomStringUtils.randomAlphabetic(10);
        Film film = new Film(filmTitle);
        filmDao.create(film);

        Film createdFilm = filmDao.getByTitle(filmTitle);

        //Проверка корректного заполнения всех полей
        Assertions.assertEquals(createdFilm.getTitle(), filmTitle);
        Assertions.assertTrue(createdFilm.getId() > 0);
        Assertions.assertNull(createdFilm.getDirector());
    }

    /**
     * Проверка сохранения фильма с несуществующим режисёром
     */
    @Test
    public void createWithIncorrectDirector() {
        String filmTitle = RandomStringUtils.randomAlphabetic(10);
        Film film = new Film(filmTitle, 0); //Все идентификаторы больше нуля, режисёра с идентификатором 0 не существует

        //Пытаемся сохранить фильм, ожидаем исключение
        Assertions.assertThrows(Exception.class, () -> filmDao.create(film));

        //Проверка, что фильм действительно не попал в базу данных
        Film createdFilm = filmDao.getByTitle(filmTitle);
        Assertions.assertNull(createdFilm);
    }

    @Test
    public void updateTest() {
        String directorName = RandomStringUtils.randomAlphabetic(10);
        Director director = new Director(directorName);
        directorDao.create(director);
        Director createdDirector = directorDao.getByName(directorName);

        String filmTitle = RandomStringUtils.randomAlphabetic(10);
        Film film = new Film(filmTitle, createdDirector.getId());

        filmDao.create(film);

        Film createdFilm = filmDao.getByTitle(filmTitle);

        //Добавление нового режисёра
        String newDirectorName = RandomStringUtils.randomAlphabetic(10);
        Director newDirector = new Director(newDirectorName);
        directorDao.create(newDirector);
        Director newCreatedDirector = directorDao.getByName(newDirectorName);

        createdFilm.setDirector(newCreatedDirector.getId());

        //Смена названия фильма
        String newFilmTitle = RandomStringUtils.randomAlphabetic(10);
        createdFilm.setTitle(newFilmTitle);

        //Обновление данных фильма
        Assertions.assertDoesNotThrow(() -> filmDao.update(createdFilm));

        Film updatedFilm = filmDao.getByTitle(newFilmTitle);

        //Проверка корректного заполнения всех полей
        Assertions.assertEquals(newFilmTitle, updatedFilm.getTitle());
        Assertions.assertEquals(createdFilm.getId(), updatedFilm.getId());
        Assertions.assertEquals(newCreatedDirector.getId(), updatedFilm.getDirector());
    }

    @Test
    public void deleteTest() {
        String filmTitle = RandomStringUtils.randomAlphabetic(10);
        Film film = new Film(filmTitle);
        filmDao.create(film);

        Film createdFilm = filmDao.getByTitle(filmTitle);

        //Проверка, что при удалении не возникает исключение
        Assertions.assertDoesNotThrow(() -> filmDao.delete(createdFilm.getId()));

        //Проврка, что после удаления фильм отсутсвует
        Film filmAfterDelete = filmDao.getByTitle(filmTitle);
        Assertions.assertNull(filmAfterDelete);
    }
}

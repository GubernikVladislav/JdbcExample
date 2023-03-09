import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.example.dao.ActorDao;
import org.example.dao.ActorFilmDao;
import org.example.dao.FilmDao;
import org.example.model.Actor;
import org.example.model.Film;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Unit-тесты для Dao-класса связи актёров с фильмами
 */
public class ActorFilmDaoTest {

    private final ActorFilmDao actorFilmDao = new ActorFilmDao();
    private final ActorDao actorDao = new ActorDao();
    private final FilmDao filmDao = new FilmDao();

    /**
     * Проверка установки связи актёров с фильмом
     */
    @Test
    public void linkTest() {
        //Создание нескольких актеров
        String actorName1 = RandomStringUtils.randomAlphabetic(10);
        Actor actor1 = createActor(actorName1);

        String actorName2 = RandomStringUtils.randomAlphabetic(10);
        Actor actor2 = createActor(actorName2);

        String actorName3 = RandomStringUtils.randomAlphabetic(10);
        Actor actor3 = createActor(actorName3);

        String actorName4 = RandomStringUtils.randomAlphabetic(10);
        Actor actor4 = createActor(actorName4);

        //Создание нескольких фильмов
        String filmTitle1 = RandomStringUtils.randomAlphabetic(10);
        Film film1 = createFilm(filmTitle1);

        String filmTitle2 = RandomStringUtils.randomAlphabetic(10);
        Film film2 = createFilm(filmTitle2);

        //Установка связи актёров с фильмами
        actorFilmDao.linkActorWithFilm(actor1, film1);
        actorFilmDao.linkActorWithFilm(actor2, film1);
        actorFilmDao.linkActorWithFilm(actor3, film1);
        actorFilmDao.linkActorWithFilm(actor4, film1);

        actorFilmDao.linkActorWithFilm(actor1, film2);
        actorFilmDao.linkActorWithFilm(actor4, film2);

        //Проверка получения всех актёров одного фильма
        List<Actor> film1Actors = actorFilmDao.getAllActorsByFilm(film1);

        //Проверка что в списке 4 актёрв
        Assertions.assertEquals(film1Actors.size(), 4);

        //Проверка получения всех фильмов одного актёра
        List<Film> actor1Films = actorFilmDao.getAllFilmsByActor(actor1);

        //Проверка что в списке 2 фильма
        Assertions.assertEquals(actor1Films.size(), 2);
    }

    /**
     * Проверка связать существующего актёра с несуществующим фильмом
     */
    @Test
    public void incorrectFilmLinkTest() {
        //Создание актёра
        String actorName = RandomStringUtils.randomAlphabetic(10);
        Actor actor = createActor(actorName);

        //Создание случайного фильма без записи в базу данных
        Film film = new Film(RandomUtils.nextInt(), RandomStringUtils.randomAlphabetic(10), RandomUtils.nextInt());

        //Одидаем исключение при попытке связать актёра с несуществующим фильмом
        Assertions.assertThrows(Exception.class, () -> actorFilmDao.linkActorWithFilm(actor, film));
    }

    /**
     * Проверка связать существующий фильм с несуществующим актёром
     */
    @Test
    public void incorrectActorLinkTest() {
        //Создание фильма
        String filmTitle = RandomStringUtils.randomAlphabetic(10);
        Film film = createFilm(filmTitle);

        //Создание случайного актёра без записи в базу данных
        Actor actor = new Actor(RandomUtils.nextInt(), RandomStringUtils.randomAlphabetic(10));

        //Одидаем исключение при попытке связать фильм с несуществующим актёром
        Assertions.assertThrows(Exception.class, () -> actorFilmDao.linkActorWithFilm(actor, film));
    }

    /**
     * Проверка удаления фильма, при наличии связи с актёром и наоборот
     */
    @Test
    public void deleteFilmOrActorWithLink() {
        //Создание фильма
        String filmTitle = RandomStringUtils.randomAlphabetic(10);
        Film film = createFilm(filmTitle);

        //Создание актёра
        String actorName = RandomStringUtils.randomAlphabetic(10);
        Actor actor = createActor(actorName);

        //Создание связи
        actorFilmDao.linkActorWithFilm(actor, film);

        //Попытка удалить фильм. Ожидается исключение, т.к нельзя удалить фильм, имеющий привязанных актёров
        Assertions.assertThrows(Exception.class,() -> filmDao.delete(film.getId()));

        //Попытка удалить актёра
        Assertions.assertThrows(Exception.class,() -> actorDao.delete(actor.getId()));
    }

    /**
     * Проверка удаления связи актёра и фильма
     */
    @Test
    public void deleteLinkTest(){

        //Создание фильма
        String filmTitle = RandomStringUtils.randomAlphabetic(10);
        Film film = createFilm(filmTitle);

        //Создание актёра
        String actorName = RandomStringUtils.randomAlphabetic(10);
        Actor actor = createActor(actorName);

        //Создание связи
        actorFilmDao.linkActorWithFilm(actor, film);

        //Проверка записи связи
        List<Actor> filmActors = actorFilmDao.getAllActorsByFilm(film);
        Assertions.assertEquals(filmActors.size(), 1);

        //Удаление связи
        actorFilmDao.deleteLink(actor, film);

        //Проверка удаления связи
        List<Actor> filmActorsAfterDelete = actorFilmDao.getAllActorsByFilm(film);
        Assertions.assertEquals(filmActorsAfterDelete.size(), 0);

        //Попытка удаления актёра и фильма после удаления связи
        Assertions.assertDoesNotThrow(() -> filmDao.delete(film.getId()));
        Assertions.assertDoesNotThrow(() -> actorDao.delete(actor.getId()));
    }

    private Actor createActor(String actorName) {
        Actor actor = new Actor(actorName);
        actorDao.create(actor);
        return actorDao.getByName(actorName);
    }

    private Film createFilm(String filmTitle) {
        Film film = new Film(filmTitle);
        filmDao.create(film);
        return filmDao.getByTitle(filmTitle);
    }
}

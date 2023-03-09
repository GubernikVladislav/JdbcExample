import org.apache.commons.lang3.RandomStringUtils;
import org.example.dao.DirectorDao;
import org.example.model.Director;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit-тесты для Dao-класса режисера
 */
public class DirectorDaoTest {

    private final DirectorDao directorDao = new DirectorDao();

    /**
     * Проверка записи/получения данных режисёра
     */
    @Test
    public void createAndGetTest() {

        //используется отдельная библиотека для генерации случайной строки https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
        String directorName = RandomStringUtils.randomAlphabetic(10);

        Director director = new Director(directorName);

        //Проверка, что при сохранении не возникает исключения
        Assertions.assertDoesNotThrow(() -> directorDao.create(director));

        Director createdDirector = directorDao.getByName(directorName);
        //Проверка, что режисёр записался в базу данных
        Assertions.assertNotNull(createdDirector);

        //Проверка совпадения имени
        Assertions.assertEquals(createdDirector.getName(), directorName);

        //Проверка наличия идентификатора
        Assertions.assertTrue(createdDirector.getId() > 0);
    }

    /**
     * Проверка сохранения режисёра без имени.
     * Ожидается ошибка, т.к при создании таблицы в базе данных, для колонки NAME указан модификатор NOT NULL
     */
    @Test
    public void createWithNullNameTest() {

        //Пустое имя режисёра
        Director director = new Director(null);

        //Проверка, что при передачи пустого имени плучаем исключение
        Assertions.assertThrows(Exception.class, () -> directorDao.create(director));
    }

    /**
     * Проверка корректности авто-генерации идентификатора.
     * Ожидается, что идентификатор каждой следующей записи в БД больше, чем идентификатор предыдущей
     */
    @Test
    public void autoincrementIdTest() {
        String firstDirectorName = RandomStringUtils.randomAlphabetic(10);
        Director firstDirector = new Director(firstDirectorName);

        String secondDirectorName = RandomStringUtils.randomAlphabetic(10);
        Director secondDirector = new Director(secondDirectorName);

        directorDao.create(firstDirector);
        directorDao.create(secondDirector);

        Director firstCreatedDirector = directorDao.getByName(firstDirectorName);
        Director secondCreatedDirector = directorDao.getByName(secondDirectorName);

        //Проверка, что идентификатор второго режисёра больше, чем идентификатор первого
        Assertions.assertTrue(firstCreatedDirector.getId() < secondCreatedDirector.getId());
    }

    /**
     * Проверка возможности SQL-инъекции.
     * Ожидается, что переданное SQL-выражение не будет выполнено, а просто запишется в поле имени режисёра
     */
    @Test
    public void sqlInjectionTest() {
        String injection = "'); drop table director; --";
        Director director = new Director(injection);

        Assertions.assertDoesNotThrow(() -> directorDao.create(director));

        //Проверка, что таблица осталась в БД и инъекция не прошла. Просто выполняется запрос без исключения
        Assertions.assertDoesNotThrow(() -> directorDao.getByName(injection));
    }

    /**
     * Проверка обновления данных режисёра
     */
    @Test
    public void updateTest() {
        String directorName = RandomStringUtils.randomAlphabetic(10);
        Director director = new Director(directorName);

        //Добавление данных
        directorDao.create(director);

        //Получение данных для того, чтобы узнать с каким идентификаторм был добавлен режисёр
        Director createdDirector = directorDao.getByName(directorName);

        //Замена имени режисёра
        String newDirectorName = RandomStringUtils.randomAlphabetic(10);
        createdDirector.setName(newDirectorName);

        //Обновление данных и проверка, что не возникает исключение
        Assertions.assertDoesNotThrow(() -> directorDao.update(createdDirector));

        //Получение данных режисёра по новому имени
        Director updatedDirector = directorDao.getByName(newDirectorName);

        //Проверка, что режисёр нашелся по новому имени
        Assertions.assertNotNull(updatedDirector);
    }

    /**
     * Проверка удаления данных режисёра
     */
    @Test
    public void deleteTest() {
        String directorName = RandomStringUtils.randomAlphabetic(10);
        Director director = new Director(directorName);

        //Добавление данных
        directorDao.create(director);
        //Получение данных для того, чтобы узнать с каким идентификаторм был добавлен режисёр
        Director createdDirector = directorDao.getByName(directorName);

        //Проверка, что при удалении данных не возникает исключение
        Assertions.assertDoesNotThrow(() -> directorDao.delete(createdDirector.getId()));

        //Попытка получить данные после удаления
        Director createdDirectorAfterDelete = directorDao.getByName(directorName);

        //Проверка, что данных нет
        Assertions.assertNull(createdDirectorAfterDelete);
    }
}

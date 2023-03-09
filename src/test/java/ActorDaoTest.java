import org.apache.commons.lang3.RandomStringUtils;
import org.example.dao.ActorDao;
import org.example.model.Actor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit-тесты для Dao-класса актёра
 */
public class ActorDaoTest {

    private final ActorDao actorDao = new ActorDao();

    @Test
    public void createAndGetTest() {
        String actorName = RandomStringUtils.randomAlphabetic(10);

        Actor actor = new Actor(actorName);

        Assertions.assertDoesNotThrow(() -> actorDao.create(actor));

        Actor createdActor = actorDao.getByName(actorName);

        Assertions.assertNotNull(createdActor);
        Assertions.assertEquals(createdActor.getName(), actorName);
        Assertions.assertTrue(createdActor.getId() > 0);
    }

    @Test
    public void createWithNullNameTest() {
        Actor actor = new Actor(null);
        Assertions.assertThrows(Exception.class, () -> actorDao.create(actor));
    }

    @Test
    public void updateTest() {
        String actorName = RandomStringUtils.randomAlphabetic(10);
        Actor actor = new Actor(actorName);

        actorDao.create(actor);

        Actor createdActor = actorDao.getByName(actorName);

        String newActorName = RandomStringUtils.randomAlphabetic(10);
        createdActor.setName(newActorName);

        Assertions.assertDoesNotThrow(() -> actorDao.update(createdActor));

        Actor updatedActor = actorDao.getByName(newActorName);
        Assertions.assertNotNull(updatedActor);
    }

    @Test
    public void deleteTest() {
        String actorName = RandomStringUtils.randomAlphabetic(10);
        Actor actor = new Actor(actorName);

        actorDao.create(actor);
        Actor createdActor = actorDao.getByName(actorName);

        Assertions.assertDoesNotThrow(() -> actorDao.delete(createdActor.getId()));

        Actor createdActorAfterDelete = actorDao.getByName(actorName);
        Assertions.assertNull(createdActorAfterDelete);
    }
}

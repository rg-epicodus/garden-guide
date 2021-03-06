import dao.Sql2oEventDao;
import models.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Sql2oEventDaoTest {

    private Sql2oEventDao eventDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        //String connectionString = ("jdbc:postgresql://localhost:5432/garden_guide_test");
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        eventDao = new Sql2oEventDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
        //try(Connection con = sql2o.open()) {
        //String sql = "DELETE FROM plants *;";
        //con.createQuery(sql).executeUpdate();
        //}
    }

    //helper
    public Event getTestEvent() {
        String startDate = "2017-05-15";
        String endDate = "2017-05-31";
        String type = "harvest";
        int plantId = 1;
        return new Event(startDate,endDate,type,plantId);
    }

    @Test
    public void addingEventSetsId() throws Exception {
        Event event = getTestEvent();
        eventDao.add(event);
        assertEquals(1, event.getId());
    }


    @Test
    public void getAllByPlantId_returnsCorrectly() throws Exception {
        Event event = getTestEvent();
        Event anotherEvent = getTestEvent();
        eventDao.add(event);
        eventDao.add(anotherEvent);
         List<Event> events  = eventDao.getAllByPlantId(1);
        int number = events.size();
        assertEquals(2, number);
    }


}

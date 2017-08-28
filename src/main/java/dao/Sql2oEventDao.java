package dao;

import models.Event;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

/**
 * Created by Guest on 8/28/17.
 */
public class Sql2oEventDao implements EventDao{
    private final Sql2o sql2o;

    public Sql2oEventDao(Sql2o sql2o)  {
        this.sql2o = sql2o;
    }

    @Override
    public List<Event> getAllByPlantId(int plantId) {
        String sql = "SELECT * FROM events WHERE plantId = :plantId";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Event.class);
        }
    }

}

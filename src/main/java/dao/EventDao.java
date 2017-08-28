package dao;

import models.Event;
import models.Plant;

import java.util.List;

/**
 * Created by Guest on 8/28/17.
 */
public interface EventDao {


    //Read
    List<Event> getAllByPlantId(int plantId);
    //List<Event> getAllEventsByTypeAndPlantId();

}

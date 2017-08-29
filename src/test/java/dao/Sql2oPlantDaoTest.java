package dao;

import models.DB;
import models.Plant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oPlantDaoTest {
  private Sql2oPlantDao plantDao;

  @Before
  public void setUp() throws Exception {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/garden_guide_test", null, null);
  }

  @After
  public void tearDown() throws Exception {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM plants *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  public Plant getTestPlant() {
    String plantName = "tomato";
    int daysToMaturity = 4;
    String plantSpacing = "1foot";
    String rowSpacing = "2foot";
    return new Plant(plantName,daysToMaturity,plantSpacing,rowSpacing);
  }

  @Test
  public void existingPlantsCanBeFoundById() throws Exception {
    Plant plant = getTestPlant();
    plantDao.add(plant);
    Plant foundPlant = plantDao.findById(plant.getId());
    assertEquals(plant, foundPlant);
  }

  @Test
  public void getAll_allPlantsAreFound() throws Exception {
    Plant plant = getTestPlant();
    Plant anotherPlant = getTestPlant();
    plantDao.add(plant);
    plantDao.add(anotherPlant);
    int number = plantDao.getAll().size();
    assertEquals(2, number);
  }

  @Test
  public void getAll_noPlantsAreFound() throws Exception {
    int number = plantDao.getAll().size();
    assertEquals(0, number);
  }


}
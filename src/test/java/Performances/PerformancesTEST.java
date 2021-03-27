package Performances;

import DAO.Performances.PerformancesDAO;
import DAO.Performances.PerformancesDAOImpl;
import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import entity.Performances.Performances;
import entity.Theaters.Theaters;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public class PerformancesTEST {
    @Test
    public void getAll() throws SQLException{
        boolean resultId1 = false;
        boolean resultId2 = false;
        PerformancesDAO objectDAO = new PerformancesDAOImpl();
        Collection<Performances> objects = objectDAO.getAll();
        for (Performances object : objects) {
            if (object.getPerformance_id() == 1) {
                if (!resultId1)
                    resultId1 = true;
                else {
                    resultId1 = false;
                    break;
                }
            } else if (object.getPerformance_id() == 2) {
                if (!resultId2)
                    resultId2 = true;
                else {
                    resultId2 = false;
                    break;
                }
            } else {
                resultId1 = false;
                break;
            }
        }
        Assert.assertTrue(resultId1);
        Assert.assertTrue(resultId2);
    }

    @Test(dependsOnMethods={"getAll"})
    public void getById() throws SQLException{
        PerformancesDAO objectDAO = new PerformancesDAOImpl();
        Performances object = objectDAO.getObjectById((long) 1);
        Assert.assertTrue(object != null && object.getPerformance_id() == 1);
    }

    @Test(dependsOnMethods={"getAll"})
    public void add() throws SQLException{
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theaters = theatersDAO.getObjectById((long)1);
        Performances object1 = new Performances("name", LocalTime.of(1,30,0),
                LocalDate.of(2021,3,1), LocalDate.of(2021,4,1),
                "description", "poster", theaters);
        PerformancesDAO objectDAO = new PerformancesDAOImpl();
        objectDAO.save(object1);
        Performances object2= objectDAO.getObjectById(object1.getPerformance_id());
        Assert.assertTrue(object1.equals(object2));
    }

    @Test(dependsOnMethods={"getAll"})
    public void delete() throws SQLException{
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theaters = theatersDAO.getObjectById((long)1);
        Performances object1 = new Performances("name", LocalTime.of(1,30,0),
                LocalDate.of(2021,3,1), LocalDate.of(2021,4,1),
                "description", "poster", theaters);
        PerformancesDAO objectDAO = new PerformancesDAOImpl();
        objectDAO.save(object1);
        long id = object1.getPerformance_id();
        objectDAO.delete(object1);
        Performances object2 = objectDAO.getObjectById(id);
        Assert.assertTrue(object2 == null);
    }

    @Test(dependsOnMethods={"getAll"})
    public void update() throws SQLException{
        PerformancesDAO objectDAO = new PerformancesDAOImpl();
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theaters = theatersDAO.getObjectById((long)1);
        Performances object = new Performances("name", LocalTime.of(1,30,0),
                LocalDate.of(2021,3,1), LocalDate.of(2021,4,1),
                "description", "poster", theaters);
        objectDAO.save(object);
        long id = object.getPerformance_id();
        object.setName("newName");
        objectDAO.update(object);
        Assert.assertTrue(objectDAO.getObjectById(id).getName().equals("newName"));
    }
}

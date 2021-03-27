package Performances;

import DAO.Performances.PeopleDAO;
import DAO.Performances.PeopleDAOImpl;
import DAO.Performances.PerformancesDAO;
import DAO.Performances.PerformancesDAOImpl;
import entity.Performances.People;
import entity.Performances.Performances;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public class PeopleTEST {
    @Test
    public void getAll() throws SQLException {
        boolean[] result = {false,false,false,false,false,false,false};
        PeopleDAO objectDAO = new PeopleDAOImpl();
        Collection<People> objects = objectDAO.getAll();
        for (People object : objects) {
            if (object.getPeople_id() > 7 || object.getPeople_id()<=0) {
                result[0] = false;
                break;
            }
            else if (!result[(int)object.getPeople_id()-1]) {
                result[(int)object.getPeople_id()-1] = true;
            }
            else {
                result[0] = false;
                break;
            }
        }
        for (int i=0;i<7;i++)
            Assert.assertTrue(result[i]);
    }

    @Test(dependsOnMethods={"getAll"})
    public void getById() throws SQLException{
        PeopleDAO objectDAO = new PeopleDAOImpl();
        People object = objectDAO.getObjectById((long) 1);
        Assert.assertTrue(object != null && object.getPeople_id() == 1);
    }

    @Test(dependsOnMethods={"getAll"})
    public void add() throws SQLException{
        People object1 = new People("name","description","photo");
        PeopleDAO objectDAO = new PeopleDAOImpl();
        objectDAO.save(object1);
        People object2= objectDAO.getObjectById(object1.getPeople_id());
        Assert.assertTrue(object1.equals(object2));
    }

    @Test(dependsOnMethods={"getAll"})
    public void delete() throws SQLException{
        People object1 = new People("name","description","photo");
        PeopleDAO objectDAO = new PeopleDAOImpl();
        objectDAO.save(object1);
        long id = object1.getPeople_id();
        objectDAO.delete(object1);
        People object2 = objectDAO.getObjectById(id);
        Assert.assertTrue(object2 == null);
    }

    @Test(dependsOnMethods={"getAll"})
    public void update() throws SQLException{
        PeopleDAO objectDAO = new PeopleDAOImpl();
        People object = new People("name","description","photo");
        objectDAO.save(object);
        long id = object.getPeople_id();
        object.setName("newName");
        objectDAO.update(object);
        Assert.assertTrue(objectDAO.getObjectById(id).getName().equals("newName"));
    }


}

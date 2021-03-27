package Performances;

import DAO.Performances.*;
import entity.Performances.People;
import entity.Performances.Perf_persons;
import entity.Performances.Performances;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Collection;

public class Perf_personsTEST {
    @Test
    public void getAll() throws SQLException {
        boolean[] result = {false,false,false,false,false,false,false};
        Perf_personsDAO objectDAO = new Perf_personsDAOImpl();
        Collection<Perf_persons> objects = objectDAO.getAll();
        for (Perf_persons object : objects) {
            if (object.getPerson_id() > 7 || object.getPerson_id()<=0) {
                result[0] = false;
                break;
            }
            else if (!result[(int)object.getPerson_id()-1]) {
                result[(int)object.getPerson_id()-1] = true;
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
        Perf_personsDAO objectDAO = new Perf_personsDAOImpl();
        Perf_persons object = objectDAO.getObjectById((long) 1);
        Assert.assertNotNull(object);
        Assert.assertTrue(object.getPerson_id() == 1);
    }

    @Test(dependsOnMethods={"getAll"})
    public void add() throws SQLException{
        PeopleDAO peopleDAO = new PeopleDAOImpl();
        People people = peopleDAO.getObjectById((long)1);

        PerformancesDAO performancesDAO = new PerformancesDAOImpl();
        Performances performances = performancesDAO.getObjectById((long)1);

        Perf_persons object1 = new Perf_persons("role", performances, people);
        Perf_personsDAO objectDAO = new Perf_personsDAOImpl();
        objectDAO.save(object1);
        Perf_persons object2= objectDAO.getObjectById(object1.getPerson_id());
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


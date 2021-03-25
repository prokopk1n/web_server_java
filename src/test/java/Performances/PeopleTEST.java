package Performances;

import DAO.Performances.PeopleDAO;
import DAO.Performances.PeopleDAOImpl;
import DAO.Performances.PerformancesDAO;
import DAO.Performances.PerformancesDAOImpl;
import entity.Performances.People;
import entity.Performances.Performances;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public class PeopleTEST {
    @Test
    public void getAll() {
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
        boolean res=true;
        for (int i=0;i<7;i++)
            res = res && result[i];
        Assert.assertTrue(res);
    }

    @Test
    public void getById() {
        PeopleDAO objectDAO = new PeopleDAOImpl();
        People object = objectDAO.getObjectById((long) 1);
        Assert.assertTrue(object != null && object.getPeople_id() == 1);
    }

    @Test
    public void add() {
        People object1 = new People("name","description","photo");
        PeopleDAO objectDAO = new PeopleDAOImpl();
        objectDAO.save(object1);
        People object2= objectDAO.getObjectById(object1.getPeople_id());
        Assert.assertTrue(object1.myEquals(object2));
    }

    @Test
    public void delete() {
        People object1 = new People("name","description","photo");
        PeopleDAO objectDAO = new PeopleDAOImpl();
        objectDAO.save(object1);
        long id = object1.getPeople_id();
        objectDAO.delete(object1);
        People object2 = objectDAO.getObjectById(id);
        Assert.assertTrue(object2 == null);
    }

    @Test
    public void update() {
        PeopleDAO objectDAO = new PeopleDAOImpl();
        People object = new People("name","description","photo");
        objectDAO.save(object);
        long id = object.getPeople_id();
        object.setName("newName");
        objectDAO.update(object);
        Assert.assertTrue(objectDAO.getObjectById(id).getName().equals("newName"));
    }


}

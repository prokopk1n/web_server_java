package Performances;

import DAO.Performances.*;
import entity.Performances.People;
import entity.Performances.Perf_persons;
import entity.Performances.Performances;
import org.testng.Assert;
import org.testng.annotations.Test;
import sun.misc.Perf;

import java.util.Collection;

public class Perf_personsTEST {
    @Test
    public void getAll() {
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
        boolean res=true;
        for (int i=0;i<7;i++)
            res = res && result[i];
        Assert.assertTrue(res);
    }

    @Test
    public void getById() {
        Perf_personsDAO objectDAO = new Perf_personsDAOImpl();
        Perf_persons object = objectDAO.getObjectById((long) 1);
        Assert.assertTrue(object != null && object.getPerson_id() == 1);
    }

    @Test
    public void add() {
        PeopleDAO peopleDAO = new PeopleDAOImpl();
        People people = peopleDAO.getObjectById((long)1);

        PerformancesDAO performancesDAO = new PerformancesDAOImpl();
        Performances performances = performancesDAO.getObjectById((long)1);

        Perf_persons object1 = new Perf_persons("role", performances, people);
        Perf_personsDAO objectDAO = new Perf_personsDAOImpl();
        objectDAO.save(object1);
        Perf_persons object2= objectDAO.getObjectById(object1.getPerson_id());
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


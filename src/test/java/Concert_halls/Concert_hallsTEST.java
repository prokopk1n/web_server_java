package Concert_halls;

import DAO.Concert_halls.Concert_hallsDAO;
import DAO.Concert_halls.Concert_hallsDAOImpl;
import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import entity.Concert_halls.Concert_halls;
import entity.Theaters.Theaters;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Collection;

public class Concert_hallsTEST {
    @Test
    public void getAll() throws SQLException {
        boolean[] result = {false,false};
        Concert_hallsDAO objectDAO = new Concert_hallsDAOImpl();
        Collection<Concert_halls> objects = objectDAO.getAll();
        for (Concert_halls object : objects) {
            if (object.getHall_id()> 2 || object.getHall_id()<=0) {
                result[0] = false;
                break;
            }
            else if (!result[(int)object.getHall_id()-1]) {
                result[(int)object.getHall_id()-1] = true;
            }
            else {
                result[0] = false;
                break;
            }
        }
        boolean res=true;
        for (int i=0;i<2;i++)
            Assert.assertTrue(result[i]);
    }

    @Test(dependsOnMethods={"getAll"})
    public void getById() throws SQLException{
        Concert_hallsDAO objectDAO = new Concert_hallsDAOImpl();
        Concert_halls object = objectDAO.getObjectById((long) 1);
        Assert.assertTrue(object != null && object.getHall_id() == 1);
    }

    @Test(dependsOnMethods={"getAll"})
    public void add() throws SQLException{
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theaters = theatersDAO.getObjectById((long)1);

        Concert_halls object1 = new Concert_halls("name","scheme",theaters);
        Concert_hallsDAO objectDAO = new Concert_hallsDAOImpl();
        objectDAO.save(object1);
        Concert_halls object2 = objectDAO.getObjectById(object1.getHall_id());
        Assert.assertTrue(object1.equals(object2));
    }

    @Test(dependsOnMethods={"getAll"})
    public void delete() throws SQLException{
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theaters = theatersDAO.getObjectById((long)1);

        Concert_halls object1 = new Concert_halls("name","scheme",theaters);
        Concert_hallsDAO objectDAO = new Concert_hallsDAOImpl();

        objectDAO.save(object1);
        long id = object1.getHall_id();
        objectDAO.delete(object1);
        Concert_halls object2 = objectDAO.getObjectById(id);
        Assert.assertTrue(object2 == null);
    }

    @Test(dependsOnMethods={"getAll"})
    public void update() throws SQLException{
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theaters = theatersDAO.getObjectById((long)1);

        Concert_halls object = new Concert_halls("name","scheme",theaters);
        Concert_hallsDAO objectDAO = new Concert_hallsDAOImpl();
        objectDAO.save(object);
        long id = object.getHall_id();
        object.setName("newName");
        objectDAO.update(object);
        Assert.assertTrue(objectDAO.getObjectById(id).getName().equals("newName"));
    }
}

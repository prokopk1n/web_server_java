package Theaters;

import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import entity.Theaters.Theaters;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Collection;

public class TheatersTest {
    @Test
    public void getAll() throws SQLException {
        boolean resultId1 = false;
        boolean resultId2 = false;
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Collection<Theaters> theaters = theatersDAO.getAll();
        for (Theaters object : theaters) {
            if (object.getTheater_id() == 1) {
                if (!resultId1)
                    resultId1 = true;
                else {
                    resultId1 = false;
                    break;
                }
            } else if (object.getTheater_id() == 2) {
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
    public void getTheaterById() throws SQLException{
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theaters = theatersDAO.getObjectById((long) 1);
        Assert.assertNotNull(theaters);
        Assert.assertTrue(theaters.getTheater_id() == 1);
    }

    @Test(dependsOnMethods={"getAll"})
    public void addTheater() throws SQLException{
        Theaters theater1 = new Theaters("name", "email", "address", "phone", "photo", "map");
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        theatersDAO.save(theater1);
        Theaters theater2 = theatersDAO.getObjectById(theater1.getTheater_id());
        Assert.assertTrue(theater1.equals(theater2));
    }

    @Test(dependsOnMethods={"getAll"})
    public void deleteTheater() throws SQLException{
        Theaters theater1 = new Theaters("name", "email", "address", "phone", "photo", "map");
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        theatersDAO.save(theater1);
        long id = theater1.getTheater_id();
        theatersDAO.delete(theater1);
        Theaters theater2 = theatersDAO.getObjectById(id);
        Assert.assertTrue(theater2 == null);
    }

    @Test(dependsOnMethods={"getAll"})
    public void updateTheater() throws SQLException{
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theaters = new Theaters("name", "email", "address", "phone", "photo", "map");
        theatersDAO.save(theaters);
        long id = theaters.getTheater_id();
        theaters.setName("newName");
        theatersDAO.update(theaters);
        Assert.assertTrue(theatersDAO.getObjectById(id).getName().equals("newName"));
    }

    @Test(dependsOnMethods={"getAll"})
    public void getTheaterByName() throws SQLException{
        Theaters theater1 = new Theaters("testingName", "email", "address", "phone", "photo", "map");
        Theaters theater2 = new Theaters("testingName", "email", "address", "phone", "photo", "map");
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        theatersDAO.save(theater1);
        theatersDAO.save(theater2);
        Collection<Theaters> theaters = theatersDAO.getTheaterByName("testingName");
        boolean result1 = false;
        boolean result2 = false;
        if (theaters.size()!=2) {
            System.out.println(theaters.size());
            Assert.assertTrue(false);
            return;
        }
        for (Theaters object: theaters)
        {
            System.out.println(object);
            if (object.getTheater_id()==theater1.getTheater_id())
            {
                if (!result1)
                    result1 = true;
                else
                {
                    result1 = false;
                    break;
                }
            }
            else if(object.getTheater_id()==theater2.getTheater_id())
            {
                if (!result2)
                    result2 = true;
                else
                {
                    result2 = false;
                    break;
                }
            }
            else
            {
                result1 = false;
                break;
            }
        }
        Assert.assertTrue(result1 && result2);
    }

}

package TheatersTEST;

import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import entity.Theaters.Theaters;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TheatersTest {
    @Test
    public void addTheater(){
        Theaters theater1 = new Theaters("name","email","address","phone","photo","map");
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        theatersDAO.save(theater1);
        Theaters theater2 = theatersDAO.getObjectById(theater1.getTheater_id());
        Assert.assertTrue(theater1.equals(theater2));
    }
}

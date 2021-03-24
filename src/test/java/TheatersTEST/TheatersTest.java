package TheatersTEST;

import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import entity.Theaters.Theaters;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collection;

public class TheatersTest {
    @Test
    public void getAll(){
        boolean resultId1 = false;
        boolean resultId2 = false;
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Collection<Theaters> theaters = theatersDAO.getAll();
        for (Theaters object: theaters)
        {
            System.out.println(object.toString());
            if (object.getTheater_id()==1){
                if (!resultId1)
                    resultId1 = true;
                else {
                    resultId1 = false;
                    break;
                }
            }
            else if (object.getTheater_id()==2){
                if (!resultId2)
                    resultId2 = true;
                else {
                    resultId2 = false;
                    break;
                }
            }
            else {
                resultId1 = false;
                break;
            }
        }
        Assert.assertTrue(resultId1 && resultId2);
    }

    @Test
    public void getTheaterById(){
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theaters = theatersDAO.getObjectById((long)1);
        Assert.assertTrue(theaters!=null && theaters.getTheater_id() == 1);
    }

    @Test
    public void addTheater(){
        Theaters theater1 = new Theaters("name","email","address","phone","photo","map");
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        theatersDAO.save(theater1);
        Theaters theater2 = theatersDAO.getObjectById(theater1.getTheater_id());
        Assert.assertTrue(theater1.equals(theater2));
    }

    @Test
    public void deleteTheater(){
        Theaters theater1 = new Theaters("name","email","address","phone","photo","map");
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        theatersDAO.save(theater1);
        long id = theater1.getTheater_id();
        theatersDAO.delete(theater1);
        Theaters theater2 = theatersDAO.getObjectById(id);
        Assert.assertTrue(theater2==null);
    }

}

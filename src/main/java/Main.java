import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import entity.Theaters.Theaters;
import org.testng.Assert;

public class Main {
    public static void main(String[] args){
        Theaters theater1 = new Theaters("name","email","address","phone","photo","map");
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        theatersDAO.save(theater1);
    }
}

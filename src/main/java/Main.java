import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import entity.Theaters.Theaters;
import org.testng.Assert;

public class Main {
    public static void main(String[] args){
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theater1 = theatersDAO.getObjectById((long)3);
        theatersDAO.delete(theater1);
    }
}

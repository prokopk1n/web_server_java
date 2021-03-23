import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import entity.Theaters.Theaters;

public class Main {
    public static void main(String[] args){
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters object = theatersDAO.getObjectById((long)1);
        System.out.println(object.getName());
    }
}

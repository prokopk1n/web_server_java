import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import entity.Theaters.Theaters;

public class Main {
    public static void main(String[] args){
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters object = theatersDAO.getObjectById((long)1);
        Theaters newObject = new Theaters();
        newObject.setAddress("Address");
        newObject.setMap("Map");
        newObject.setEmail("Email");
        newObject.setPhone("Phone");
        newObject.setPhoto("Photo");
        newObject.setName("Name");
        theatersDAO.save(newObject);

        System.out.println(object.getName());
        System.out.println(object.getPerfomances().get(0).getDescription());
    }
}

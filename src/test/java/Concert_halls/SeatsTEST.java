package Concert_halls;

import DAO.Concert_halls.*;
import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import entity.Concert_halls.Concert_halls;
import entity.Concert_halls.Seats;
import entity.Concert_halls.Type_of_seats;
import entity.Theaters.Theaters;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Collection;

public class SeatsTEST {

    @Test
    public void getAll() throws SQLException {
        boolean[] result = new boolean[36];
        for (int i = 0; i < 36; i++)
            result[i] = false;
        SeatsDAO objectDAO = new SeatsDAOImpl();
        Collection<Seats> objects = objectDAO.getAll();
        for (Seats object : objects) {
            if (object.getSeat_id()> 36 || object.getSeat_id()<=0) {
                result[0] = false;
                break;
            }
            else if (!result[(int)object.getSeat_id()-1]) {
                result[(int)object.getSeat_id()-1] = true;
            }
            else {
                result[0] = false;
                break;
            }
        }
        boolean res=true;
        for (int i=0;i<36;i++)
            Assert.assertTrue(result[i]);
    }

    @Test(dependsOnMethods={"getAll"})
    public void getById() throws SQLException{
        SeatsDAO objectDAO = new SeatsDAOImpl();
        Seats object = objectDAO.getObjectById((long) 1);
        Assert.assertTrue(object != null && object.getSeat_id() == 1);
    }

    @Test(dependsOnMethods={"getAll"})
    public void add() throws SQLException{
        Concert_hallsDAO concert_hallsDAO = new Concert_hallsDAOImpl();
        Concert_halls concert_halls = concert_hallsDAO.getObjectById((long)1);

        Type_of_seatsDAO type_of_seatsDAO = new Type_of_seatsDAOImpl();
        Type_of_seats type_of_seats = type_of_seatsDAO.getObjectById((long)1);

        SeatsDAO objectDAO = new SeatsDAOImpl();
        Seats object1 = new Seats(null,null,1,1,concert_halls,type_of_seats);
        objectDAO.save(object1);
        Seats object2 = objectDAO.getObjectById(object1.getSeat_id());
        Assert.assertTrue(object1.equals(object2));
    }

    @Test(dependsOnMethods={"getAll"})
    public void delete() throws SQLException{
        Concert_hallsDAO concert_hallsDAO = new Concert_hallsDAOImpl();
        Concert_halls concert_halls = concert_hallsDAO.getObjectById((long)1);

        Type_of_seatsDAO type_of_seatsDAO = new Type_of_seatsDAOImpl();
        Type_of_seats type_of_seats = type_of_seatsDAO.getObjectById((long)1);

        SeatsDAO objectDAO = new SeatsDAOImpl();
        Seats object1 = new Seats(null,null,1,1,concert_halls,type_of_seats);

        objectDAO.save(object1);
        long id = object1.getSeat_id();
        objectDAO.delete(object1);
        Seats object2 = objectDAO.getObjectById(id);
        Assert.assertTrue(object2 == null);
    }

    @Test(dependsOnMethods={"getAll"})
    public void update() throws SQLException{
        Concert_hallsDAO concert_hallsDAO = new Concert_hallsDAOImpl();
        Concert_halls concert_halls = concert_hallsDAO.getObjectById((long)1);

        Type_of_seatsDAO type_of_seatsDAO = new Type_of_seatsDAOImpl();
        Type_of_seats type_of_seats = type_of_seatsDAO.getObjectById((long)1);

        SeatsDAO objectDAO = new SeatsDAOImpl();
        Seats object = new Seats(null,null,1,1,concert_halls,type_of_seats);
        objectDAO.save(object);
        long id = object.getSeat_id();
        object.setSection(3);
        objectDAO.update(object);
        Assert.assertTrue(objectDAO.getObjectById(id).getSection().equals(3));
    }
}

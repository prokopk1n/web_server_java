package Concert_halls;

import DAO.Concert_halls.*;
import entity.Concert_halls.Concert_halls;
import entity.Concert_halls.Seats;
import entity.Concert_halls.Type_of_seats;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collection;

public class Type_of_seatsTEST {
    @Test
    public void getAll() {
        boolean[] result = new boolean[36];
        for (int i = 0; i < 7; i++)
            result[i] = false;
        Type_of_seatsDAO objectDAO = new Type_of_seatsDAOImpl();
        Collection<Type_of_seats> objects = objectDAO.getAll();
        for (Type_of_seats object : objects) {
            if (object.getType_id()> 7 || object.getType_id()<=0) {
                result[0] = false;
                break;
            }
            else if (!result[(int)object.getType_id()-1]) {
                result[(int)object.getType_id()-1] = true;
            }
            else {
                result[0] = false;
                break;
            }
        }
        boolean res=true;
        for (int i=0;i<7;i++) {
            res = res && result[i];
        }
        Assert.assertTrue(res);
    }

    @Test
    public void getById() {
        Type_of_seatsDAO objectDAO = new Type_of_seatsDAOImpl();
        Type_of_seats object = objectDAO.getObjectById((long) 1);
        Assert.assertTrue(object != null && object.getType_id() == 1);
    }

    @Test
    public void add() {
        Type_of_seatsDAO objectDAO = new Type_of_seatsDAOImpl();
        Type_of_seats object1 = new Type_of_seats("Seat");
        objectDAO.save(object1);
        Type_of_seats object2 = objectDAO.getObjectById(object1.getType_id());
        Assert.assertTrue(object1.equals(object2));
    }

    @Test
    public void delete() {
        Type_of_seatsDAO objectDAO = new Type_of_seatsDAOImpl();
        Type_of_seats object1 = new Type_of_seats("Seat");

        objectDAO.save(object1);
        long id = object1.getType_id();
        objectDAO.delete(object1);
        Type_of_seats object2 = objectDAO.getObjectById(id);
        Assert.assertTrue(object2 == null);
    }

    @Test
    public void update() {
        Type_of_seatsDAO objectDAO = new Type_of_seatsDAOImpl();
        Type_of_seats object = new Type_of_seats("Seat");

        objectDAO.save(object);
        long id = object.getType_id();
        object.setName("newName");
        objectDAO.update(object);
        Assert.assertTrue(objectDAO.getObjectById(id).getName().equals("newName"));
    }
}

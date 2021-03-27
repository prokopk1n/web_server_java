package Tickets;

import DAO.Concert_halls.Concert_hallsDAO;
import DAO.Concert_halls.Concert_hallsDAOImpl;
import DAO.Concert_halls.SeatsDAO;
import DAO.Concert_halls.SeatsDAOImpl;
import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import DAO.Tickets.ScheduleDAO;
import DAO.Tickets.ScheduleDAOImpl;
import DAO.Tickets.TicketsDAO;
import DAO.Tickets.TicketsDAOImpl;
import entity.Concert_halls.Concert_halls;
import entity.Concert_halls.Seats;
import entity.Theaters.Theaters;
import entity.Tickets.Schedule;
import entity.Tickets.Tickets;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Collection;

public class TicketsTEST {
    @Test
    public void getAll() throws SQLException {
        boolean[] result = new boolean[36];
        for (int i = 0; i < 36; i++)
            result[i] = false;
        TicketsDAO objectDAO = new TicketsDAOImpl();
        Collection<Tickets> objects = objectDAO.getAll();
        for (Tickets object : objects) {
            if (object.getTicket_id()> 36 || object.getTicket_id()<=0) {
                result[0] = false;
                break;
            }
            else if (!result[(int)object.getTicket_id()-1]) {
                result[(int)object.getTicket_id()-1] = true;
            }
            else {
                result[0] = false;
                break;
            }
        }
        for (int i=0;i<36;i++)
            Assert.assertTrue(result[i]);
    }

    @Test(dependsOnMethods={"getAll"})
    public void getById() throws SQLException{
        TicketsDAO objectDAO = new TicketsDAOImpl();
        Tickets object = objectDAO.getObjectById((long) 1);
        Assert.assertNotNull(object);
        Assert.assertTrue(object.getTicket_id() == 1);
    }

    @Test(dependsOnMethods={"getAll"})
    public void add() throws SQLException{
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        Schedule schedule = scheduleDAO.getObjectById((long)1);

        SeatsDAO seatsDAO = new SeatsDAOImpl();
        Seats seats = seatsDAO.getObjectById((long)1);

        Tickets object1 = new Tickets(schedule, seats, 1000, true);
        TicketsDAO objectDAO = new TicketsDAOImpl();
        objectDAO.save(object1);
        Tickets object2 = objectDAO.getObjectById(object1.getTicket_id());
        Assert.assertTrue(object1.equals(object2));
    }

    @Test(dependsOnMethods={"getAll"})
    public void delete() throws SQLException{
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        Schedule schedule = scheduleDAO.getObjectById((long)1);

        SeatsDAO seatsDAO = new SeatsDAOImpl();
        Seats seats = seatsDAO.getObjectById((long)1);

        Tickets object1 = new Tickets(schedule, seats, 1000, true);
        TicketsDAO objectDAO = new TicketsDAOImpl();

        objectDAO.save(object1);
        long id = object1.getTicket_id();
        objectDAO.delete(object1);
        Tickets object2 = objectDAO.getObjectById(id);
        Assert.assertTrue(object2 == null);
    }

    @Test(dependsOnMethods={"getAll"})
    public void update() throws SQLException{
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        Schedule schedule = scheduleDAO.getObjectById((long)1);
        SeatsDAO seatsDAO = new SeatsDAOImpl();
        Seats seats = seatsDAO.getObjectById((long)1);

        Tickets object = new Tickets(schedule, seats, 1000, true);
        TicketsDAO objectDAO = new TicketsDAOImpl();

        objectDAO.save(object);
        long id = object.getTicket_id();
        object.setCost(1500);
        objectDAO.update(object);
        Assert.assertTrue(objectDAO.getObjectById(id).getCost() == 1500);
    }
}

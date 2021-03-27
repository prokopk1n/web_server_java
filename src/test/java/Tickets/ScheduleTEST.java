package Tickets;

import DAO.Concert_halls.Concert_hallsDAO;
import DAO.Concert_halls.Concert_hallsDAOImpl;
import DAO.Concert_halls.SeatsDAO;
import DAO.Concert_halls.SeatsDAOImpl;
import DAO.Performances.PerformancesDAO;
import DAO.Performances.PerformancesDAOImpl;
import DAO.Tickets.ScheduleDAO;
import DAO.Tickets.ScheduleDAOImpl;
import DAO.Tickets.TicketsDAO;
import DAO.Tickets.TicketsDAOImpl;
import entity.Concert_halls.Concert_halls;
import entity.Concert_halls.Seats;
import entity.Performances.Performances;
import entity.Tickets.Schedule;
import entity.Tickets.Tickets;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

public class ScheduleTEST {
    @Test
    public void getAll() throws SQLException {
        boolean[] result = new boolean[2];
        for (int i = 0; i < 2; i++)
            result[i] = false;
        ScheduleDAO objectDAO = new ScheduleDAOImpl();
        Collection<Schedule> objects = objectDAO.getAll();
        for (Schedule object : objects) {
            if (object.getEvent_id()> 2 || object.getEvent_id()<=0) {
                result[0] = false;
                break;
            }
            else if (!result[(int)object.getEvent_id()-1]) {
                result[(int)object.getEvent_id()-1] = true;
            }
            else {
                result[0] = false;
                break;
            }
        }
        for (int i=0;i<2;i++)
            Assert.assertTrue(result[i]);
    }

    @Test(dependsOnMethods={"getAll"})
    public void getById() throws SQLException{
        ScheduleDAO objectDAO = new ScheduleDAOImpl();
        Schedule object = objectDAO.getObjectById((long) 1);
        Assert.assertNotNull(object);
        Assert.assertTrue(object.getEvent_id() == 1);
    }

    @Test(dependsOnMethods={"getAll"})
    public void add() throws SQLException{
        Concert_hallsDAO concert_hallsDAO = new Concert_hallsDAOImpl();
        Concert_halls concert_halls = concert_hallsDAO.getObjectById((long)1);
        PerformancesDAO seatsDAO = new PerformancesDAOImpl();
        Performances performances = seatsDAO.getObjectById((long)1);

        Schedule object1 = new Schedule(concert_halls, performances, LocalDateTime.of(2021,3,1,15,30));
        ScheduleDAO objectDAO = new ScheduleDAOImpl();
        objectDAO.save(object1);
        Schedule object2 = objectDAO.getObjectById(object1.getEvent_id());
        Assert.assertTrue(object1.equals(object2));
    }

    @Test(dependsOnMethods={"getAll"})
    public void delete() throws SQLException{
        Concert_hallsDAO concert_hallsDAO = new Concert_hallsDAOImpl();
        Concert_halls concert_halls = concert_hallsDAO.getObjectById((long)1);
        PerformancesDAO seatsDAO = new PerformancesDAOImpl();
        Performances performances = seatsDAO.getObjectById((long)1);

        Schedule object1 = new Schedule(concert_halls, performances, LocalDateTime.of(2021,3,1,15,30));
        ScheduleDAO objectDAO = new ScheduleDAOImpl();
        objectDAO.save(object1);
        long id = object1.getEvent_id();
        objectDAO.delete(object1);
        Schedule object2 = objectDAO.getObjectById(id);
        Assert.assertTrue(object2 == null);
    }

    @Test(dependsOnMethods={"getAll"})
    public void update() throws SQLException{
        Concert_hallsDAO concert_hallsDAO = new Concert_hallsDAOImpl();
        Concert_halls concert_halls = concert_hallsDAO.getObjectById((long)1);
        PerformancesDAO seatsDAO = new PerformancesDAOImpl();
        Performances performances = seatsDAO.getObjectById((long)1);

        Schedule object = new Schedule(concert_halls, performances, LocalDateTime.of(2021,3,1,15,30));
        ScheduleDAO objectDAO = new ScheduleDAOImpl();

        objectDAO.save(object);
        long id = object.getEvent_id();
        object.setDate(LocalDateTime.of(2000,1,1,0,0));
        objectDAO.update(object);
        Assert.assertTrue(objectDAO.getObjectById(id).getDate().equals(LocalDateTime.of(2000,1,1,0,0)));
    }
}

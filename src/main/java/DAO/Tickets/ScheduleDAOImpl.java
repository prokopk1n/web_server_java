package DAO.Tickets;

import DAO.BaseDAO.baseDAOImpl;
import entity.Tickets.Schedule;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDAOImpl extends baseDAOImpl<Schedule> implements ScheduleDAO {
    @Override
    public Schedule getObjectById(Long objectId){
        Session session = null;
        Schedule object = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            object = (Schedule) session.load(Schedule.class, objectId);
        } catch (Exception e) {
            System.out.println("Exception in Performances.getById: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return object;
    }

    @Override
    public List<Schedule> getAll(){
        Session session = null;
        List<Schedule> schedule= new ArrayList<Schedule>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query<Schedule> query = session.createQuery("SELECT s FROM Schedule s", Schedule.class);
            schedule = (List<Schedule>) query.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Exception in Performances.getQuery: " + e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return schedule;
    }
}

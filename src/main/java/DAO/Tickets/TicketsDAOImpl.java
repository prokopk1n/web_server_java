package DAO.Tickets;

import DAO.BaseDAO.baseDAOImpl;
import entity.Tickets.Tickets;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class TicketsDAOImpl extends baseDAOImpl<Tickets> implements TicketsDAO{
    @Override
    public Tickets getObjectById(Long objectId){
        Session session = null;
        Tickets object = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            object = (Tickets) session.load(Tickets.class, objectId);
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
    public List<Tickets> getAll(){
        Session session = null;
        List<Tickets> tickets = new ArrayList<Tickets>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query<Tickets> query = session.createQuery("SELECT t FROM Tickets t", Tickets.class);
            tickets = (List<Tickets>) query.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Exception in Performances.getQuery: " + e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tickets;
    }
}

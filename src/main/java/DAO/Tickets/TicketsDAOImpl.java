package DAO.Tickets;

import DAO.BaseDAO.baseDAOImpl;
import entity.Tickets.Tickets;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketsDAOImpl extends baseDAOImpl<Tickets> implements TicketsDAO{
    @Override
    public Tickets getObjectById(Long objectId) throws SQLException {
        Session session = null;
        Tickets object = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        object = (Tickets) session.get(Tickets.class, objectId);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return object;
    }

    @Override
    public List<Tickets> getAll() throws SQLException{
        Session session = null;
        List<Tickets> tickets = new ArrayList<Tickets>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Tickets> query = session.createQuery("FROM Tickets", Tickets.class);
        tickets = (List<Tickets>) query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return tickets;
    }
}

package DAO.Concert_halls;

import DAO.BaseDAO.baseDAOImpl;
import entity.Concert_halls.Seats;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatsDAOImpl extends baseDAOImpl<Seats> implements SeatsDAO {
    @Override
    public Seats getObjectById(Long objectId) throws SQLException {
        Session session = null;
        Seats object = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        object = (Seats) session.get(Seats.class, objectId);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return object;
    }

    @Override
    public List<Seats> getAll() throws SQLException {
        Session session = null;
        List<Seats> seats= new ArrayList<Seats>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Seats> query = session.createQuery("FROM Seats", Seats.class);
        seats= (List<Seats>) query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return seats;
    }
}

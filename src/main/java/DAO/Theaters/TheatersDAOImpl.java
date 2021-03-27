package DAO.Theaters;

import DAO.BaseDAO.baseDAOImpl;
import entity.Theaters.Theaters;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TheatersDAOImpl extends baseDAOImpl<Theaters> implements TheatersDAO {
    @Override
    public Theaters getObjectById(Long objectId) throws SQLException {
        Session session = null;
        Theaters object = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        object = (Theaters) session.get(Theaters.class, objectId);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return object;
    }

    @Override
    public List<Theaters> getAll() throws SQLException{
        Session session = null;
        List<Theaters> theaters = new ArrayList<Theaters>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Theaters> query = session.createQuery("FROM Theaters", Theaters.class);
        theaters = (List<Theaters>) query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return theaters;
    }

    @Override
    public Collection<Theaters> getTheaterByName(String name) throws SQLException {
        Session session = null;
        List<Theaters> theaters = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Theaters> query = session.createQuery("FROM Theaters WHERE name LIKE :name", Theaters.class);
        query.setParameter("name", name);
        theaters = (List<Theaters>) query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return theaters;
    }

}

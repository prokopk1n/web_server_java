package DAO.Theaters;

import DAO.BaseDAO.baseDAOImpl;
import entity.Theaters.Theaters;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class DAOImpl extends baseDAOImpl<Theaters> implements TheatersDAO {

    @Override
    public Theaters getObjectById(Long objectId) throws SQLException {
        Session session = null;
        Theaters object = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            object = (Theaters) session.load(Theaters.class, objectId);
        } catch (Exception e) {
            System.out.println("Exception in Theaters.getById: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return object;
    }

    @Override
    public List<Theaters> getAll() throws SQLException {
        Session session = null;
        List<Theaters> theaters = new ArrayList<Theaters>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query<Theaters> query = session.createQuery("SELECT t FROM Theaters t", Theaters.class);
            theaters = (List<Theaters>) query.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Exception in Theaters.getQuery: " + e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return theaters;
    }

}

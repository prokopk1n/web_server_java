package DAO.BaseDAO;

import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.List;

public abstract class baseDAOImpl<T> implements baseDAO<T> {

    @Override
    public void save(T object) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
                session.close();
        }
    }

    @Override
    public void update(T object) throws SQLException{
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void delete(T object){
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    @Override
    public abstract T getObjectById(Long objectId) throws SQLException;

    @Override
    public abstract List<T> getAll() throws SQLException;

}

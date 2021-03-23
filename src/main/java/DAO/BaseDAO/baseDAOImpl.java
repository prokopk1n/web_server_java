package DAO.BaseDAO;

import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public abstract class baseDAOImpl<T> implements baseDAO<T> {

    @Override
    public void save(T object){
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Exception in save: " +object.toString() + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void update(T object){
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Exception in update: " + object.toString() + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(T object){
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Exception in Performances.delete: " + object.toString() +  e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public abstract T getObjectById(Long objectId);

    @Override
    public abstract List<T> getAll();

}

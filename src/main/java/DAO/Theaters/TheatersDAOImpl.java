package DAO.Theaters;

import DAO.BaseDAO.baseDAOImpl;
import entity.Theaters.Theaters;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TheatersDAOImpl extends baseDAOImpl<Theaters> implements TheatersDAO {
    @Override
    public Theaters getObjectById(Long objectId){
        Session session = null;
        Theaters object = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            object = (Theaters) session.get(Theaters.class, objectId);
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
    public List<Theaters> getAll(){
        Session session = null;
        List<Theaters> theaters = new ArrayList<Theaters>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            System.out.println("1");
            Query<Theaters> query = session.createQuery("FROM Theaters", Theaters.class);
            System.out.println("1");
            theaters = (List<Theaters>) query.list();
            System.out.println("1");
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Exception in Theaters.getAll: " + e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return theaters;
    }

    @Override
    public Collection<Theaters> getTheaterByName(String name) {
        Session session = null;
        List<Theaters> theaters = null;
        try{
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query<Theaters> query = session.createQuery("FROM Theaters WHERE name LIKE :name", Theaters.class);
            query.setParameter("name", name);
            theaters = (List<Theaters>) query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Exception in Theaters.getTheaterByName: " + e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return theaters;

    }

}

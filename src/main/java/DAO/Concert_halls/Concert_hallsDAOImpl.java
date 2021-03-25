package DAO.Concert_halls;

import DAO.BaseDAO.baseDAOImpl;
import entity.Concert_halls.Concert_halls;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class Concert_hallsDAOImpl extends baseDAOImpl<Concert_halls> implements Concert_hallsDAO {
    @Override
    public Concert_halls getObjectById(Long objectId){
        Session session = null;
        Concert_halls object = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            object = (Concert_halls) session.get(Concert_halls.class, objectId);
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
    public List<Concert_halls> getAll(){
        Session session = null;
        List<Concert_halls> concert_halls= new ArrayList<Concert_halls>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query<Concert_halls> query = session.createQuery("FROM Concert_halls", Concert_halls.class);
            concert_halls= (List<Concert_halls>) query.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Exception in Performances.getQuery: " + e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return concert_halls;
    }
}

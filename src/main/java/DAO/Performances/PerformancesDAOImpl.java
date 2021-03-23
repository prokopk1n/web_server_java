package DAO.Performances;

import DAO.BaseDAO.baseDAOImpl;
import entity.Performances.Performances;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class PerformancesDAOImpl extends baseDAOImpl<Performances> implements PerformancesDAO {

    @Override
    public Performances getObjectById(Long objectId){
        Session session = null;
        Performances object = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            object = (Performances) session.load(Performances.class, objectId);
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
    public List<Performances> getAll(){
        Session session = null;
        List<Performances> performances = new ArrayList<Performances>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query<Performances> query = session.createQuery("SELECT p FROM Performances p", Performances.class);
            performances = (List<Performances>) query.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Exception in Performances.getQuery: " + e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return performances;
    }

}

package DAO.Performances;

import DAO.BaseDAO.baseDAOImpl;
import entity.Performances.Perf_persons;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class Perf_personsDAOImpl extends baseDAOImpl<Perf_persons> implements Perf_personsDAO{
    @Override
    public Perf_persons getObjectById(Long objectId){
        Session session = null;
        Perf_persons object = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            object = (Perf_persons) session.load(Perf_persons.class, objectId);
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
    public List<Perf_persons> getAll(){
        Session session = null;
        List<Perf_persons> perf_persons= new ArrayList<Perf_persons>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query<Perf_persons> query = session.createQuery("SELECT p FROM Perf_persons p", Perf_persons.class);
            perf_persons = (List<Perf_persons>) query.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Exception in Performances.getQuery: " + e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return perf_persons;
    }

}

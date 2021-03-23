package DAO.Performances;

import DAO.BaseDAO.baseDAOImpl;
import entity.Performances.People;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class PeopleDAOImpl extends baseDAOImpl<People> implements PeopleDAO {
    @Override
    public People getObjectById(Long objectId){
        Session session = null;
        People object = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            object = (People) session.load(People.class, objectId);
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
    public List<People> getAll(){
        Session session = null;
        List<People> people = new ArrayList<People>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query<People> query = session.createQuery("SELECT p FROM People p", People.class);
            people = (List<People>) query.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Exception in Performances.getQuery: " + e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return people;
    }
}

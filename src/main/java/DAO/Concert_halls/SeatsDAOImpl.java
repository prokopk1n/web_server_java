package DAO.Concert_halls;

import DAO.BaseDAO.baseDAOImpl;
import entity.Concert_halls.Seats;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class SeatsDAOImpl extends baseDAOImpl<Seats> implements SeatsDAO {
    @Override
    public Seats getObjectById(Long objectId){
        Session session = null;
        Seats object = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            object = (Seats) session.load(Seats.class, objectId);
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
    public List<Seats> getAll(){
        Session session = null;
        List<Seats> seats= new ArrayList<Seats>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query<Seats> query = session.createQuery("SELECT c FROM Seats c", Seats.class);
            seats= (List<Seats>) query.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Exception in Performances.getAll: " + e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return seats;
    }
}

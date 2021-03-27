package DAO.Performances;

import DAO.BaseDAO.baseDAOImpl;
import entity.Performances.Performances;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerformancesDAOImpl extends baseDAOImpl<Performances> implements PerformancesDAO {

    @Override
    public Performances getObjectById(Long objectId)throws SQLException {
        Session session = null;
        Performances object = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        object = (Performances) session.get(Performances.class, objectId);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return object;
    }

    @Override
    public List<Performances> getAll()throws SQLException{
        Session session = null;
        List<Performances> performances = new ArrayList<Performances>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Performances> query = session.createQuery("FROM Performances", Performances.class);
        performances = (List<Performances>) query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return performances;
    }

}

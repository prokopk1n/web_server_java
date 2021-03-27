package DAO.Performances;

import DAO.BaseDAO.baseDAOImpl;
import entity.Performances.Perf_persons;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Perf_personsDAOImpl extends baseDAOImpl<Perf_persons> implements Perf_personsDAO{
    @Override
    public Perf_persons getObjectById(Long objectId) throws SQLException {
        Session session = null;
        Perf_persons object = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        object = (Perf_persons) session.get(Perf_persons.class, objectId);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return object;
    }

    @Override
    public List<Perf_persons> getAll() throws SQLException{
        Session session = null;
        List<Perf_persons> perf_persons= new ArrayList<Perf_persons>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Perf_persons> query = session.createQuery("FROM Perf_persons", Perf_persons.class);
        perf_persons = (List<Perf_persons>) query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return perf_persons;
    }

}

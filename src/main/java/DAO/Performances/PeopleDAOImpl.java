package DAO.Performances;

import DAO.BaseDAO.baseDAOImpl;
import entity.Performances.People;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeopleDAOImpl extends baseDAOImpl<People> implements PeopleDAO {
    @Override
    public People getObjectById(Long objectId) throws SQLException {
        Session session = null;
        People object = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        object = (People) session.get(People.class, objectId);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return object;
    }

    @Override
    public List<People> getAll() throws SQLException{
        Session session = null;
        List<People> people = new ArrayList<People>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<People> query = session.createQuery("FROM People", People.class);
        people = (List<People>) query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return people;
    }
}

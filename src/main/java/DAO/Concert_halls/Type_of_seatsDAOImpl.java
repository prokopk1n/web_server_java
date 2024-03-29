package DAO.Concert_halls;

import DAO.BaseDAO.baseDAOImpl;
import entity.Concert_halls.Type_of_seats;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Type_of_seatsDAOImpl extends baseDAOImpl<Type_of_seats> implements Type_of_seatsDAO {

    @Override
    public Type_of_seats getObjectById(Long objectId) throws SQLException {
        Session session = null;
        Type_of_seats object = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        object = (Type_of_seats) session.get(Type_of_seats.class, objectId);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return object;
    }

    @Override
    public List<Type_of_seats> getAll(){
        Session session = null;
        List<Type_of_seats> type_of_seats= new ArrayList<Type_of_seats>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Type_of_seats> query = session.createQuery("FROM Type_of_seats", Type_of_seats.class);
        type_of_seats= (List<Type_of_seats>) query.list();
        session.getTransaction().commit();

        if (session != null && session.isOpen()) {
            session.close();
        }
        return type_of_seats;
    }
}

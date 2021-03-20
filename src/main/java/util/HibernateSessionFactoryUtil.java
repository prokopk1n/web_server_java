package util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(entity.Performances.Performances.class);
                configuration.addAnnotatedClass(entity.Performances.Perf_persons.class);
                configuration.addAnnotatedClass(entity.Theaters.Theaters.class);
                configuration.addAnnotatedClass(entity.Tickets.Schedule.class);
                configuration.addAnnotatedClass(entity.Tickets.Tickets.class);
                configuration.addAnnotatedClass(entity.Concert_halls.Concert_halls.class);
                configuration.addAnnotatedClass(entity.Concert_halls.Seats.class);
                configuration.addAnnotatedClass(entity.Concert_halls.Type_of_seats.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}

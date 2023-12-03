package es.ecofam.economiafamiliar.modelo.hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException ex) {
            System.err.println("Initial SessionFactory creation failed." +ex);
            throw new ExceptionInInitializerError(ex); }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
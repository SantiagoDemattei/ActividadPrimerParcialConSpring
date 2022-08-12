import java.util.Date;
import java.util.List;

import Hibernate.Event;
import Hibernate.HibernateUtil;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

public class Main {
    private final static Logger log = Logger.getLogger(Main.class);
    /**
     * @param args
     */
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
        new Main();
    }

    public Main() {
        createAndStoreEvent("El Hibernate.Event", new Date());
        listEvents();
        HibernateUtil.getSessionFactory().close();
    }

    private Long createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        session.save(theEvent);
        session.getTransaction().commit();
        log.info("Insertado: "+theEvent);
        return theEvent.getId();
    }

    private List<Event> listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Event> result = (List<Event>)session.createQuery("from Event").list();
        session.getTransaction().commit();
        for (Event evento : result) {
            log.info("Leido: "+evento);
        }
        return result;
    }
}

package CRUDs;


import
import org.hibernate.Session;

public class CRUDUsuario {

    public void insertarUsuario(Usuario u){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


    }

}

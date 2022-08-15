package Database;

import Dominio.UserService;
import Dominio.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CRUDUsuario {
    public static void insertarUsuario(Usuario u){
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
        Session s = sf.openSession();
        try {
            s.beginTransaction();
            s.save(u);
            s.getTransaction().commit();
        } catch (Exception e) {
            UserService.mostrarMensajeDeError(e.getStackTrace().toString());
            UserService.mostrarMensajeDeError("Error al insertar el registro en DB");
        } finally {
            s.close();
        }
    }

    public static Usuario buscarUsuarioPorMail(String m){
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
        Session s = sf.openSession();
        try {
            s.beginTransaction();
            Usuario u = (Usuario) s.createQuery("from Usuario where mail = :mail").setParameter("mail", m).uniqueResult();
            return u;
        } catch (Exception e) {
            UserService.mostrarMensajeDeError(e.getStackTrace().toString());
            UserService.mostrarMensajeDeError("Error al buscar el usuario en DB");
        } finally {
            s.close();
        }
        return null;
    }

    public static void actualizarUsuario(Usuario u){
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
        Session s = sf.openSession();
        try {
            s.beginTransaction();
            s.update(u);
            s.getTransaction().commit();
        } catch (Exception e) {
            UserService.mostrarMensajeDeError(e.getStackTrace().toString());
            UserService.mostrarMensajeDeError("Error al actualizar el registro del usuario en DB");
        } finally {
            s.close();
        }
    }

    public static boolean coincideContrasenia(String passIngresada, String passDb){
        return passIngresada.equals(passDb);
    }
}
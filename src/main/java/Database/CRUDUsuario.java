package Database;

import Dominio.Categoria;
import Dominio.UserService;
import Dominio.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.logging.Level;

public class CRUDUsuario {
    public static void insertarUsuario(Usuario u){
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
        Session s = sf.openSession();
        try {
            s.beginTransaction();
            s.save(u);
            s.getTransaction().commit();
            UserService.mostrarMensajeConsulta("Registro insertado correctamente en DB");
        } catch (Exception e) {
            UserService.mostrarMensajeDeError("Error al insertar el registro en DB");
        } finally {
            s.close();
        }
    }

    public static Usuario buscarUsuarioPorMail(String m){
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
        Session s = sf.openSession();
        try {
            s.beginTransaction();
            Usuario u = (Usuario) s.createQuery("from Usuario where mail = :mail").setParameter("mail", m).uniqueResult();
            return u;
        } catch (Exception e) {
            e.printStackTrace();
            UserService.mostrarMensajeDeError("Error al buscar el usuario en DB");
        } finally {
            s.close();
        }
        return null;
    }

    public static void actualizarUsuario(Usuario u){
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
        Session s = sf.openSession();
        try {
            s.beginTransaction();
            s.update(u);
            s.getTransaction().commit();
            UserService.mostrarMensajeConsulta("Registro actualizado correctamente en DB");
        } catch (Exception e) {
            UserService.mostrarMensajeDeError("Error al actualizar el registro en DB");
        } finally {
            s.close();
        }
    }

    public static boolean coincideContrasenia(String passIngresada, String passDb){
        return passIngresada.equals(passDb);
    }
}
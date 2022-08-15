package Database;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import Dominio.Vuelo;
import Dominio.UserService;

import java.util.ArrayList;
import java.util.List;

public class CRUDVuelo {
    public static void insertarVuelo(Vuelo v){
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Vuelo.class).buildSessionFactory();
        Session s = sf.openSession();
        try {
            s.beginTransaction();
            s.save(v);
            s.getTransaction().commit();
        } catch (Exception e) {
            UserService.mostrarMensajeDeError("Error al insertar el registro en DB");
        } finally {
            s.close();
        }
    }

    public static void actualizarVuelo(Vuelo v){
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Vuelo.class).buildSessionFactory();
        Session s = sf.openSession();
        try {
            s.beginTransaction();
            s.update(v);
            s.getTransaction().commit();
        } catch (Exception e) {
            UserService.mostrarMensajeDeError(e.getStackTrace().toString());
            UserService.mostrarMensajeDeError("Error al actualizar el registro del usuario en DB");
        } finally {
            s.close();
        }
    }

    public static List<Vuelo> obtenerVuelosDb(){
        List<Vuelo> vuelos = new ArrayList<>();
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Vuelo.class).buildSessionFactory();
        Session s = sf.openSession();
        try {
            s.beginTransaction();
            vuelos = s.createQuery("from Vuelo").list();
        } catch (Exception e) {
            UserService.mostrarMensajeDeError(e.getStackTrace().toString());
            UserService.mostrarMensajeDeError("Error al obtener los vuelos de DB");
        } finally {
            s.close();
            return vuelos;
        }
    }

    public static void borrarVuelo(Vuelo v){
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Vuelo.class).buildSessionFactory();
        Session s = sf.openSession();
        try {
            s.beginTransaction();
            s.delete(v);
            s.getTransaction().commit();
        } catch (Exception e) {
            UserService.mostrarMensajeDeError(e.getStackTrace().toString());
            UserService.mostrarMensajeDeError("Error al borrar el vuelo de DB");
        } finally {
            s.close();
        }
    }

}

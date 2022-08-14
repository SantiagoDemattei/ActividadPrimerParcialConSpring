package Database;

import Dominio.UserService;
import Dominio.Categoria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CRUDCategoria {

    public static void actualizarCategoria(Categoria c){
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Categoria.class).buildSessionFactory();
        Session se = sf.openSession();
        try {
            se.beginTransaction();
            se.update(c);
            se.getTransaction().commit();
            UserService.mostrarMensajeConsulta("Registro actualizado correctamente en DB");
        } catch (Exception e) {
            UserService.mostrarMensajeDeError("Error al actualizar el registro en DB");
        } finally {
            se.close();
        }
    }

    public static void actualizarCategoriaAPremium(Categoria catVieja, Categoria catNueva){
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Categoria.class).buildSessionFactory();
        Session se = sf.openSession();
        try {
            se.beginTransaction();
            se.delete(catVieja);
            se.save(catNueva);
            se.getTransaction().commit();
            UserService.mostrarMensajeConsulta("Registro actualizado correctamente en DB");
        } catch (Exception e) {
            UserService.mostrarMensajeDeError("Error al actualizar el registro en DB");
        } finally {
            se.close();
        }
    }
}

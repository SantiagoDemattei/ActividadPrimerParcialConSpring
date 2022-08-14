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
        } catch (Exception e) {
            UserService.mostrarMensajeDeError("Error al actualizar el registro de categoria en DB");
        } finally {
            se.close();
        }
    }

    public static void actualizarCategoriaAPremium(Categoria catVieja, Categoria catNueva){
        // entity manager
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Categoria.class).buildSessionFactory();
        Session se = sf.openSession();
        try {
            se.beginTransaction();
            se.createNativeQuery("UPDATE Categoria SET Nombre = :premium, cantMax = :cantMax WHERE Categoria_id = :id").setParameter("premium", "PremiumAdapter").setParameter("cantMax", null).setParameter("id", catVieja.getId()).executeUpdate();
            se.getTransaction().commit();
            UserService.mostrarMensajeConsulta("Registro actualizado correctamente en DB");
        } catch (Exception e) {
            UserService.mostrarMensajeDeError("Error al actualizar el registro de categoria en DB");
        } finally {
            se.close();
        }
    }

    public static void borrarCategoria(Categoria cat){
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Categoria.class).buildSessionFactory();
        Session se = sf.openSession();
        try {
            se.beginTransaction();
            se.delete(cat);
            se.getTransaction().commit();
            UserService.mostrarMensajeConsulta("Registro borrado correctamente en DB");
        } catch (Exception e) {
            UserService.mostrarMensajeDeError("Error al borrar el registro de categoria en DB");
        } finally {
            se.close();
        }
    }
}

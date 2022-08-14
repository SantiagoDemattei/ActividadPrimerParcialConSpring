
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import Dominio.*;

public class Main {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/parcialdb?useSSL=false";
        String username = "admin";
        String password = "admin";

        try {
            System.out.println("Intentando conectar");
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Conectado");

        } catch (Error e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
        Session miSession = sessionFactory.openSession();

        try{
            //AGREGAR REGISTROS
          // Categoria cate = new Estandar();
            Categoria cate3 = new Estandar();
            Usuario user = new Usuario("Juan", "Martinez","jdjd@mail.com", "juancho", cate3, true);
            miSession.beginTransaction();

         //  miSession.save(cate);
           miSession.save(cate3);
           miSession.save(user);
            miSession.getTransaction().commit();
            System.out.println("Categoria guardado");

            //LEER REGISTROS
            miSession.beginTransaction();
            System.out.println("Lectura del registro con id "+ cate3.getId());
            Categoria cateInsertada = miSession.get(Categoria.class, cate3.getId());
            System.out.println("REGISTRO: "+ cateInsertada);
            miSession.getTransaction().commit();

            //CONSULTA DE CATEGORIAS (Nos devuelve todas las categorias de la base de datos)
            miSession.beginTransaction();
            List<Categoria> categorias = miSession.createQuery("from Categoria").getResultList();
            for (Categoria catego : categorias) {
                System.out.println(catego);

            }
            miSession.getTransaction().commit();

            //CONSULTA POR UN CRITERIO
            miSession.beginTransaction();
            List<Categoria> categor = miSession.createQuery("from Categoria cate where cate.nombre='Dani'").getResultList();
            for (Categoria catego : categor) {
                System.out.println(catego);

            }
            miSession.getTransaction().commit();

            //ACTUALIZAR CATEGORIAS
            int categoriaId = 6;
            miSession.beginTransaction();

            Categoria categoriaUpdate = miSession.get(Categoria.class, categoriaId);

            miSession.getTransaction().commit();
            System.out.println("Categoria actualizada");
            miSession.close();

        }finally {
            sessionFactory.close();
        }
    }

}


}

package Database;

import Dominio.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class UsuarioDb {

    private static final Encriptacion enc = new Encriptacion();

    public static void registrarUsuario(Usuario user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conn = Init.initDb();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO usuario (Nombre, Apellido, Mail, Contraseña, Categoria, PagaMembresia) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, user.getNombre());
        stmt.setString(2, user.getApellido());
        stmt.setString(3, user.getMail());

        String contraseniaEncriptada = enc.encriptacion(user.getPassword());
        stmt.setString(4, contraseniaEncriptada);

        stmt.setInt(5, agregarCategoria(conn, user.getCategoria()));
        stmt.setBoolean(6, user.getPagaMembresia());
        stmt.execute();
        int generatedKey = 0;
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            generatedKey = rs.getInt(1);
            user.setId(generatedKey);
        }

        conn.close();
    }


    public static Integer agregarCategoria(Connection conn, Categoria categoria) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO categoria (Nombre, CantMax) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, categoria.getNombre());
        if(categoria.getNombre() == "PremiumAdapter")
            stmt.setNull(2, Types.INTEGER);
        else
            stmt.setInt(2, categoria.getCantMax());
        stmt.execute();
        int generatedKey = 0;
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        categoria.setId(generatedKey);
        return generatedKey;
    }

    public static Usuario buscarEnDb(String email) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Connection conn = Init.initDb();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario WHERE Mail = ?");
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        Usuario user = null;
        if (rs.next()) {
            Categoria cat = buscarCategoriaEnDb(conn, rs.getInt("Categoria"));
            String passDesencriptada = enc.desencriptacion(rs.getString("Contraseña"));
            user = new Usuario(rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("Mail"), passDesencriptada,  cat, rs.getBoolean("PagaMembresia"));
            user.setId(rs.getInt(6));
        }
        conn.close();
        return user;
    }

    public static boolean coincideContrasenia(String passIngresada, String passDb){
        return passIngresada.equals(passDb);
    }

    public static Categoria buscarCategoriaEnDb(Connection conn, Integer categoriaId) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categoria WHERE Categoria_id = ?");
        stmt.setInt(1, categoriaId);
        ResultSet rs2 = stmt.executeQuery();
        if(rs2.next()){
            Class<?> clazz = Class.forName("Dominio." + rs2.getString("Nombre"));
            Categoria date = (Categoria) clazz.newInstance();
            date.setNombre(rs2.getString("Nombre"));
            date.setCantMax(rs2.getInt("CantMax"));
            date.setId(rs2.getInt("Categoria_id"));
            return date;
        }
        else return null;
    }

    public static void actualizarUsuarioEnDb(Usuario u) throws Exception{
        Connection conn = Init.initDb();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE usuario SET Nombre = ?, Apellido = ?, Mail = ?, Contraseña = ?, PagaMembresia = ? WHERE Id = ?");
            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getApellido());
            stmt.setString(3, u.getMail());

            String contraseniaEncriptada = enc.encriptacion(u.getPassword());
            stmt.setString(4, contraseniaEncriptada);

            stmt.setBoolean(5, u.getPagaMembresia());
            stmt.setInt(6, u.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void actualizarCategoria(Categoria cat) throws Exception{
        Connection conn = Init.initDb();
        actualizarCategoriaEnDb(conn, cat);
        conn.close();
    }

    public static void actualizarCategoriaEnDb(Connection conn, Categoria categoria) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE categoria SET Nombre = ?, CantMax = ? WHERE Categoria_id = ?");
        stmt.setString(1, categoria.getNombre());

        if(categoria.getNombre() == "PremiumAdapter")
            stmt.setNull(2, Types.INTEGER);
        else
            stmt.setInt(2, categoria.getCantMax());

        stmt.setInt(3, categoria.getId());
        stmt.executeUpdate();
    }

}
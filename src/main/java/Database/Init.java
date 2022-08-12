package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Init {

    public static Connection initDb() throws SQLException{
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/parcialdb"; // TODO: VER CLAVES DE AUTH
            String username = "admin";
            String password = "admin";
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            return conn;
        } catch (Error e) {
            e.printStackTrace();
        }
        return null;
    }
}
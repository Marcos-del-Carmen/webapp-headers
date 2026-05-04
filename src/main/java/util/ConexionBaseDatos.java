package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static String url = "jdbc:mysql://localhost:3306/db_tienda?serverTimezone=America/Santiago";
    private static String user = "root";
    private static String password = "";

    public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(url, user, password);
    }
}

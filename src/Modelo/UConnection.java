package Modelo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

public class UConnection implements Serializable {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            String driver = "com.mysql.jdbc.Driver";
            String usr = "MrLennon";
            String pwd = "123456";
            String bd = "creedm";
            String url = "jdbc:mysql://127.0.0.1:3306/";

            Class.forName(driver);
            System.out.println("Conectando a la base de datos...");
            
            conn = DriverManager.getConnection(url + bd, usr, pwd);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al crear la conexion", ex);
        }
        return conn;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionABD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author adamj
 */
public class Conexion {
    private static String serv = "localhost";
    private static String db = "agricultura_precision";
    private static String user = "root";
    private static String pwd = "";
    
    
    //Este metodo devuelve un objeto de tipo Connection
    public static Connection getConexion() {
        Connection conexion = null;
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.jdbc.Driver");
            
            // Establecer la conexión
            String url = "jdbc:mysql://" + serv + "/" + db;
            conexion = DriverManager.getConnection(url, user, pwd);
            System.out.println("Conexión exitosa a la base de datos");
            return conexion;
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            e.printStackTrace();
        }
        return null;
    }
}

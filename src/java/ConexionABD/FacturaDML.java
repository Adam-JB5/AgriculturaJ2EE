/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionABD;

import Modelo.Factura;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author adamj
 */
public class FacturaDML {
    
    public static boolean insertar(Connection BD, int idTrabajo, double dinero, String estado, String fechaEmision, String fechaPago) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("INSERT INTO facturas(ID_Trabajo, Dinero, Estado, Fecha_Emision, Fecha_Pago) VALUES ('" + idTrabajo + "','" + dinero + "','" + estado + "','" + fechaEmision + "','" + fechaPago + "')");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static ArrayList<Factura> listar(Connection BD) {
        String consulta = "SELECT * FROM facturas";
        ArrayList<Factura> lista = new ArrayList<>();
        try (Statement st = BD.createStatement(); ResultSet rs = st.executeQuery(consulta)){
            
            while (rs.next()) {
                Factura factura = new Factura(
                    rs.getInt("ID"),
                    rs.getInt("ID_Trabajo"),
                    rs.getDouble("Dinero"),
                    rs.getString("Estado"),
                    rs.getDate("Fecha_Emision"),
                    rs.getDate("Fecha_Pago"));
                lista.add(factura);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static boolean eliminar(Connection BD, int id) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("DELETE FROM facturas WHERE id = '" + id + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

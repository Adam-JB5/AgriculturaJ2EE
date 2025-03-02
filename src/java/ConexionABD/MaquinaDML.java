/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionABD;

import Modelo.Maquina;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author adamj
 */
public class MaquinaDML {
    
    public static boolean insertar(Connection BD, String tipo, String estado) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("INSERT INTO maquinas(Tipo, Estado) VALUES ('" + tipo + "', '" + estado + "')");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public static ArrayList<Maquina> listar(Connection BD) {
        String consulta = "SELECT * FROM maquinas";
        ArrayList<Maquina> lista = new ArrayList<>();
        try (Statement st = BD.createStatement(); ResultSet rs = st.executeQuery(consulta)){
            
            while (rs.next()) {
                Maquina maquina = new Maquina(
                    rs.getInt("ID"),
                    rs.getString("Tipo"),
                    rs.getString("Estado"));
                lista.add(maquina);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static boolean actualizarMaquina(Connection BD, String tipo, String estado, int idMaquina) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("UPDATE maquinas SET Tipo = '" + tipo + "', Estado = '" + estado + "' WHERE ID = '" + idMaquina + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean liberarMaquina(Connection BD, int idMaquina) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("UPDATE maquinas SET Estado = 'Libre' WHERE ID = (SELECT ID_Maquina FROM trabajos WHERE ID = '" + idMaquina + "')");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public static boolean eliminar(Connection BD, int id) {
        try {
            Statement st = BD.createStatement();
        
            st.executeUpdate("DELETE FROM maquinas WHERE ID = '" + id + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }
}

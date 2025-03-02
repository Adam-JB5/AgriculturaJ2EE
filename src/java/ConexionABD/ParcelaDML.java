/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionABD;

import Modelo.Parcela;
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
public class ParcelaDML {
    
    public static boolean insertar(Connection BD, int idAgricultor, String ubicacion, double superficie, String ccaa) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("INSERT INTO parcelas(ID_Agricultor, Ubicacion, Superficie, Comunidad_Autonoma) VALUES ('" + idAgricultor + "','" + ubicacion + "','" + superficie + "','" + ccaa + "')");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static ArrayList<Parcela> listar(Connection BD) {
        String consulta = "SELECT * FROM parcelas";
        ArrayList<Parcela> lista = new ArrayList<>();
        try (Statement st = BD.createStatement(); ResultSet rs = st.executeQuery(consulta)){
            
            while (rs.next()) {
                Parcela parcela = new Parcela(
                    rs.getInt("ID"),
                    rs.getInt("ID_agricultor"),
                    rs.getString("Ubicacion"),
                    rs.getDouble("Superficie"),
                    rs.getString("Comunidad_Autonoma"));
                lista.add(parcela);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static boolean eliminar(Connection BD, int id) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("DELETE FROM parcelas WHERE ID = '" + id + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

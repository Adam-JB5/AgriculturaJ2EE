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
    
    public static ArrayList<Parcela> obtenerParcelasAgricultor(Connection BD, int idAgricultor) {
        String consulta = "SELECT * FROM parcelas WHERE ID_Agricultor = '" + idAgricultor + "'";
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
    
    public static boolean actualizarParcela(Connection BD, int idAgricultor, String ubicacion, double superficie, String ccaa, int idParcela) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("UPDATE parcelas SET ID_Agricultor = '" + idAgricultor + "', Ubicacion = '" + ubicacion + "', Superficie = '" + superficie + "', Comunidad_Autonoma = '" + ccaa + "' WHERE ID = '" + idParcela + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static double obtenerSuperficieParcela(Connection BD, int idParcela) {
        try {
            Statement st = BD.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT Superficie FROM parcelas WHERE ID = '" + idParcela + "'");
            if (rs.next()) {
                return rs.getDouble("Superficie");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    public static double obtenerPrecioHectarea(Connection BD, String tipoTrabajo) {
        try {
            Statement st = BD.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT Precio_por_Hectarea FROM precios_trabajos WHERE Tipo_Trabajo = '" + tipoTrabajo + "'");
            
            if (rs.next()) {
                return rs.getDouble("Precio_por_Hectarea");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionABD;

import Modelo.Trabajo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author adamj
 */
public class TrabajoDML {
    
    public static boolean insertar(Connection BD, String tipo, String estado) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("INSERT INTO trabajos(Tipo, Estado) VALUES ('" + tipo + "','" + estado + "')");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static ArrayList<Trabajo> listar(Connection BD) {
        String consulta = "SELECT * FROM trabajos";
        ArrayList<Trabajo> lista = new ArrayList<>();
        try (Statement st = BD.createStatement(); ResultSet rs = st.executeQuery(consulta)){
            
            while (rs.next()) {
                Trabajo trabajo = new Trabajo(
                    rs.getInt("ID"),
                    rs.getInt("ID_parcela"),
                    rs.getInt("ID_Maquinista"),
                    rs.getInt("ID_Maquina"),
                    rs.getString("Tipo"),
                    rs.getString("Estado"),
                    rs.getDate("Fecha_Inicio"),
                    rs.getDate("Fecha_Fin"));
                lista.add(trabajo);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static ArrayList<Trabajo> obtenerTrabajosAsignados(Connection BD, int idMaquinista) {
        String consulta = "SELECT * FROM trabajos WHERE Estado = 'Asignado' AND ID_Maquinista = '" + idMaquinista + "'";
        ArrayList<Trabajo> lista = new ArrayList<>();
        try (Statement st = BD.createStatement(); ResultSet rs = st.executeQuery(consulta)){
            
            while (rs.next()) {
                Trabajo trabajo = new Trabajo(
                    rs.getInt("ID"),
                    rs.getInt("ID_parcela"),
                    rs.getInt("ID_Maquinista"),
                    rs.getInt("ID_Maquina"),
                    rs.getString("Tipo"),
                    rs.getString("Estado"),
                    rs.getDate("Fecha_Inicio"),
                    rs.getDate("Fecha_Fin"));
                lista.add(trabajo);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static ArrayList<Trabajo> obtenerTrabajosIniciados(Connection BD, int idMaquinista) {
        String consulta = "SELECT * FROM trabajos WHERE Estado = 'Iniciado' AND ID_Maquinista = '" + idMaquinista + "'";
        ArrayList<Trabajo> lista = new ArrayList<>();
        try (Statement st = BD.createStatement(); ResultSet rs = st.executeQuery(consulta)){
            
            while (rs.next()) {
                Trabajo trabajo = new Trabajo(
                    rs.getInt("ID"),
                    rs.getInt("ID_parcela"),
                    rs.getInt("ID_Maquinista"),
                    rs.getInt("ID_Maquina"),
                    rs.getString("Tipo"),
                    rs.getString("Estado"),
                    rs.getDate("Fecha_Inicio"),
                    rs.getDate("Fecha_Fin"));
                lista.add(trabajo);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static ArrayList<Trabajo> obtenerTrabajosIniciadosAgricultor(Connection BD, int idAgricultor) {
        String consulta = "SELECT * FROM trabajos WHERE Estado = 'Iniciado' AND ID_Parcela IN (SELECT ID FROM parcelas WHERE ID_Agricultor = '" + idAgricultor + "')";
        ArrayList<Trabajo> lista = new ArrayList<>();
        try (Statement st = BD.createStatement(); ResultSet rs = st.executeQuery(consulta)){
            
            while (rs.next()) {
                Trabajo trabajo = new Trabajo(
                    rs.getInt("ID"),
                    rs.getInt("ID_parcela"),
                    rs.getInt("ID_Maquinista"),
                    rs.getInt("ID_Maquina"),
                    rs.getString("Tipo"),
                    rs.getString("Estado"),
                    rs.getDate("Fecha_Inicio"),
                    rs.getDate("Fecha_Fin"));
                lista.add(trabajo);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static ArrayList<Trabajo> obtenerTrabajosFinalizados(Connection BD, int idMaquinista) {
        String consulta = "SELECT * FROM trabajos WHERE Estado = 'Finalizado' AND ID_Maquinista = '" + idMaquinista + "'";
        ArrayList<Trabajo> lista = new ArrayList<>();
        try (Statement st = BD.createStatement(); ResultSet rs = st.executeQuery(consulta)){
            
            while (rs.next()) {
                Trabajo trabajo = new Trabajo(
                    rs.getInt("ID"),
                    rs.getInt("ID_parcela"),
                    rs.getInt("ID_Maquinista"),
                    rs.getInt("ID_Maquina"),
                    rs.getString("Tipo"),
                    rs.getString("Estado"),
                    rs.getDate("Fecha_Inicio"),
                    rs.getDate("Fecha_Fin"));
                lista.add(trabajo);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static ArrayList<Trabajo> obtenerTrabajosFinalizadosAgricultor(Connection BD, int idAgricultor) {
        String consulta = "SELECT * FROM trabajos WHERE Estado = 'Finalizado' AND ID_Parcela IN (SELECT ID FROM parcelas WHERE ID_Agricultor = '" + idAgricultor + "')";
        ArrayList<Trabajo> lista = new ArrayList<>();
        try (Statement st = BD.createStatement(); ResultSet rs = st.executeQuery(consulta)){
            
            while (rs.next()) {
                Trabajo trabajo = new Trabajo(
                    rs.getInt("ID"),
                    rs.getInt("ID_parcela"),
                    rs.getInt("ID_Maquinista"),
                    rs.getInt("ID_Maquina"),
                    rs.getString("Tipo"),
                    rs.getString("Estado"),
                    rs.getDate("Fecha_Inicio"),
                    rs.getDate("Fecha_Fin"));
                lista.add(trabajo);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    
    public static boolean iniciarTrabajo(Connection BD, String estado, String fechaInicio, int idTrabajo) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("UPDATE trabajos SET Estado = '" + estado + "', Fecha_Inicio = '" + fechaInicio + "' WHERE ID = '" + idTrabajo + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean finalizarTrabajo(Connection BD, String estado, String fechaFin, int idTrabajo) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("UPDATE trabajos SET Estado = '" + estado + "', Fecha_Fin = '" + fechaFin + "' WHERE ID = '" + idTrabajo + "'");
            MaquinaDML.liberarMaquina(BD, idTrabajo);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean eliminar(Connection BD, int id) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("DELETE FROM trabajos WHERE id = '" + id + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}

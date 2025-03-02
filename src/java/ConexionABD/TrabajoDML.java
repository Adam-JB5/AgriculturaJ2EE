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
    
    public static boolean asignarTrabajo(Connection BD, int idTrabajo, int idParcela, int idMaquinista, int idMaquina, String tipo, String estado) {
        try {
            Statement st = BD.createStatement();
            
            // Verificar que la máquina esté libre y su tipo coincida con el del trabajo
            String consultaMaquina = "SELECT Tipo, Estado FROM maquinas WHERE ID = " + idMaquina;
            ResultSet rs = st.executeQuery(consultaMaquina);
            if (rs.next()) {
                String tipoMaquina = rs.getString("Tipo");
                String estadoMaquina = rs.getString("Estado");
                
                if (!tipoMaquina.equals(tipo)) {
                    System.out.println("Error: El tipo de trabajo debe coincidir con el tipo de máquina.");
                    return false;
                }
                
                if (!estadoMaquina.equals("Libre")) {
                    System.out.println("Error: La máquina no está libre.");
                    return false;
                }
            } else {
                System.out.println("Error: No se encontró la máquina en la base de datos.");
                return false;
            }
            
            // Asignar el trabajo
            String sqlAsignar = "UPDATE trabajos SET ID_Parcela='" + idParcela + "',ID_Maquinista='" + idMaquinista + "',ID_Maquina='" + idMaquina + "',Tipo='" + tipo + "',Estado='" + estado + "' WHERE ID = '" + idTrabajo + "'";
            st.executeUpdate(sqlAsignar);
            
            // Si el trabajo fue asignado, actualizar el estado de la máquina
            if ("Asignado".equals(estado)) {
                String sqlActualizarMaquina = "UPDATE maquinas SET Estado = 'Ocupada' WHERE ID = " + idMaquina;
                st.executeUpdate(sqlActualizarMaquina);
            }
            
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
    
    public static ArrayList<Trabajo> obtenerTrabajosNoAsignados(Connection BD) {
        String consulta = "SELECT * FROM trabajos WHERE Estado = 'No asignado'";
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
    
    public static ArrayList<Trabajo> obtenerTodosTrabajosFinalizados(Connection BD) {
        String consulta = "SELECT * FROM trabajos WHERE Estado = 'Finalizado'";
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

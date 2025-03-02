/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionABD;

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
public class UsuarioDML {
    
    public static boolean insertar(Connection BD, String nombre, String apellidos, String email, String contrasenna) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("INSERT INTO usuarios(Nombre, Apellidos, Email, Contraseña) VALUES ('" + nombre + "', '" + apellidos + "', '" + email + "', '" + contrasenna + "')");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static ArrayList<Usuario> listar(Connection BD) {
        String consulta = "SELECT * FROM usuarios";
        ArrayList<Usuario> lista = new ArrayList<>();
        try (Statement st = BD.createStatement(); ResultSet rs = st.executeQuery(consulta)){
            
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("ID"),
                    rs.getString("Nombre"),
                    rs.getString("Apellidos"),
                    rs.getString("Email"),
                    rs.getString("Contraseña"),
                    rs.getString("Tipo"));
                lista.add(usuario);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static boolean actualizarUsuario(Connection BD, String nombre, String apellidos, String email, String contrasenna, String tipo, int idUsuario) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("UPDATE usuarios SET Nombre='" + nombre + "', Apellidos='" + apellidos + "', Email='" + email + "', Contraseña='" + contrasenna + "', Tipo='" + tipo + "' WHERE ID='" + idUsuario + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean eliminar(Connection BD, int id) {
        try {
            Statement st = BD.createStatement();
            
            st.executeUpdate("DELETE FROM usuarios WHERE ID = '" + id + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
//  Este metodo se usa para validar si existe el usuario. Si existe se obtienen todos sus atributos
    public static Usuario obtenerUsuario(Connection BD, String email, String contra) {
        String consulta = "SELECT * FROM usuarios WHERE Email = '" + email + "' AND Contraseña = '" + contra + "'";
        Usuario usuario = null;
        try (Statement st = BD.createStatement(); ResultSet rs = st.executeQuery(consulta);) {
            
            if (rs.next()) {
                usuario = new Usuario(
                    rs.getInt("ID"),
                    rs.getString("Nombre"),
                    rs.getString("Apellidos"),
                    rs.getString("Email"),
                    rs.getString("Contraseña"),
                    rs.getString("Tipo")
                );
                
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
    
    
}

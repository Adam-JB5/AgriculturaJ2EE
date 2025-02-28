/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author adamj
 */
public class Usuario {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private String contrasenna;
    private String tipo;

    public Usuario(int id, String nombre, String apellidos, String email, String contrasenna, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.contrasenna = contrasenna;
        this.tipo = tipo;
    }
    
    //Getters

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public String getTipo() {
        return tipo;
    }
    
    
    
    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
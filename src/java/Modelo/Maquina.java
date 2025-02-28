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
public class Maquina {
    private int id;
    private String tipo;
    private String estado;

    public Maquina(int id, String tipo, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
    }
    
    //Getters

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }
    
    
    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}

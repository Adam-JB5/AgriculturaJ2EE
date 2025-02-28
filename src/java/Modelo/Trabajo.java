/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author adamj
 */
public class Trabajo {
    private int id;
    private int idParcela;
    private int idMaquinista;
    private int idMaquina;
    private String tipo;
    private String estado;
    private Date fechaInicio;
    private Date fechaFin;

    public Trabajo(int id, int idParcela, int idMaquinista, int idMaquina, String tipo, String estado, Date fechaInicio, Date fechaFin) {
        this.id = id;
        this.idParcela = idParcela;
        this.idMaquinista = idMaquinista;
        this.idMaquina = idMaquina;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    //Getters

    public int getId() {
        return id;
    }

    public int getIdParcela() {
        return idParcela;
    }

    public int getIdMaquinista() {
        return idMaquinista;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }
    
    
    
    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
    }

    public void setIdMaquinista(int idMaquinista) {
        this.idMaquinista = idMaquinista;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
}

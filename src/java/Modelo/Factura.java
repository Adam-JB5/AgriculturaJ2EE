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
public class Factura {
    private int id;
    private int idTrabajo;
    private double dinero;
    private String estado;
    private Date fechaEmision;
    private Date fechaPago;

    public Factura(int id, int idTrabajo, double dinero, String estado, Date fechaEmision, Date fechaPago) {
        this.id = id;
        this.idTrabajo = idTrabajo;
        this.dinero = dinero;
        this.estado = estado;
        this.fechaEmision = fechaEmision;
        this.fechaPago = fechaPago;
    }
    
    
    //Getters

    public int getId() {
        return id;
    }

    public int getIdTrabajo() {
        return idTrabajo;
    }

    public double getDinero() {
        return dinero;
    }

    public String getEstado() {
        return estado;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public Date getFechaPago() {
        return fechaPago;
    }
    
    
    
    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setIdTrabajo(int idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
    
    
}

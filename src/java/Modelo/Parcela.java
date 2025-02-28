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
public class Parcela {
    private int id;
    private int idAgricultor;
    private String ubicacion;
    private double superficie;
    private String comunidadAutonoma;

    public Parcela(int id, int idAgricultor, String ubicacion, double superficie, String comunidadAutonoma) {
        this.id = id;
        this.idAgricultor = idAgricultor;
        this.ubicacion = ubicacion;
        this.superficie = superficie;
        this.comunidadAutonoma = comunidadAutonoma;
    }
    
    //Getters

    public int getId() {
        return id;
    }

    public int getIdAgricultor() {
        return idAgricultor;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public double getSuperficie() {
        return superficie;
    }

    public String getComunidadAutonoma() {
        return comunidadAutonoma;
    }
    
    
    
    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setIdAgricultor(int idAgricultor) {
        this.idAgricultor = idAgricultor;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public void setComunidadAutonoma(String comunidadAutonoma) {
        this.comunidadAutonoma = comunidadAutonoma;
    }
    
}

/*
 *  author:    Arturo Camargo
 *  mail:      scarturocamargo@gmail.com
 *  license:   Copyright © SC Soluciones en Computacion 2017
 */
package Modelo;

/**
 *
 * @author Arturo Camargo
 */
public class EstudioVO {
    private String clave; 
    private String nombre; 
    private String descripcion; 
    private String direccion;
    private String pagoPersona;
    private String incentivoPersona;
    private String fechaInicio;
    private String fechaFin;
    private String edadMin;
    private String edadMax;
    private String nseMin;
    private String nseMax;
    private String genero;
    private String estado;
    private String municipio;
    
    public EstudioVO(){}

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPagoPersona() {
        return pagoPersona;
    }

    public void setPagoPersona(String pagoPersona) {
        this.pagoPersona = pagoPersona;
    }

    public String getIncentivoPersona() {
        return incentivoPersona;
    }

    public void setIncentivoPersona(String incentivoPersona) {
        this.incentivoPersona = incentivoPersona;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEdadMin() {
        return edadMin;
    }

    public void setEdadMin(String edadMin) {
        this.edadMin = edadMin;
    }

    public String getEdadMax() {
        return edadMax;
    }

    public void setEdadMax(String edadMax) {
        this.edadMax = edadMax;
    }

    public String getNseMin() {
        return nseMin;
    }

    public void setNseMin(String nseMin) {
        this.nseMin = nseMin;
    }

    public String getNseMax() {
        return nseMax;
    }

    public void setNseMax(String nseMax) {
        this.nseMax = nseMax;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author ArturoLennon
 */
public class PersonaVO {
    private String ID;
    private String nombre; 
    private String primerApellido;
    private String segundoApellido;
    private String genero;
    private String fechaNacimiento;
    private String direccion;
    private ColoniaVO colonia;
    private String telCasa;
    private String telMovil;
    private String email;
    
    PersonaVO (){}
    
    //Constructor de la clase Persona.
    public PersonaVO(String ID, String nombre, String primerApellido, String segundoApellido, String genero, String fechaNacimiento, String direccion, ColoniaVO colonia, String telCasa, String telMovil, String email) {
        this.ID = ID;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.colonia = colonia;
        this.telCasa = telCasa;
        this.telMovil = telMovil;
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ColoniaVO getColonia() {
        return colonia;
    }

    public void setColonia(ColoniaVO domicilio) {
        this.colonia = domicilio;
    }

    public String getTelCasa() {
        return telCasa;
    }

    public void setTelCasa(String telCasa) {
        this.telCasa = telCasa;
    }

    public String getTelMovil() {
        return telMovil;
    }

    public void setTelMovil(String telMovil) {
        this.telMovil = telMovil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEdad() {
        return calcEdad();
    }
    
    private String calcEdad(){
        LocalDate fActual = LocalDate.now();
        LocalDate fNacimiento = LocalDate.parse(getFechaNacimiento());
        
        long edad = ChronoUnit.YEARS.between(fNacimiento, fActual);
        
        return String.valueOf(edad);
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author ArturoLennon
 */
public class ColoniaVO {

    private String ID;
    private String nombre_colonia;
    private String cod_postal;
    private String municipio;
    private String estado;
    private String nse;

    public ColoniaVO() {}

    public ColoniaVO(String ID, String nombre_colonia, String cod_postal, String municipio, String estado, String nse) {
        this.ID = ID;
        this.nombre_colonia = nombre_colonia;
        this.cod_postal = cod_postal;
        this.municipio = municipio;
        this.estado = estado;
        this.nse = nse;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombreColonia() {
        return nombre_colonia;
    }

    public void setNombreColonia(String nombre_colonia) {
        this.nombre_colonia = nombre_colonia;
    }

    public String getCodPostal() {
        return cod_postal;
    }

    public void setCodPostal(String cod_postal) {
        this.cod_postal = cod_postal;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNSE() {
        int nse;
        switch (this.nse) {
            case "E":
                nse = 1;
                break;
            case "D":
                nse = 2;
                break;
            case "D+":
                nse = 3;
                break;
            case "C":
                nse = 4;
                break;
            case "C+":
                nse = 5;
                break;
            case "B/C+":
                nse = 6;
                break;
            case "A/B":
                nse = 7;
                break;
            default:
                nse = 0;
                break;
        }
        return nse;
    }
    
    public String getNSEString(){
        return this.nse;
    }

    public void setNSE(int nse) {
        switch (nse) {
            case 1:
                this.nse = "E";
                break;
            case 2:
                this.nse = "D";
                break;
            case 3:
                this.nse = "D+";
                break;
            case 4:
                this.nse = "C";
                break;
            case 5:
                this.nse = "C+";
                break;
            case 6:
                this.nse = "B/C+";
                break;
            case 7:
                this.nse = "A/B";
                break;
            default:
                this.nse = "Indeterminado";
                break;
        }
    }
    
    @Override
    public String toString(){
        return getNombreColonia();
    }

}

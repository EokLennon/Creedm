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
public class ParticipacionVO {
    private PersonaVO persona;
    private EstudioVO estudio;
    private String fechaParticipacion;
    private String HoraParticipacion;

    public PersonaVO getPersona() {
        return persona;
    }

    public void setPersona(PersonaVO persona) {
        this.persona = persona;
    }

    public EstudioVO getEstudio() {
        return estudio;
    }

    public void setEstudio(EstudioVO estudio) {
        this.estudio = estudio;
    }

    public String getFechaParticipacion() {
        return fechaParticipacion;
    }

    public void setFechaParticipacion(String fechaParticipacion) {
        this.fechaParticipacion = fechaParticipacion;
    }

    public String getHoraParticipacion() {
        return HoraParticipacion;
    }

    public void setHoraParticipacion(String HoraParticipacion) {
        this.HoraParticipacion = HoraParticipacion;
    }
    
    
}

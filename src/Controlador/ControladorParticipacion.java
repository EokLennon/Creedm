/*
 *  author:    Arturo Camargo
 *  mail:      scarturocamargo@gmail.com
 *  license:   Copyright © SC Soluciones en Computacion 2017
 */
package Controlador;

import Modelo.ParticipacionDAO;
import Modelo.ParticipacionVO;
import Modelo.PersonaVO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Arturo Camargo
 */
public class ControladorParticipacion {
    
    private ArrayList<ParticipacionVO> listaParticipaciones;
    
    public DefaultTableModel buscarParticipacionesPersona(PersonaVO persona) {
        ParticipacionDAO pdao = new ParticipacionDAO();
        listaParticipaciones = pdao.buscarParticipacionesPersona(persona);
        return buildParticipacionesPersonaTableModel();
    }
    
    private DefaultTableModel buildParticipacionesPersonaTableModel() {
        String col[] = {"Clave de Estudio", "Nombre de Estudio", "Fecha", "Hora"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        for (int i = 0; i < listaParticipaciones.size(); i++) {
            Object[] datos = {listaParticipaciones.get(i).getEstudio().getClave(),
                listaParticipaciones.get(i).getEstudio().getNombre(),
                listaParticipaciones.get(i).getFechaParticipacion(),
                listaParticipaciones.get(i).getHoraParticipacion()
            };
            tableModel.addRow(datos);
        }

        return tableModel;
    }
}

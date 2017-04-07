/*
 *  author:    Arturo Camargo
 *  mail:      scarturocamargo@gmail.com
 *  license:   Copyright © SC Soluciones en Computacion 2017
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Arturo Camargo
 */
public class ParticipacionDAO {
    
    public ParticipacionDAO(){}
    
    public ArrayList<ParticipacionVO> buscarParticipacionesPersona(PersonaVO p){
        Connection conn = UConnection.getConnection();
        PreparedStatement st;
        ResultSet rs;
        String sql = "SELECT participacion.fecha_participacion, participacion.hora_participacion, estudio.clave, estudio.nombre, estudio.descripcion, estudio.direccion, "
                   + "estudio.pago_persona, estudio.incentivo_persona, estudio.fecha_inicio, estudio.fecha_fin, estudio.edad_min, estudio.edad_max, estudio.nse_min, "
                   + "estudio.nse_max, estudio.genero, estudio.estado, estudio.municipio "
                   + "FROM (estudio INNER JOIN participacion ON estudio.clave = participacion.estudio_clave) "
                   + "WHERE participacion.id_persona = " + p.getID();
        ArrayList<ParticipacionVO> listaParticipaciones = new ArrayList<>();
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery(sql);
            while(rs.next()){
                ParticipacionVO part = new ParticipacionVO();
                EstudioVO e = new EstudioVO();
                part.setPersona(p);
                part.setFechaParticipacion(rs.getString(1));
                part.setHoraParticipacion(rs.getString(2));
                e.setClave(rs.getString(3));
                e.setNombre(rs.getString(4));
                e.setDescripcion(rs.getString(5));
                e.setDireccion(rs.getString(6));
                e.setPagoPersona(rs.getString(7));
                e.setIncentivoPersona(rs.getString(8));
                e.setFechaInicio(rs.getString(9));
                e.setFechaFin(rs.getString(10));
                e.setEdadMin(rs.getString(11));
                e.setEdadMax(rs.getString(12));
                e.setNseMin(rs.getString(13));
                e.setNseMax(rs.getString(14));
                e.setGenero(rs.getString(15));
                e.setEstado(rs.getString(16));
                e.setMunicipio(rs.getString(17));
                part.setEstudio(e);
                listaParticipaciones.add(part);
            }
            conn.close();            
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion con la Base de Datos.", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
        return listaParticipaciones;
    }
    
}

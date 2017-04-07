/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ArturoLennon
 */
public class PersonaDAO {
    
    public PersonaDAO(){}

    public ArrayList<PersonaVO> searchPersona(String nombre, String p_apellido, String s_apellido, String genero, String tel_casa, String tel_movil, String nombre_colonia, int nse_min, int nse_max, String fNacimientoMin, String fNacimientoMax, String municipio, String estado) {
        Connection conn;
        PreparedStatement st;
        ResultSet rs;
        String sql = buildSelectQuery(nombre, p_apellido, s_apellido, genero, tel_casa, tel_movil, nombre_colonia, nse_min, nse_max, fNacimientoMin, fNacimientoMax, municipio, estado);
        ArrayList<PersonaVO> listaPersonas = new ArrayList<>();
        
        try {
            conn = UConnection.getConnection();
            st = conn.prepareStatement(sql);
            rs = st.executeQuery(sql);
            while(rs.next()){
                PersonaVO p = new PersonaVO();
                ColoniaVO c = new ColoniaVO(); 
                p.setID(rs.getString(1)); 
                p.setNombre(rs.getString(2));
                p.setPrimerApellido(rs.getString(3));
                p.setSegundoApellido(rs.getString(4));
                p.setGenero(rs.getString(5));
                p.setFechaNacimiento(rs.getString(6));
                p.setTelCasa(rs.getString(7));
                p.setTelMovil(rs.getString(8));
                p.setEmail(rs.getString(9));
                p.setDireccion(rs.getString(10)); 
                c.setID(rs.getString(11)); 
                c.setCodPostal(rs.getString(12)); 
                c.setNombreColonia(rs.getString(13));
                c.setNSE(rs.getInt(14)); 
                c.setMunicipio(rs.getString(15));
                c.setEstado(rs.getString(16));
                p.setColonia(c);
                listaPersonas.add(p);
            }
            conn.close();
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "La Busqueda no pudo ser realizada.", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
        return listaPersonas;
    }
    
    public ArrayList<PersonaVO> searchPersona() {
        Connection conn = UConnection.getConnection();
        PreparedStatement st;
        ResultSet rs;
        String sql = "SELECT persona.id_persona, persona.nombre, persona.p_apellido, persona.s_apellido, persona.genero, persona.fecha_nac, persona.tel_local, persona.tel_movil, persona.email, persona.direccion, colonia.id_colonia, colonia.cod_postal, colonia.colonia, colonia.nse, colonia.municipio, colonia.estado FROM (persona INNER JOIN colonia ON persona.id_colonia = colonia.id_colonia) ORDER BY id_persona DESC LIMIT 10";
        ArrayList<PersonaVO> listaPersonas = new ArrayList<>();
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery(sql);
            while(rs.next()){
                PersonaVO p = new PersonaVO();
                ColoniaVO c = new ColoniaVO(); 
                p.setID(rs.getString(1)); 
                p.setNombre(rs.getString(2));
                p.setPrimerApellido(rs.getString(3));
                p.setSegundoApellido(rs.getString(4));
                p.setGenero(rs.getString(5));
                p.setFechaNacimiento(rs.getString(6));
                p.setTelCasa(rs.getString(7));
                p.setTelMovil(rs.getString(8));
                p.setEmail(rs.getString(9));
                p.setDireccion(rs.getString(10)); 
                c.setID(rs.getString(11)); 
                c.setCodPostal(rs.getString(12)); 
                c.setNombreColonia(rs.getString(13));
                c.setNSE(rs.getInt(14)); 
                c.setMunicipio(rs.getString(15));
                c.setEstado(rs.getString(16));
                p.setColonia(c);
                listaPersonas.add(p);
            }
            conn.close();            
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion con la Base de Datos.", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
        return listaPersonas;
    }

    public boolean insertIntoPersona(PersonaVO persona) {
        Connection conn = UConnection.getConnection();
        PreparedStatement pst;
        boolean msj = false;
        String sql = "INSERT INTO persona(nombre, p_apellido, s_apellido, genero, fecha_nac, direccion, id_colonia, tel_local, tel_movil, email)VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, persona.getNombre());
            pst.setString(2, persona.getPrimerApellido());
            pst.setString(3, persona.getSegundoApellido());
            pst.setString(4, persona.getGenero());
            pst.setString(5, persona.getFechaNacimiento());
            pst.setString(6, persona.getDireccion());
            pst.setString(7, persona.getColonia().getID());
            pst.setString(8, persona.getTelCasa());
            pst.setString(9, persona.getTelMovil());
            pst.setString(10, persona.getEmail());
            pst.executeUpdate();
            conn.close();
            JOptionPane.showMessageDialog(null, "Registro insertado correctamente.", "Mensaje del sistema", JOptionPane.INFORMATION_MESSAGE);
            msj = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El Registro no pudo ser insertado.", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
        return msj;
    }

    public boolean deletePersona(PersonaVO persona) {
        Connection conn = UConnection.getConnection();
        PreparedStatement pst;
        boolean msj = false;
        String sql = "DELETE FROM persona WHERE id_persona= " + persona.getID();
        
        try {
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            conn.close();
            JOptionPane.showMessageDialog(null, "Registro eliminado correctamente.", "Mensaje del sistema", JOptionPane.INFORMATION_MESSAGE);
            msj = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El Registro no pudo ser eliminado.", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
        return msj;
    }

    public boolean updateDatosContactoPersona(PersonaVO persona) {
        Connection conn = UConnection.getConnection();
        PreparedStatement pst;
        boolean msj = false;
        String sql = "UPDATE persona SET id_colonia=" + persona.getColonia().getID() + ", tel_local='" + persona.getTelCasa() + "', tel_movil='" + persona.getTelMovil() + "', email='" + persona.getEmail() + "', direccion='" + persona.getDireccion() + "' WHERE id_persona=" + persona.getID();
        
        try {
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            conn.close();
            JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.", "Mensaje del sistema", JOptionPane.INFORMATION_MESSAGE);
            msj = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El Registro no pudo ser actualizado.", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
        return msj;
    }
    
    public void updateDatosGeneralesPersona(PersonaVO persona) {
        Connection conn = UConnection.getConnection();
        PreparedStatement st;
        String sql = "UPDATE persona SET nombre='" + persona.getNombre() + "', p_apellido='" + persona.getPrimerApellido() + "', s_apellido='" + persona.getSegundoApellido() + "', genero=" + persona.getGenero() + ", fecha_nac='" + persona.getFechaNacimiento() + "', id_colonia=" + persona.getColonia().getID() + ", tel_local='" + persona.getTelCasa() + "', tel_movil='" + persona.getTelMovil() + "', email='" + persona.getEmail() + "', direccion='" + persona.getDireccion() + "' WHERE id_persona=" + persona.getID();
        
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
            conn.close();
            JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.", "Mensaje del sistema", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El Registro no pudo ser actualizado.", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String buildSelectQuery(String nombre, String p_apellido, String s_apellido, String genero, String tel_casa, String tel_movil, String nombre_colonia, int nse_min, int nse_max, String fNacimientoMin, String fNacimientoMax, String municipio, String estado){
        String sql = "SELECT persona.id_persona, persona.nombre, persona.p_apellido, persona.s_apellido, persona.genero, persona.fecha_nac, persona.tel_local, persona.tel_movil, persona.email, persona.direccion, colonia.id_colonia, colonia.cod_postal, colonia.colonia, colonia.nse, colonia.municipio, colonia.estado FROM persona, colonia WHERE persona.id_colonia = colonia.id_colonia";
        if (!nombre.equals("")){
            sql = sql + " AND persona.nombre LIKE '%" + nombre + "%'"; 
        }
        if (!p_apellido.equals("")){
            sql = sql + " AND persona.p_apellido LIKE '%" + p_apellido + "%'";
        }
        if (!s_apellido.equals("")){
            sql = sql + " AND persona.s_apellido LIKE '%" + s_apellido + "%'";
        }
        if (!genero.equals("-1")){
            sql = sql + " AND persona.genero LIKE '%" + genero + "%'";
        }
        if (!tel_casa.equals("")){
            sql = sql + " AND persona.tel_local LIKE '%" + tel_casa + "%'";
        }
        if (!tel_movil.equals("")){
            sql = sql + " AND persona.tel_movil LIKE '%" + tel_movil + "%'";
        }
        if (!nombre_colonia.equals("")){
            sql = sql + " AND colonia.colonia LIKE '%" + nombre_colonia + "%'";
        }
        if ((nse_min > 0) && (nse_max > 0)){
            sql = sql + " AND (colonia.nse BETWEEN " + nse_min + " AND " + nse_max +")";
        }
        if ((nse_min == 0) && (nse_max > 0)){
            sql = sql + " AND colonia.nse =" + nse_max;
        }
        if ((nse_min > 0) && (nse_max == 0)){
            sql = sql + " AND colonia.nse =" + nse_min;
        }
        if ((!fNacimientoMin.equals("")) && (!fNacimientoMax.equals(""))){
            sql = sql + " AND persona.fecha_nac BETWEEN '" + fNacimientoMin + "' AND '" + fNacimientoMax + "'";
        }
        if ((fNacimientoMin.equals("")) && (!fNacimientoMax.equals(""))){
            sql = sql + " AND persona.fecha_nac <= '" + fNacimientoMax + "'";
        }
        if ((!fNacimientoMin.equals("")) && (fNacimientoMax.equals(""))){
            sql = sql + " AND persona.fecha_nac >= '" + fNacimientoMin + "'";
        }
        if (!municipio.equals("")){
            sql = sql + " AND colonia.municipio LIKE '%" + municipio + "%'";
        }
        if (!estado.equals("-1")){
            sql = sql + " AND colonia.estado LIKE '%" + estado + "%'";
        }
        return sql;
    }
}

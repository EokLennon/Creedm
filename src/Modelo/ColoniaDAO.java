/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ArturoLennon
 */
public class ColoniaDAO {
   
    public ColoniaDAO(){}

    public ArrayList<ColoniaVO> searchColonia(String cod_postal) {
        ArrayList<ColoniaVO> listaColonias = new ArrayList<>();
        Connection conn = UConnection.getConnection();
        PreparedStatement st;
        ResultSet rs;
        String sql = "SELECT colonia.id_colonia, colonia.cod_postal, colonia.colonia, colonia.nse, colonia.municipio, colonia.estado FROM colonia WHERE cod_postal = '" + cod_postal + "'";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery(sql);
            while(rs.next()){
                ColoniaVO c = new ColoniaVO();
                c.setID(rs.getString(1));
                c.setCodPostal(rs.getString(2));
                c.setNombreColonia(rs.getString(3));
                c.setNSE(rs.getInt(4));
                c.setMunicipio(rs.getString(5));
                c.setEstado(rs.getString(6));
                listaColonias.add(c);
            }
            conn.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "La Busqueda no pudo ser realizada.", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
        return listaColonias;
    }
    
}

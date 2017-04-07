/*
 *  author:    Arturo Camargo
 *  mail:      scarturocamargo@gmail.com
 *  license:   Copyright © SC Soluciones en Computacion 2017
 */
package Controlador;

import EstrategiasValidacion.FabricaEstrategiaValidacion;
import EstrategiasValidacion.IEstrategiaValidacion;
import EstrategiasValidacion.IFabricaEstrategiaValidacion;
import Modelo.ColoniaDAO;
import Modelo.ColoniaVO;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Arturo Camargo
 */
public class ControladorColonia {
    
    ArrayList<ColoniaVO> listaColonias;
    
    private IFabricaEstrategiaValidacion fabrica;
    private IEstrategiaValidacion validador;
    
    public ControladorColonia() {
        fabrica = FabricaEstrategiaValidacion.get();
    }
    
    public void getListaColonias(String cod_postal, JComboBox comboColonia, JTextField txtNSE, JTextField txtMunicipio, JRadioButton rbtnCd_Mex, JRadioButton rbtnEdo_Mex){
        if(fabrica.getEstrategia("numero").validar(cod_postal)){
            ColoniaDAO cdao = new ColoniaDAO();
            listaColonias = cdao.searchColonia(cod_postal);
            buildColoniaCombo(comboColonia, txtNSE, txtMunicipio, rbtnCd_Mex, rbtnEdo_Mex);
        }
    }
    
    private void buildColoniaCombo(JComboBox comboColonia, JTextField txtNSE, JTextField txtMunicipio, JRadioButton rbtnCd_Mex, JRadioButton rbtnEdo_Mex){
        comboColonia.removeAllItems();
        txtNSE.setText("");
        txtMunicipio.setText("");
        rbtnCd_Mex.setEnabled(true);
        rbtnEdo_Mex.setEnabled(true);
        for(ColoniaVO c : listaColonias){
            comboColonia.addItem(c);
        }
    }
    
    public void buildColoniaGUI(Object objColonia, JTextField txtNSE, JTextField txtMunicipio, JRadioButton rbtnCd_Mex, JRadioButton rbtnEdo_Mex){
        ColoniaVO colonia = (ColoniaVO) objColonia;
        txtNSE.setText(colonia.getNSEString());
        txtMunicipio.setText(colonia.getMunicipio());
        rbtnCd_Mex.setEnabled(true);
        rbtnEdo_Mex.setEnabled(true);
        if(colonia.getEstado().equals("0")) {
            rbtnCd_Mex.setSelected(true);
            rbtnEdo_Mex.setEnabled(false);
        } else {
            rbtnCd_Mex.setEnabled(false);
            rbtnEdo_Mex.setSelected(true);
        }
    }
}

/*
 *  author:    Arturo Camargo
 *  mail:      scarturocamargo@gmail.com
 *  license:   Copyright © SC Soluciones en Computacion 2017
 */
package Controlador;

import EstrategiasValidacion.*;
import Modelo.ColoniaVO;
import Modelo.PersonaDAO;
import Modelo.PersonaVO;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Arturo Camargo
 */
public class ControladorPersona {

    private ArrayList<PersonaVO> listaPersonas;

    private IFabricaEstrategiaValidacion fabricaEstVal;
    private IEstrategiaValidacion validador;

    public ControladorPersona() {
        fabricaEstVal = FabricaEstrategiaValidacion.get();
    }

    public DefaultTableModel seleccionarPersonas(String nombre, String pApellido, String sApellido, String genero, String edadMin, String edadMax, String nseMin, String nseMax, String colonia, String municipio, String estado, String telFijo, String telMovil) {
        boolean vNombre, vPApellido, vSApellido, vEdadMin, vEdadMax, vTelCasa, vTelMovil, vColonia, vMunicipio;
        vNombre = revisarEntrada("nombre", false, nombre);
        vPApellido = revisarEntrada("nombre", false, pApellido);
        vSApellido = revisarEntrada("nombre", false, sApellido);
        vEdadMin = revisarEntrada("edad", false, edadMin);
        vEdadMax = revisarEntrada("edad", false, edadMax);
        vTelCasa = revisarEntrada("numero", false, telFijo);
        vTelMovil = revisarEntrada("numero", false, telMovil);
        vColonia = revisarEntrada("nombre", false, colonia);
        vMunicipio = revisarEntrada("nombre", false, municipio);

        if (vNombre && vPApellido && vSApellido && vEdadMin && vEdadMax && vTelMovil && vTelCasa && vColonia && vMunicipio) {
            int edadMinN;
            int edadMaxN;
            if (edadMin.isEmpty()){
                edadMinN = -1;
            }
            else{
                edadMinN = Integer.parseInt(edadMin);
            }
            if (edadMax.isEmpty()){
                edadMaxN = -1;
            }
            else{
                edadMaxN = Integer.parseInt(edadMax);
            }
            
            if ((edadMinN > -1)&&(edadMaxN > -1)&&(edadMinN > edadMaxN)) {
                int aux = edadMaxN;
                String auxs = edadMax;
                edadMaxN = edadMinN;
                edadMax = edadMin;
                edadMinN = aux;
                edadMin = auxs;
            }
            int nseMinN = getNseNumber(nseMin);
            int nseMaxN = getNseNumber(nseMax);
            if (nseMinN > nseMaxN) {
                int aux = nseMaxN;
                nseMaxN = nseMinN;
                nseMinN = aux;
            }

            PersonaDAO pdao = new PersonaDAO();
            listaPersonas = pdao.searchPersona(nombre, pApellido, sApellido, genero, telFijo, telMovil, colonia, nseMinN, nseMaxN, getFechaMin(edadMaxN), getFechaMax(edadMinN), municipio, estado);
        } else {
            JOptionPane.showMessageDialog(null, "Ciertos campos no respetan el formato.", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }

        return buildPersonaTableModel();
    }

    public void insertarPersona(String nombre, String p_apellido, String s_apellido, String genero, String fecha_nac, String direccion, Object objColonia, String tel_casa, String tel_movil, String email) {
        ColoniaVO colonia = (ColoniaVO) objColonia;
        boolean vNombre, vPApellido, vSApellido, vDireccion, vTelCasa, vTelMovil, vEmail;

        vNombre = revisarEntrada("nombre", true, nombre);
        vPApellido = revisarEntrada("nombre", true, p_apellido);
        vSApellido = revisarEntrada("nombre", true, s_apellido);
        vDireccion = revisarEntrada("direccion", true, direccion);
        vTelCasa = revisarEntrada("numero", false, tel_casa);
        vTelMovil = revisarEntrada("numero", true, tel_movil);
        vEmail = revisarEntrada("email", false, email);

        if (vNombre && vPApellido && vSApellido && !genero.equals("-1") && vDireccion && vTelMovil && vTelCasa && vEmail) {
            PersonaDAO pdao = new PersonaDAO();
            PersonaVO p = new PersonaVO("", nombre, p_apellido, s_apellido, genero, fecha_nac, direccion, colonia, tel_casa, tel_movil, email);
            pdao.insertIntoPersona(p);
        } else {
            JOptionPane.showMessageDialog(null, "Ciertos campos no respetan el formato o estan vacios.", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificarPersonaTotal(String ID, String nombre, String p_apellido, String s_apellido, String genero, String fecha_nac, String direccion, Object objColonia, String tel_casa, String tel_movil, String email) {
        ColoniaVO colonia = (ColoniaVO) objColonia;
        boolean vNombre, vPApellido, vSApellido, vDireccion, vTelCasa, vTelMovil, vEmail;

        vNombre = revisarEntrada("nombre", true, nombre);
        vPApellido = revisarEntrada("nombre", true, p_apellido);
        vSApellido = revisarEntrada("nombre", true, s_apellido);
        vDireccion = revisarEntrada("direccion", true, direccion);
        vTelCasa = revisarEntrada("numero", false, tel_casa);
        vTelMovil = revisarEntrada("numero", true, tel_movil);
        vEmail = revisarEntrada("email", false, email);

        if (vNombre && vPApellido && vSApellido && !genero.equals("-1") && vDireccion && vTelMovil && vTelCasa && vEmail) {
            PersonaDAO pdao = new PersonaDAO();
            PersonaVO p = buscarPersonaEnMemoria(ID);
            p.setNombre(nombre);
            p.setPrimerApellido(p_apellido);
            p.setSegundoApellido(s_apellido);
            p.setGenero(genero);
            p.setFechaNacimiento(fecha_nac);
            p.setDireccion(direccion);
            p.setColonia(colonia);
            p.setTelCasa(tel_casa);
            p.setTelMovil(tel_movil);
            p.setEmail(email);
            pdao.updateDatosGeneralesPersona(p);
        } else {
            JOptionPane.showMessageDialog(null, "Ciertos campos no respetan el formato o estan vacios.", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificarPersonaContacto(PersonaVO p, String ID, String direccion, Object objColonia, String tel_casa, String tel_movil, String email) {
        ColoniaVO colonia = (ColoniaVO) objColonia;
        boolean vDireccion, vTelCasa, vTelMovil, vEmail;

        vDireccion = revisarEntrada("direccion", true, direccion);
        vTelCasa = revisarEntrada("numero", false, tel_casa);
        vTelMovil = revisarEntrada("numero", true, tel_movil);
        vEmail = revisarEntrada("email", false, email);

        if (vDireccion && vTelMovil && vTelCasa && vEmail) {
            PersonaDAO pdao = new PersonaDAO();
            p.setDireccion(direccion);
            p.setColonia(colonia);
            p.setTelCasa(tel_casa);
            p.setTelMovil(tel_movil);
            p.setEmail(email);
            pdao.updateDatosContactoPersona(p);
        } else {
            JOptionPane.showMessageDialog(null, "Ciertos campos no respetan el formato o estan vacios.", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarPersona(String ID) {
        PersonaDAO pdao = new PersonaDAO();
        PersonaVO p = buscarPersonaEnMemoria(ID);
        pdao.deletePersona(p);
    }

    public PersonaVO consultarPersona(String ID) {
        return buscarPersonaEnMemoria(ID);
    }

    public void mostrarPersona(String ID, JTextField txtNombre, JTextField txtPApellido, JTextField txtSApellido, JRadioButton rbtnMasculino, JRadioButton rbtnFemenino, JTextField txtFechaNac, JTextField txtEdad, JTextField txtDireccion, JTextField txtCodPostal, JComboBox comboColonia, JTextField txtNSE, JTextField txtMunicipio, JRadioButton rbtnCdMex, JRadioButton rbtnEdoMex, JTextField txtTelFijo, JTextField txtTelMovil, JTextField txtMail) {
        PersonaVO p = buscarPersonaEnMemoria(ID);
        txtNombre.setText(p.getNombre());
        txtPApellido.setText(p.getPrimerApellido());
        txtSApellido.setText(p.getSegundoApellido());
        if (p.getGenero().equals("0")) {
            rbtnMasculino.setSelected(true);
        } else {
            rbtnFemenino.setSelected(true);
        }
        txtFechaNac.setText(p.getFechaNacimiento());
        txtEdad.setText(p.getEdad());
        txtDireccion.setText(p.getDireccion());
        txtCodPostal.setText(p.getColonia().getCodPostal());
        comboColonia.removeAllItems();
        comboColonia.addItem(p.getColonia());
        comboColonia.setSelectedIndex(0);
        txtNSE.setText(p.getColonia().getNSEString());
        txtMunicipio.setText(p.getColonia().getMunicipio());
        if (p.getColonia().getEstado().equals("0")) {
            rbtnCdMex.setSelected(true);
        } else {
            rbtnEdoMex.setSelected(true);
        }
        txtTelFijo.setText(p.getTelCasa());
        txtTelMovil.setText(p.getTelMovil());
        txtMail.setText(p.getEmail());
    }

    public void mostrarPersona(PersonaVO p, JTextField txtNombre, JTextField txtPApellido, JTextField txtSApellido, JRadioButton rbtnMasculino, JRadioButton rbtnFemenino, JTextField txtFechaNac, JTextField txtEdad, JTextField txtDireccion, JTextField txtCodPostal, JComboBox comboColonia, JTextField txtNSE, JTextField txtMunicipio, JRadioButton rbtnCdMex, JRadioButton rbtnEdoMex, JTextField txtTelFijo, JTextField txtTelMovil, JTextField txtMail) {
        txtNombre.setText(p.getNombre());
        txtPApellido.setText(p.getPrimerApellido());
        txtSApellido.setText(p.getSegundoApellido());
        if (p.getGenero().equals("0")) {
            rbtnMasculino.setSelected(true);
        } else {
            rbtnFemenino.setSelected(true);
        }
        txtFechaNac.setText(p.getFechaNacimiento());
        txtEdad.setText(p.getEdad());
        txtDireccion.setText(p.getDireccion());
        txtCodPostal.setText(p.getColonia().getCodPostal());
        comboColonia.removeAllItems();
        comboColonia.addItem(p.getColonia());
        comboColonia.setSelectedIndex(0);
        txtNSE.setText(p.getColonia().getNSEString());
        txtMunicipio.setText(p.getColonia().getMunicipio());
        if (p.getColonia().getEstado().equals("0")) {
            rbtnCdMex.setSelected(true);
        } else {
            rbtnEdoMex.setSelected(true);
        }
        txtTelFijo.setText(p.getTelCasa());
        txtTelMovil.setText(p.getTelMovil());
        txtMail.setText(p.getEmail());
    }

    public DefaultTableModel seleccionarUltimasPersonas() {
        PersonaDAO pdao = new PersonaDAO();
        listaPersonas = pdao.searchPersona();
        return buildPersonaTableModel();
    }

    private boolean revisarEntrada(String func, boolean strongCheck, String cadena) {
        if (strongCheck) {
            if (!cadena.isEmpty()) {
                validador = fabricaEstVal.getEstrategia(func);
                return validador.validar(cadena);
            } else {
                return false;
            }
        } else {
            if (!cadena.isEmpty()) {
                validador = fabricaEstVal.getEstrategia(func);
                return validador.validar(cadena);
            } else {
                return true;
            }
        }
    }

    private DefaultTableModel buildPersonaTableModel() {
        String col[] = {"ID", "Nombre", "Apellido 1", "Apellido 2", "Edad", "Colonia", "NSE", "Tel. Local", "Tel. Movil", "Email"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        for (int i = 0; i < listaPersonas.size(); i++) {
            Object[] datos = {listaPersonas.get(i).getID(),
                listaPersonas.get(i).getNombre(),
                listaPersonas.get(i).getPrimerApellido(),
                listaPersonas.get(i).getSegundoApellido(),
                listaPersonas.get(i).getEdad(),
                listaPersonas.get(i).getColonia().getNombreColonia(),
                listaPersonas.get(i).getColonia().getNSEString(),
                listaPersonas.get(i).getTelCasa(),
                listaPersonas.get(i).getTelMovil(),
                listaPersonas.get(i).getEmail()
            };
            tableModel.addRow(datos);
        }

        return tableModel;
    }

    private PersonaVO buscarPersonaEnMemoria(String ID) {
        int i;
        for (i = 0; i < listaPersonas.size(); i++) {
            if (listaPersonas.get(i).getID().equals(ID)) {
                break;
            }
        }
        PersonaVO p = listaPersonas.get(i);
        return p;
    }

    public String setEdadGUI(String fec_nac) {
        if (fabricaEstVal.getEstrategia("fecha").validar(fec_nac)) {
            LocalDate fActual = LocalDate.now();
            LocalDate fNacimiento = LocalDate.parse(fec_nac);

            long edad = ChronoUnit.YEARS.between(fNacimiento, fActual);

            return String.valueOf(edad);
        } else {
            JOptionPane.showMessageDialog(null, "Formato de fecha no valido", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }

    private String getFechaMin(int edad) {
        if (edad > 0) {
            LocalDate today = LocalDate.now();
            LocalDate fechaMin = today.minusYears(edad+1);
            fechaMin = fechaMin.plusDays(1);
            return fechaMin.toString();
        } else {
            return "";
        }
    }

    private String getFechaMax(int edad) {
        if (edad > 0) {
            LocalDate today = LocalDate.now();
            LocalDate fechaMax = today.minusYears(edad);
            return fechaMax.toString();
        } else {
            return "";
        }
    }

    private int getNseNumber(String nse) {
        int nseN;
        switch (nse) {
            case "E":
                nseN = 1;
                break;
            case "D":
                nseN = 2;
                break;
            case "D+":
                nseN = 3;
                break;
            case "C":
                nseN = 4;
                break;
            case "C+":
                nseN = 5;
                break;
            case "B/C+":
                nseN = 6;
                break;
            case "A/B":
                nseN = 7;
                break;
            default:
                nseN = 0;
                break;
        }
        return nseN;
    }
}

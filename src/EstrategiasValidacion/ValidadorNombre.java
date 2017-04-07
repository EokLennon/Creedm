/*
 *  author:    Arturo Camargo
 *  mail:      scarturocamargo@gmail.com
 *  license:   Copyright © SC Soluciones en Computacion 2017
 */
package EstrategiasValidacion;

import java.util.regex.*;

/**
 *
 * @author Arturo Camargo
 */
public class ValidadorNombre implements IEstrategiaValidacion {

    public ValidadorNombre() {
    }

    @Override
    public boolean validar(String cadena) {
        String patron = "^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\\s]*)+$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }
}

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
public class ValidacionDireccion implements IEstrategiaValidacion {

    public ValidacionDireccion() {
    }

    @Override
    public boolean validar(String cadena) {
        String patron = "[a-zA-ZñÑ0-9\\s\\.\\-\\#]+";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }

}

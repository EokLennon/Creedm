/*
 *  author:    Arturo Camargo
 *  mail:      scarturocamargo@gmail.com
 *  license:   Copyright © SC Soluciones en Computacion 2017
 */
package EstrategiasValidacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Arturo Camargo
 */
public class ValidadorEdad implements IEstrategiaValidacion {

    public ValidadorEdad() {}

    @Override
    public boolean validar(String cadena) {
        String patron = "[\\d]{1,2}";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }
}

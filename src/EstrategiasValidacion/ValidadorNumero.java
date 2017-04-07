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
public class ValidadorNumero implements IEstrategiaValidacion {

    public ValidadorNumero() {}

    @Override
    public boolean validar(String cadena) {
        String patron = "[\\d]{5,10}";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }
    
}

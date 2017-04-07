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
public class ValidadorMail implements IEstrategiaValidacion {

    public ValidadorMail() {
    }

    @Override
    public boolean validar(String cadena) {
        String patron = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }
}

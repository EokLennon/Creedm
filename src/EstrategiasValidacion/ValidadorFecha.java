/*
 *  author:    Arturo Camargo
 *  mail:      scarturocamargo@gmail.com
 *  license:   Copyright © SC Soluciones en Computacion 2017
 */
package EstrategiasValidacion;

import java.text.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Arturo Camargo
 */
public class ValidadorFecha implements IEstrategiaValidacion {

    public ValidadorFecha() {
    }

    @Override
    public boolean validar(String cadena) {
        String patron = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(cadena);
        if (matcher.matches()) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            try {
                df.parse(cadena);
                return true;
            } catch (ParseException ex) {
                return false;
            }
        } else {
            return false;
        }
    }
}

/*
 *  author:    Arturo Camargo
 *  mail:      scarturocamargo@gmail.com
 *  license:   Copyright © SC Soluciones en Computacion 2017
 */
package EstrategiasValidacion;

/**
 *
 * @author Arturo Camargo
 */
public class FabricaEstrategiaValidacion implements IFabricaEstrategiaValidacion {

    static private IFabricaEstrategiaValidacion validador = null;
    
    private FabricaEstrategiaValidacion() {}
    
    public static IFabricaEstrategiaValidacion get(){
        if (validador == null){
            validador = new FabricaEstrategiaValidacion();
        }
        return validador;
    }

    @Override
    public IEstrategiaValidacion getEstrategia(String cadena) {
        switch (cadena) {
            case "email":
                return new ValidadorMail();
            case "nombre":
                return new ValidadorNombre();
            case "numero":
                return new ValidadorNumero();
            case "edad":
                return new ValidadorEdad();
            case "fecha":
                return new ValidadorFecha();
            case "direccion":
                return new ValidacionDireccion();
            default:
                return null;
        }
    }

}

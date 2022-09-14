
package edu.eci.arem.parcialcalculadora.ResJson;

import edu.eci.arem.parcialcalculadora.CalcImp.Calculadora;

/**
 *
 * @author arep02
 */
public class RespuestaJson {
    public static String generarJsonString(String op, String num){
        float res = Calculadora.calcular(op,num);
        return "{\"operacion\": \""+op+"\", \"numero\": \""+num+"\", \"respuesta\": \""+res+"\" }";
    }
}

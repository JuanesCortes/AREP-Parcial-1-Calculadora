
package edu.eci.arem.parcialcalculadora;

/**
 *
 * @author arep02
 */
public class ParcialCalculadoraException extends Exception{
    public static final String  HTTP_E_500 = "HTTP/1.1 500 NO_OK\r\n"
                        + "Content-Type: text/Json\r\n";
    
    public ParcialCalculadoraException(String msg){
        super(msg);
    }
}

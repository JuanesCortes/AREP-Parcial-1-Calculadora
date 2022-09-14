package edu.eci.arem.parcialcalculadora.CalcImp;

/**
 *
 * @author arep02
 */
public class Calculadora {

    public static float calcular(String op, String num) {
        float res = 0;
        switch (op) {
            case "tan":
                res = (float) Math.tan(Float.valueOf(num));
                break;
            case "sin":
                res = (float) Math.sin(Float.valueOf(num));
                break;
            case "cos":
                res = (float) Math.cos(Float.valueOf(num));
                break;
        }
        return res;
    }
}

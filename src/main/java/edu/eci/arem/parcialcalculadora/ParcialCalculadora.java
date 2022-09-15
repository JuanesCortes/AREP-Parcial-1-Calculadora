package edu.eci.arem.parcialcalculadora;

/**
 *
 * @author arep02
 */
import edu.eci.arem.parcialcalculadora.ResJson.RespuestaJson;
import java.net.*;
import java.io.*;

public class ParcialCalculadora {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        boolean ejecutando = true;
        while (ejecutando) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine, entrada;
            entrada = "";
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibí: " + inputLine);
                if (inputLine.contains("GET")) {
                    entrada = inputLine.split(" ")[1];
                }
                if (!in.ready()) {
                    break;
                }
            }
            if (entrada.equals("/")) {
                outputLine
                        = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/Json\r\n"
                        + "\r\n";
                out.println(outputLine);
                out.close();
                in.close();
                clientSocket.close();
            } else {
                try {
                    if (validar(entrada)) {
                        outputLine
                                = "HTTP/1.1 200 OK\r\n"
                                + "Content-Type: text/Json\r\n"
                                + "\r\n"
                                + RespuestaJson.generarJsonString(getOp(entrada), getNum(entrada));
                        out.println(outputLine);
                        out.close();
                        in.close();
                        clientSocket.close();
                    } else {
                        throw new ParcialCalculadoraException(ParcialCalculadoraException.HTTP_E_500);
                    }
                } catch (ParcialCalculadoraException ex) {
                    outputLine = ex.getMessage();
                    out.println(outputLine);
                    out.close();
                    in.close();
                    clientSocket.close();
                }
            }

        }

        serverSocket.close();
    }

    public static boolean validar(String ent) {
        boolean valid = false;
        if (ent.contains("cos?val=") || ent.contains("tan?val=") || ent.contains("sin?val=") || ent.contains("qck?val=")) {
            valid = true;
        }
        return valid;
    }

    public static String getNum(String ent) throws ParcialCalculadoraException {
        try {
            return ent.split("=")[1];
        } catch (Exception ex) {
            throw new ParcialCalculadoraException(ParcialCalculadoraException.HTTP_E_500);
        }
    }

    public static String getOp(String ent) throws ParcialCalculadoraException {
        try {
            return ent.substring(1, 4);
        } catch (Exception ex) {
            throw new ParcialCalculadoraException(ParcialCalculadoraException.HTTP_E_500);
        }
    }

    public static String operar(String op, String num) {
        return op + num;
    }
}

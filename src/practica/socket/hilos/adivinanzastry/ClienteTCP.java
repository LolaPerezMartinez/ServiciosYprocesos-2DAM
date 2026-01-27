package practica.socket.hilos.adivinanzastry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP {
    private String serverIP;
    private int serverPort;
    private int intentos;

    public ClienteTCP(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void iniciarCliente() {
        try (Socket socket = new Socket(serverIP, serverPort);
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            for (int i = 1; i <= 1000; i++) {
                String datoEnviado = Integer.toString(i);
                pw.println(datoEnviado);
                String datoRecibido = br.readLine();
                intentos++;
                if ("Acertado".equals(datoRecibido)) {
                    System.out.printf("(Cliente) Dato acertado después de %d intentos%n", intentos);
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("No se puede encontrar el host: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ClienteTCP cliente = new ClienteTCP("localhost", 8081);
        cliente.iniciarCliente();
    }
}
package practica.socket.hilos.adivinanzastry;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorSocketServer implements Runnable {
    private Socket socket;
    private JuegoAdivinaNumero juego;
    private static AtomicLong peticionesAlServidor = new AtomicLong(0);

    public GestorSocketServer(Socket socket) {
        this.socket = socket;
        juego = new JuegoAdivinaNumero();
    }

    @Override
    public void run() {
        try (InputStream is = socket.getInputStream();
             OutputStream os = socket.getOutputStream();
             PrintWriter pw = new PrintWriter(os, true);
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)) {

            String datoLeido;
            while ((datoLeido = br.readLine()) != null) {
                peticionesAlServidor.getAndIncrement();
                String respuesta = juego.verificarIntento(datoLeido);
                pw.println(respuesta);
                if ("Acertado".equals(respuesta)) {
                    System.out.printf("(Gestor sockets) %d sockets aceptados y %d peticiones recibidas%n",
                            ServidorTCP.getSocketAceptados(), peticionesAlServidor.get());
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("(Gestor sockets) Error en la comunicación con el cliente: " + e.getMessage());
        }
    }
}
package practica.socket.hilos.adivinanzas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * La clase ClienteTCP implementa un cliente TCP básico.
 * Esta clase es responsable de establecer una conexión con el servidor,
 * enviar solicitudes y recibir respuestas.
 */
public class ClienteTCP {
	private String serverIP;
	private int serverPort;
	private Socket socket;
	private OutputStream os;
	private InputStream is;
	private PrintWriter pw;
	private InputStreamReader isr;
	private BufferedReader br;
	private int intentos;
	
	
	/**
     * Constructor de ClienteTCP.
     * 
     * @param serverIP La dirección IP del servidor al que se conectará el cliente.
     * @param serverPort El puerto del servidor al que se conectará el cliente.
     * @throws UnknownHostException Si la dirección IP del host no se puede determinar.
     * @throws IOException Si ocurre un error de E/S al abrir la conexión.
     */
	public ClienteTCP(String serverIP, int serverPort) throws UnknownHostException, IOException {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		socket = new Socket(serverIP, serverPort);
		is = socket.getInputStream();
		os = socket.getOutputStream();
		pw = new PrintWriter(os, true);
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
	}
	
	 /**
     * Punto de entrada principal del programa cliente.
     * Este método inicia múltiples instancias del cliente para enviar solicitudes
     * al servidor y procesar las respuestas.
     * 
     * @param args Argumentos de línea de comandos (no utilizados).
     */
	public static void main(String[] args) {
		ClienteTCP cliente;
		
		for (int lanzamientoClientes = 0; lanzamientoClientes < 10; lanzamientoClientes++) {
			try {
				cliente  = new ClienteTCP("localhost", 4000);
				for (int i = 1; i <= 1000; i++) {
					String datoEnviado = Integer.toString(i);
					cliente.pw.println(datoEnviado);
					String datoRecibido = cliente.br.readLine();
					cliente.intentos++;
					if("🎉¡Has acertado!🎉".equals(datoRecibido)) {
						System.out.printf("(Cliente) Dato acertado después de %d intentos%n", cliente.intentos);
						break;
					}
					
				}
				// Cierre de los streams del cliente
                cliente.pw.close();
                cliente.br.close();
                cliente.isr.close();
                cliente.os.close();
                cliente.is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}

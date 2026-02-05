package practica.socket.hilos.comandosdiferentes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSocketComandos {
	private String address;
	private int port;
	
	
	public ClienteSocketComandos(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void gestionarComandos() {
		try (Socket socket = new Socket(address, port);
			 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
			
			for (int i = 1; i <= 3; i++) {
				System.out.printf("%n--INTENTO %d--", i);
				System.out.printf("%nIntroduzca un comando: ");
				
				String mensajeUsuario = reader.readLine();
				System.out.printf("%nMensaje enviado: %s%n", mensajeUsuario);
				pw.println(mensajeUsuario);
				
				String mensajeRecibido = br.readLine();
				System.out.printf("%nMensaje recibido: %s%n", mensajeRecibido);
				
				if("FIN".equals(mensajeRecibido)) {
					break;
				}
			}
			System.out.printf("%nFinal de intentos.");
			Thread.sleep(500);
			
		} catch (InterruptedException e) {
			System.out.printf("%n[Servidor] Hilo interrumpido | Error: %s%n", e.getMessage());
		} catch (IOException e) {
			System.out.printf("%n[Servidor] Problemas al conectar con el servidor | Error: %s%n", e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		ClienteSocketComandos c1  = new ClienteSocketComandos("localhost", 4002);
		c1.gestionarComandos();
	}
}

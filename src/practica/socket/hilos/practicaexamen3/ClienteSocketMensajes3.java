package practica.socket.hilos.practicaexamen3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSocketMensajes3 {
	private String address;
	private int port;
	
	
	public ClienteSocketMensajes3(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void generadorMensajes() {
		//pw.println enviarrr
		
		try (Socket socket = new Socket(address, port);
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
			
			for (int i = 1; i <= 4; i++) {
				System.out.printf("%n--INTENTO %d--", i);
				System.out.printf("%nIntroduzca un comando: ");
				
				String mensajeUsuario = reader.readLine();
				System.out.printf("%nMensaje enviado: %s%n", mensajeUsuario);
				
				pw.println(mensajeUsuario);
				String mensajeRecibido = br.readLine();
				System.out.printf("%nMensaje recibido: %s%n", mensajeRecibido);
				
				if("#Finalizado#".equals(mensajeRecibido)) {
					break;
				}
			}
			Thread.sleep(500);
			
		} catch (InterruptedException e) {
			System.out.printf("[Cliente] Hilo interrumpido | Error: %s%n", e.getMessage());
		} catch (IOException e) {
			System.out.printf("[Gestor de servidor] Problemas al conectar con el servidor | Error: %s%n", e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		ClienteSocketMensajes3 c1 = new ClienteSocketMensajes3("localhost", 5050);
		c1.generadorMensajes();
	}
	
	

}

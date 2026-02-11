package practica.socket.hilos.practicaexamen4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSocketMensajes4 {
	private String address;
	private int port;
	
	
	public ClienteSocketMensajes4(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	
	public void generarmensajes() {
		try (Socket socket = new Socket(address, port);
			 PrintWriter pw  = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
			
			//printlnnnnn enviar
			
			for (int i = 1; i <= 3; i++) {
				System.out.printf("%n--INTENTO %d--%n", i);
				System.out.printf("%nIntroduzca un comando: ");
				
				String mensajeUsuario = reader.readLine();
				pw.println(mensajeUsuario);
				System.out.printf("%nMensaje enviado: %s%n", mensajeUsuario);
				
				String respuesta = br.readLine();
				System.out.printf("%nRespuesta recibida: %s%n", respuesta);
				
				if("#Finalizado#".equals(respuesta)) {
					break;
				}
			}
			Thread.sleep(500);
			
		} catch (InterruptedException e) {
			System.out.printf("%n[Cliente] Hilo interrumpido | Error: %S%n", e.getMessage());
		} catch (IOException e) {
			System.out.printf("%n[Cliente] Problemas al conectar con el servidor | Error: %S%n", e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		ClienteSocketMensajes4 c1 = new ClienteSocketMensajes4("localhost", 5053);
		c1.generarmensajes();
	}
	
	
	
	
	
	
	
	
	
	
	
}

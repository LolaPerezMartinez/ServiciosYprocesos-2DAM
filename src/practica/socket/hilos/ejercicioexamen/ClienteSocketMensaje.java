package practica.socket.hilos.ejercicioexamen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSocketMensaje {
	private String address;
	private int port;
	
	 
	public ClienteSocketMensaje(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void mandarMensaje() {
		
		try (Socket socket = new Socket(address, port);
			 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
			
			 for (int i = 1; i <= 3; i++) {
				System.out.printf("%n--INTENTO %d--%n", i);
				System.out.printf("Escribe el comando:");
				
				String mensajeUsuario = reader.readLine();
				pw.println(mensajeUsuario);
				System.out.printf("%nMensaje enviado: %s%n", mensajeUsuario);
				
				String respuesta = br.readLine();
				System.out.printf("%nMensaje recibido: %s%n", respuesta);
				
				if("#finalizado#".equalsIgnoreCase(respuesta)) {
					break;
				}
			}
			 Thread.sleep(500);
			 System.out.printf("%nFin de intentos.");
			
		} catch (IOException e) {
			System.out.printf("%n[Cliente] Problemas en la conexión con el servidor | Error: %s%n", e.getMessage());
		} catch (InterruptedException e) {
			System.out.printf("%n[Cliente] Hilo interrumpido | Error: %s%n", e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		ClienteSocketMensaje c1 = new ClienteSocketMensaje("localhost", 3002);
		c1.mandarMensaje();
	}

}

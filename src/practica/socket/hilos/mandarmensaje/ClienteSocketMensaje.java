package practica.socket.hilos.mandarmensaje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteSocketMensaje {
	private String address;
	private int port;
	
	
	public ClienteSocketMensaje(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void gestionarMensajes() {
		try (Socket socket = new Socket(address, port);
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
			
			for (int i = 1; i <= 4; i++) {
				System.out.printf("%n--INTENTO %d--%n", i);
				System.out.printf("%nIntroduzca un comando:");
				String mensajeUsuario = reader.readLine();
				
				pw.println(mensajeUsuario);
				System.out.printf("%nMensaje enviado: %s%n", mensajeUsuario);
				
				String respuestaRecibida = br.readLine();
				System.out.printf("%nMensaje recibido: %s%n", respuestaRecibida);
				
				if("finalizado".equalsIgnoreCase(respuestaRecibida)) {
					break;
				}
			}
			Thread.sleep(500);
			
		} catch (InterruptedException e) {
			System.out.printf("%n[Cliente] Hilo interrumpido | Error: %s%n", e.getMessage());
		} catch (IOException e) {
			System.out.printf("%n[Cliente] problemas al conectar con el servidor | Error: %s%n", e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		ClienteSocketMensaje cliente = new ClienteSocketMensaje("localhost", 4141);
		cliente.gestionarMensajes();
	}

}

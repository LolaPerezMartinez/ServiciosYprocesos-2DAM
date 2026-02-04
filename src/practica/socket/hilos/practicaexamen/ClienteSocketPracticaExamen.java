package practica.socket.hilos.practicaexamen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSocketPracticaExamen {
	private String address;
	private int port;
	
	
	public ClienteSocketPracticaExamen(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void generarMensajes() {
		try (Socket socket = new Socket(address, port);
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
			
			for (int i = 1; i <= 3; i++) {
				System.out.printf("%n--INTENTO %d--%n", i);
				System.out.printf("Introduzca un comando: ");
				
				String mensaje = reader.readLine();
				System.out.printf("%nMensaje enviado: %s%n", mensaje);
				
				pw.println(mensaje);
				
				String mensajeRecibido = br.readLine();
				System.out.printf("%nMensaje recibido: %s%n", mensajeRecibido);
				
				if("#Finalizado#".equals(mensajeRecibido)) {
					break;
				}
			}
			Thread.sleep(500);
			System.out.println("Intentos finalizados.");
			
		} catch (InterruptedException e) {
			System.out.printf("%n[Cliente] Hilo interrumpido | Error: %s%n", e.getMessage());
		} catch (IOException e) {
			System.out.printf("%n[Cliente] Error al conectar con el servidor | Error: %s%n", e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		ClienteSocketPracticaExamen c1 = new ClienteSocketPracticaExamen("localhost", 4003);
		c1.generarMensajes();
	}

}

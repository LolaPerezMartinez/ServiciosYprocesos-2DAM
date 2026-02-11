package practica.socket.hilos.practicaexamen2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSocketMensajes2 {
	private String address;
	private int port;
	
	
	
	public ClienteSocketMensajes2(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void gestionarComandos() {
		try (Socket socket = new Socket(address, port);
			 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
			 
			 //pw.println() -> enviarrrrr
			
			for (int i = 1; i <= 4; i++) {
				System.out.printf("--INTENTO %d--%n", i);
				System.out.printf("Introduzca un comando:");
				
				String mensajeUsuario = reader.readLine();
				System.out.printf("%nMensaje enviado: %s%n", mensajeUsuario);
				
				pw.println(mensajeUsuario);
				String respuesta = br.readLine();
				if("#Finalizado#".equals(respuesta)) {
					break;
				}
			}
			
			Thread.sleep(500);
			
		} catch (InterruptedException e) {
			System.out.printf("%n[Cliente] Hilo interrumpido | Error: %s%n", e.getMessage());
		} catch (IOException e) {
			System.out.printf("%n[Cliente] Problemas al conectar con el cliente | Error: %s%n", e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		ClienteSocketMensajes2 c1 = new ClienteSocketMensajes2("localhost", 4042);
		c1.gestionarComandos();
	}
	
	

}

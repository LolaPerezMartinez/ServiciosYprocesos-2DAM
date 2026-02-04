package practica.socket.hilos.comandostexto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;

public class ClienteSocketComandoTexto {
	private String address;
	private int port;
	
	
	public ClienteSocketComandoTexto(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void gestionarComandos() {
		
		try (Socket socket = new Socket(address, port);
			 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
			
			 for (int i = 1; i <= 3; i++) {
				 System.out.printf("--INTENTO %d--%n", i);
				 System.out.printf("Introduce tu comando: ");
				 
				 String mensaje = reader.readLine();
				 pw.println(mensaje);
				 System.out.printf("%nMensaje enviado: %s", mensaje);
				 
				 String respuesta = br.readLine();
				 System.out.printf("%nMensaje recibido: %s", respuesta);
				 
				 if("FIN".equals(respuesta)) {
					 break;
				 }
			}
			 Thread.sleep(700);
			 System.out.println("Fin de los intentos");
			
		} catch (IOException e) {
			System.out.printf("%n[Cliente] Problemas en la comunicación con el servidor | Error: %s%n", e.getMessage());
		} catch (InterruptedException e) {
			System.out.printf("%n[Cliente] Hilo interrumpido | Error: %s%n", e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		ClienteSocketComandoTexto c1  = new ClienteSocketComandoTexto("localhost", 2001);
		c1.gestionarComandos();
	}
	
	

}

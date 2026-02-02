package practica.socket.hilos.comandos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSocketComando {
	private int port;
	private String address;
	private int intentos;
	
	public ClienteSocketComando(int port, String address) {
		this.port = port;
		this.address = address;
	}
	
	public void jugar() {
		try (Socket socket = new Socket(address, port);
			 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
			
			for (int i = 1; i <= 3; i++) {
				System.out.printf("%n--COMANDO %d--", i);
				System.out.printf("%nIntroduzca un comando: ");
				
				String mensajeUsuario = reader.readLine();
				pw.println(mensajeUsuario);
				System.out.printf("%nMensaje enviado: %s%n", mensajeUsuario);
				
				String respuesta = br.readLine();
				System.out.printf("Mensaje recibido: %s%n", respuesta);
				
				if("#Finalizado#".equals(respuesta)) {
					System.out.printf("%n[Cliente Socket] Juego finalizado | Fin de la conexión con el servidor");
					break;
				}
				
				Thread.sleep(800);
			}
			System.out.printf("%nFin de intentos%n");
			
		} catch (IOException e) {
			System.out.printf("%n[Cliente] Problemas al conectar con el servidor | Error: %s%n", e.getMessage());
		} catch (InterruptedException e) {
			System.out.printf("%n[Cliente Socket] Hilo interrumpido | Error: %s%n", e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		ClienteSocketComando c1 = new ClienteSocketComando( 7002, "localhost");
		c1.jugar();
	}
	

}

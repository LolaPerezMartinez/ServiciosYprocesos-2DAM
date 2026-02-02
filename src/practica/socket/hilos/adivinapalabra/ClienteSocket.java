package practica.socket.hilos.adivinapalabra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteSocket {
	private String address;
	private int port;
	
	public ClienteSocket(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void jugar() {
		try (Socket socket = new Socket(address, port);
			 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			
			for (int i = 1; i <= 10; i++) {
				System.out.println("\n=== Intento " + i + " ===");
				System.out.printf("Adivina la palabra entre \"clavel\", \"montaña\", \"reloj\", \"nube\", \"ventana\", \"faro\", \"cuaderno\", \"bosque\", \"taza\", \"estrella\"%n");
				System.out.print("Introduce la palabra a enviar: ");
				
				String mensaje = reader.readLine();
				
				pw.println(mensaje);
				System.out.printf("%nMensaje enviado: %s%n", mensaje);
				
				String respuesta = br.readLine();
				System.out.printf("Mensaje recibido: %s%n", respuesta);
				
				if("Juego terminado".equals(respuesta)) {
					break;
				}
				
				Thread.sleep(800);
			}
			
		} catch (IOException e) {
			System.out.printf("%n[Cliente] Problemas al conectar con el servidor | Error: %s%n", e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ClienteSocket cliente = new ClienteSocket("localhost", 7001);
		cliente.jugar();
	}
}

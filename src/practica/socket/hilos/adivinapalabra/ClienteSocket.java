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
	
	public void jugar(String mensaje) {
		try (Socket socket = new Socket(address, port);
			 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			
			String datoEnviado = mensaje;
			pw.println(datoEnviado);
			System.out.printf("%nMensaje enviado: %s%n", datoEnviado);
			
			String respuesta = br.readLine();
			System.out.printf("Mensaje recibido: %s%n", respuesta);
			
		} catch (IOException e) {
			System.out.printf("%n[Cliente] Problemas al conectar con el servidor | Error: %s%n", e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			for (int i = 1; i <= 10; i++) {
				System.out.println("\n=== Cliente " + i + " ===");
				System.out.print("Introduce el mensaje a enviar: ");
				
				String mensaje = reader.readLine();
				
				ClienteSocket cliente = new ClienteSocket("localhost", 7001);
				cliente.jugar(mensaje);
				
				Thread.sleep(800);
			}
			
			reader.close();
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
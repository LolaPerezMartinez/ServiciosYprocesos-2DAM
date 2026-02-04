package practica.socket.hilos.mensajecomando;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSocketMensajeComando {
	private String address;
	private int port;
	private int intentos;
	
	
	public ClienteSocketMensajeComando(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void gestionarMensajes() {
		try(Socket socket = new Socket(address, port);
				PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));){
				
				for (int i = 1; i <= 3; i++) {
					System.out.printf("%n--INTENTO %d--", i);
					System.out.printf("%nIntroduzca un comando: ");
					
					String mensaje = reader.readLine();
					pw.println(mensaje);
					System.out.printf("%nMensaje enviado: %s", mensaje);
					
					String respuesta = br.readLine();
					System.out.printf("%nMensaje recibido: %s", respuesta);
					
					if("OK".equalsIgnoreCase(respuesta)) {
						break;
					}
					
				}
				Thread.sleep(500);
				System.out.printf("%nIntentos finalizados%n");
			
		} catch (IOException e) {
			System.out.printf("%n[Cliente socket] Problemas en la conexión con el servidor | Error: %s%n", e.getMessage());
		} catch (InterruptedException e) {
			System.out.printf("%n[Cliente socket] Hilo interrumpido | Error: %s%n", e.getMessage());
		}
		
	}
	public static void main(String[] args) {
		ClienteSocketMensajeComando c1 = new ClienteSocketMensajeComando("localhost", 3001);
		c1.gestionarMensajes();
	}

}

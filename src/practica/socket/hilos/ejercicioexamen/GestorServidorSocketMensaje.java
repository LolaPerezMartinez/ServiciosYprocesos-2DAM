package practica.socket.hilos.ejercicioexamen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorServidorSocketMensaje implements Runnable{
	private Socket socket;
	private Mensajes mensaje;
	private static AtomicLong peticionesAlServidor = new AtomicLong(0);
	
	
	public GestorServidorSocketMensaje(Socket socket) {
		this.socket = socket;
		mensaje = new Mensajes();
	}


	@Override
	public void run() {
		try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			
			String datoLeido;
			while((datoLeido = br.readLine()) != null) {
				
				System.out.printf("%n[Gestor de sockets] Dato recibido: %s%n", datoLeido);
				String respuesta = mensaje.verificarMensaje(datoLeido);
				
				pw.println(respuesta);
				peticionesAlServidor.getAndIncrement();
				System.out.printf("%n[Gestor de sockets] Dato enviado: %s | Sockets aceptados: %d | Peticiones al servidor: %d%n", 
								  respuesta, ServidorSocketMensaje.getSocketsAceptados().get(), peticionesAlServidor.get());
				
				if("#finalizado#".equalsIgnoreCase(respuesta)) {
					break;
				}
			}
		} catch (IOException e) {
			System.out.printf("%n[Gestor de sockets] Problemas en la conexión con el cliente | Error: %s%n", e.getMessage());
		}
		
		
	}
	
	

}

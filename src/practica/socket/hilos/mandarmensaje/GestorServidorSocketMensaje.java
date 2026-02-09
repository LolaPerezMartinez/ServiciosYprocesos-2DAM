package practica.socket.hilos.mandarmensaje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class GestorServidorSocketMensaje implements Runnable{
	private Socket socket;
	private GestorMensajes gestor;
	private static AtomicInteger peticionesAlServidor = new AtomicInteger(0);
	
	

	public GestorServidorSocketMensaje(Socket socket) {
		this.socket = socket;
		gestor = new GestorMensajes();
	}

	public static AtomicInteger getPeticionesAlServidor() {
		return peticionesAlServidor;
	}

	@Override
	public void run() {
		try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			
			 String datoLeido;
			 while((datoLeido = br.readLine()) != null) {
				 peticionesAlServidor.incrementAndGet();
				 System.out.printf("%n[Gestor de servidor] Dato recibido: %s%n", datoLeido);
				 
				 String respuesta = gestor.verificarMensaje(datoLeido);
				 pw.println(respuesta);
				 System.out.printf("%n[Gestor de servidor] Dato enviado: %s%n", respuesta);
				 
				 if("finalizado".equalsIgnoreCase(respuesta)) {
					 break;
				 }
			 }
			 System.out.printf("%nIntentos finalizados | Sockets aceptados: %d | Peticiones al servidor: %d%n",
					  ServidorSocketMensaje.getSocketsAceptados(), GestorServidorSocketMensaje.getPeticionesAlServidor().get());
			
		} catch (IOException e) {
			System.out.printf("%n[Gestor de servidor] Problemas leyendo buffered | Error: %s%n", e.getMessage());
		} 
		
	}

}

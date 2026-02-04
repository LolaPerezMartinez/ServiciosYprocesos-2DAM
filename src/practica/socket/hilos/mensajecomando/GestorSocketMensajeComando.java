package practica.socket.hilos.mensajecomando;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorSocketMensajeComando implements Runnable{
	private GestorMensajes gestor;
	private Socket socket;
	private AtomicLong peticionesAlServidor = new AtomicLong(0);
	
	

	public GestorSocketMensajeComando(Socket socket) {
		this.socket = socket;
		gestor = new GestorMensajes();
	}

	@Override
	public void run() {
		
		try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);	
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));){
			
			String datoRecibido;
			while((datoRecibido = br.readLine()) != null) {
				System.out.printf("%n[Gestor de servidor] Dato recibido: %s | Peticiones al servidor: %d%n", datoRecibido, peticionesAlServidor.get());
				
				String respuesta = gestor.verificarMensaje(datoRecibido);
				pw.println(respuesta);
				peticionesAlServidor.getAndIncrement();
				System.out.printf("%n[Gestor de servidor] Respuesta enviada: %s | Peticiones al servidor: %d%n", respuesta, peticionesAlServidor.get());
				
				if("ok".equalsIgnoreCase(respuesta)) {
					break;
				}
			}
		} catch (IOException e) {
			System.out.printf("%n[Gestor de servidor] Problemas en lectura de buffered | Error: %s%n", e.getMessage());
		}
		
		
	}

}

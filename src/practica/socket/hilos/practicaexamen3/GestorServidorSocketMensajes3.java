package practica.socket.hilos.practicaexamen3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorServidorSocketMensajes3 implements Runnable{
	private Socket socket;
	private GestorMensajes3 gestor;
	private AtomicLong peticionesAlServidor = new AtomicLong(0);
	
	

	public GestorServidorSocketMensajes3(Socket socket) {
		this.socket = socket;
		//cada hilo tiene su gestor de mensajes
		gestor = new GestorMensajes3();
	}



	@Override
	public void run() {
		
		// pw.println() -> enviarrrr
		
		try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			
			String datoLeido;
			while((datoLeido = br.readLine()) != null) {
				peticionesAlServidor.getAndIncrement();
				System.out.printf("%n[Gestor de servidor] Dato leido: %s%n", datoLeido);
				
				String respuesta = gestor.verificarMensaje(datoLeido);
				pw.println(respuesta);
				System.out.printf("%n[Gestor de servidor] Dato enviado: %s%n", respuesta);
				
				
				if("#Finalizado#".equals(respuesta)) {
					break;
				}
			}
			System.out.printf("%nIntentos finalizados | Sockets Aceptados: %d | Peticiones al servidor: %d%n",
					  ServidorSocketMensajes3.getSocketsAceptados().get(), peticionesAlServidor.get());
			
		} catch (IOException e) {
			System.out.printf("[Gestor de servidor] Problemas al leer buffered | Error: %s%n", e.getMessage());
		}
		
	}

}

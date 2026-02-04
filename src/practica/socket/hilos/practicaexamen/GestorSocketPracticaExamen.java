package practica.socket.hilos.practicaexamen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorSocketPracticaExamen implements Runnable{
	private Socket socket;
	private InterpreteInstrucciones interprete;
	private static AtomicLong peticionesAlServidor = new AtomicLong(0);
	
	
	public GestorSocketPracticaExamen(Socket socket) {
		this.socket = socket;
		interprete = new InterpreteInstrucciones();
	}


	@Override
	public void run() {
		try(PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			
			String datoLeido;
			while((datoLeido = br.readLine()) != null) {
				System.out.printf("%n[Gestor de socket] Mensaje recibido: %s%n", datoLeido);
				
				String respuesta = interprete.verificarMensaje(datoLeido);
				pw.println(respuesta);
				peticionesAlServidor.getAndIncrement();
				System.out.printf("%n[Gestor de socket] Mensaje enviado: %s%n", respuesta);
				
				if("#Finalizado#".equals(respuesta)) {
					System.out.printf("%n[Gestor de socket] Juego finalizado | Sockets aceptados: %d | Peticiones al servidor: %d%n",
									  ServidorSocketPracticaExamen.getSocketsAceptados().get(), peticionesAlServidor.get());
					break;
				}
			}
			
		}catch (IOException e) {
			System.out.printf("%n[Gestor de socket] Juego finalizado | Sockets aceptados: %d | Peticiones al servidor: %d%n",
							 ServidorSocketPracticaExamen.getSocketsAceptados().get(), peticionesAlServidor.get());
		}
		
		
		
	}

}

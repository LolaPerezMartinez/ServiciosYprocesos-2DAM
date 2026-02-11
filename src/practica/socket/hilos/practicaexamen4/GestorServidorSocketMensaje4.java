package practica.socket.hilos.practicaexamen4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorServidorSocketMensaje4 implements Runnable{
	private Socket socket;
	private GestorMensajes4 gestor;
	private static AtomicLong peticionesAlServidor = new AtomicLong(0);
	
	

	public GestorServidorSocketMensaje4(Socket socket) {
		this.socket = socket;
		gestor = new GestorMensajes4();
	}



	@Override
	public void run() {
		try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			// println enviaaaaaa
			
			String datoLeido;
			while((datoLeido = br.readLine()) != null) {
				peticionesAlServidor.getAndIncrement();
				System.out.printf("%n[Gestor de Servidor] Datos recibidos: %s%n", datoLeido);
				
				String respuesta = gestor.verificarintento(datoLeido);
				pw.println(respuesta);
				System.out.printf("%n[Gestor de Servidor] Datos enviados: %s%n", respuesta);
				
				if("#Finalizado#".equals(respuesta)) {
					break;
				}
			}
			System.out.printf("%n[Gestor de servidor] Intentos finalizados | Sockets aceptados: %d | Peticiones al servidor: %d%n",
							 ServidorSocketMensajes4.getSocketsAceptados().get(), peticionesAlServidor.get());
			
		} catch (IOException e) {
			System.out.printf("%n[Servidor] Problemas al leer buffered | Error: %S%n", e.getMessage());
		}
		
	}

}

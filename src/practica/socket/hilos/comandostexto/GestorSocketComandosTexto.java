package practica.socket.hilos.comandostexto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorSocketComandosTexto implements Runnable{
	private Socket socket;
	private Comandos comandos;
	private AtomicLong peticionesAlServidor = new AtomicLong(0);
	
	
	public GestorSocketComandosTexto(Socket socket) {
		this.socket = socket;
		comandos = new Comandos();
	}


	@Override
	public void run() {
		try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			
			 String datoLeido;
			 while((datoLeido = br.readLine()) != null) {
				 
				 System.out.printf("%n[Gestor Socket] Dato recibido: %s%n", datoLeido);
				 
				 String respuesta = comandos.verificarComandos(datoLeido);
				 pw.println(respuesta);
				 peticionesAlServidor.getAndIncrement();
				 System.out.printf("%n[Gestor Socket] Dato enviado: %s%n", respuesta);
				 
				 if("FIN".equals(respuesta)) {
					 System.out.printf("%n[Gestor Socket] Sockets aceptados: %d | Peticiones al servidor: %d%n",
							 		   ServidorSocketComandoTexto.getSocketsAceptados(), peticionesAlServidor.get());
					 break;
				 }
			 }
			 
			 System.out.printf("%n[Gestor Socket] Sockets aceptados: %d | Peticiones al servidor: %d%n",
			 		   ServidorSocketComandoTexto.getSocketsAceptados(), peticionesAlServidor.get());
			
		} catch (IOException e) {
			System.out.printf("%n[Gestor Socket] Problemas en lectura de buffered | Error: %s%n", e.getMessage());
		}
		
		
	}

}

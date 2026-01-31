package practica.socket.hilos.adivinalista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorServerSocketLista implements Runnable{
	private Socket socket;
	private JuegoLista juego;
	private static AtomicLong peticionesAlServidor = new AtomicLong(0);
	

	public GestorServerSocketLista(Socket socket) {
		this.socket = socket;
		juego = new JuegoLista();
	}


	@Override
	public void run() {
		try(PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));){
			
			String mensajeLeido = br.readLine();
			
				String respuesta = juego.handlerMensajes(mensajeLeido);
				 pw.println(respuesta);
				 
				 peticionesAlServidor.getAndIncrement();
				 System.out.printf("%n[Gestor Socket] Mensaje leido: %s | Mensaje enviado: %s | Peticiones al servidor: %d%n",
						 			mensajeLeido, respuesta, peticionesAlServidor.get());
				 
		
			
		}catch(IOException e){
			System.out.printf("%n[Gestor Socket] Problemas al conectar con el cliente | Error: %s%n", e.getMessage());
		}
		
	}

}

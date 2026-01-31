package practica.socket.hilos.mandalista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorSocket implements Runnable{
	private Socket socket;
	private JuegoMensajes juego;
	private static AtomicLong peticionesAlServidor = new AtomicLong(0);
	
	
	public GestorSocket(Socket socket) {
		this.socket = socket;
		juego = new JuegoMensajes();
	}


	@Override
	public void run() {
		try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			
			String mensajeRecibido = br.readLine();
			peticionesAlServidor.getAndIncrement();
			System.out.printf("%n[Gestor de socket] Peticiones al servidor: %d%n", peticionesAlServidor.get());
			
			String respuesta = juego.handlerMensaje(mensajeRecibido);
			pw.println(respuesta);
			
		}catch (IOException e) {
			System.out.printf("%n[Gestor de socket] Problemas con la conexión con el cliente | Error: %s%n", e.getMessage());
			return;
		}
		
	}
	
	
	

}

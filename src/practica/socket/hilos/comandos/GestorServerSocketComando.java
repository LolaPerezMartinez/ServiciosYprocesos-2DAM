package practica.socket.hilos.comandos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorServerSocketComando implements Runnable{
	private Socket socket;
	private JuegoComando juego;
	private AtomicLong peticionesAlServidor = new AtomicLong(0);
	
	

	public GestorServerSocketComando(Socket socket) {
		this.socket = socket;
		juego = new JuegoComando();
	}

	@Override
	public void run() {
		try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			
			
			String datoRecibido;
			while((datoRecibido = br.readLine()) != null) {
				peticionesAlServidor.getAndIncrement();
				
				System.out.printf("%n[Gestor de servidor] Dato recibido: %s | Peticiones al servidor: %d%n", datoRecibido, peticionesAlServidor.get());
				
				String respuesta = juego.verificarIntento(datoRecibido);
				pw.println(respuesta);
				System.out.printf("%n[Gestor de servidor] Dato enviado: %s%n", respuesta);
			}
			
			
		} catch (IOException e) {
			System.out.printf("%n[Gestor de servidor] Problemas en la conexión con el cliente | Error: %s%n", e.getMessage());
		}
		
		
	}

}

package practica.socket.hilos.adivinapalabra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorServerSocketAdivinaPalabra implements Runnable{
	private Socket socket;
	private JuegoPalabra juego;
	private static AtomicLong peticionesAlServidor = new AtomicLong(0);
	
	

	public GestorServerSocketAdivinaPalabra(Socket socket) {
		this.socket = socket;
		juego = new JuegoPalabra();
	}



	@Override
	public void run() {
		try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			
			String datoLeido;
			while((datoLeido = br.readLine()) != null) {
				peticionesAlServidor.getAndIncrement();
				
				String respuesta = juego.verificarIntento(datoLeido);
				pw.println(respuesta);
				
				if("Acertaste".equals(respuesta)) {
					System.out.printf("%n[Gestor de servidor] Respuesta acertada | Sockets aceptados: %d | Peticiones al servidor: %d%n",
									 ServidorSocketAdivinaPalabra.getSocketAceptados(), peticionesAlServidor.get());
					break;
				}
				
			}
			
		} catch (IOException e) {
			System.out.printf("%n[Gestor de servidor] Problemas con buffered | Error: %s%n", e.getMessage());
		}
		
	}

}

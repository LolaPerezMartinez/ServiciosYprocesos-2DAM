package practica.socket.hilos.adivinanzanumero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorSocketAdivinanzaNumero implements Runnable{
	private Socket socket;
	private JuegoAdivinar juego;
	
	private static AtomicLong peticionesAlServidor = new AtomicLong(0);
	
	
	public GestorSocketAdivinanzaNumero(Socket socket) {
		this.socket = socket;
		juego =  new JuegoAdivinar();
	}





	@Override
	public void run() {
		
		try (InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr)){
			
			String datoLeido;
			while((datoLeido = br.readLine()) != null) {
				peticionesAlServidor.getAndIncrement();
				String respuesta = juego.verificarIntento(datoLeido);
				pw.println(respuesta);
				
				if("Acertaste".equals(respuesta)) {
					System.out.printf("%n[Gestor de Servidor] Acertaste el numero | Sockets aceptados: %d | Peticiones al servidor: %d%n",
									  ServerSocketAdivinanzaNumero.getSocketsAceptados(), peticionesAlServidor.get());
					break;
				}
				
			}
		} catch (IOException e) {
			System.out.printf("%n[Gestor de Servidor] Problema al conectar con el cliente | Error: %s%n", e.getMessage());
			return;
		}
	}
	
	
	
	

}

package practica.socket.hilos.adivinanzas2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorSocket implements Runnable{
	private Socket socket;
	private JuegoAdivinanza juego;
	private InputStream is;
	private OutputStream os;
	private PrintWriter pw;
	private InputStreamReader isr;
	private BufferedReader br;
	
	private static AtomicLong peticionesAlServidor = new AtomicLong(0);
	
	public GestorSocket(Socket socket) {
		this.socket = socket;
		juego =  new JuegoAdivinanza();
	}

	@Override
	public void run() {
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
			pw = new PrintWriter(os, true);
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
		} catch (IOException e) {
			System.out.printf("%n[Gestor de Streams] Error abriendo el gestor de streams.%n");
			return;
		}
		
		while(true) {
			String datoLeido;
			
			try {
				datoLeido = br.readLine();
				if(datoLeido != null) {
					
					peticionesAlServidor.getAndIncrement();
					String respuesta = juego.verificarIntento(datoLeido);
					pw.println(respuesta);
					
					if("Acierto".equals(respuesta)) {
						System.out.printf("%n[Gestor Socket] Peticiones al servidor aceptadas: %d | Sockets aceptados: %d%n",
										  ServerSocketAdivinanzas.getSocketsAceptados(), peticionesAlServidor.get());
					}
					
				}else {
					break;
				}
			} catch (IOException e) {
				System.out.printf("%nError leyendo buffered reader.%n");
				break;
			}
		}
		
		try {
			pw.close();
			br.close();
			isr.close();
			is.close();
			os.close();
			
		}catch (IOException e) {
			System.out.printf("%n[Gestor Sockets] Error cerrando streams de sockets.%n");
		}
	}
	
	
	

}

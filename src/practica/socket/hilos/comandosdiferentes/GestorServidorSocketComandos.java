package practica.socket.hilos.comandosdiferentes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorServidorSocketComandos implements Runnable{
	private Socket socket;
	private GestoriaComandos gestor;
	private AtomicLong peticionesAlServidor = new AtomicLong(0);
	

	public GestorServidorSocketComandos(Socket socket) {
		this.socket = socket;
		gestor = new GestoriaComandos();
	}

	@Override
	public void run() {
		try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			
			String datoLeido;
			while((datoLeido = br.readLine()) != null) {
				System.out.printf("%n[Gestor de servidor] Dato recibido: %s%n", datoLeido);
				
				String respuesta = gestor.verificarComando(datoLeido);
				pw.println(respuesta);
				peticionesAlServidor.getAndIncrement();
				System.out.printf("%n[Gestor de servidor] Dato enviado: %s%n",respuesta);
				
				if("FIN".equalsIgnoreCase(respuesta)) {
					System.out.printf("%n[Gestor de servidor] Fin de conexión | Sockets aceptados: %d | Peticiones al servidor: %d%n",
									  ServidorSocketComandos.getSocketsAceptados().get(), peticionesAlServidor.get());
				}
			}
			
		} catch (IOException e) {
			System.out.printf("%n[Servidor] Problemas al leer buffered | Error: %s%n", e.getMessage());
		}
		
	}

}

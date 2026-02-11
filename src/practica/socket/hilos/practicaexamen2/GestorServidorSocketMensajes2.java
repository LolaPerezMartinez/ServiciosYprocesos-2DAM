package practica.socket.hilos.practicaexamen2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorServidorSocketMensajes2 implements Runnable{
	private Socket socket;
	private ManejadorComandos comandos;
	private AtomicLong peticionesAlServidor = new AtomicLong(0);
	
	

	public GestorServidorSocketMensajes2(Socket socket) {
		this.socket = socket;
		comandos = new ManejadorComandos();
	}



	@Override
	public void run() {
		try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			//pw.printlnn
			
			String datoLeido;
			while((datoLeido = br.readLine()) != null) {
				System.out.printf("%n[Gestor de servidor] Dato leido: %s%n", datoLeido);
				peticionesAlServidor.incrementAndGet();
				
				String respuesta = comandos.verificarComando(datoLeido);
				pw.println(respuesta);
				System.out.printf("%n[Gestor de servidor] Dato enviado: %s%n", respuesta);
				
				if("#finalizado#".equalsIgnoreCase(respuesta)) {
					break;
				}
			}
			
			System.out.printf("%n[Gestor de servidor] Intentos finalizados | Sockets aceptados: %d | Peticiones al servidor: %d%n",
							 ServidorSocketMensajes2.getSocketsAceptados().get(), peticionesAlServidor.get());
			
		} catch (IOException e) {
			System.out.printf("%n[Servidor] Problemas al leer buffered | Error: %s%n", e.getMessage());
		}
		
	}

}

package practica.socket.hilos.comandosdiferentes;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class ServidorSocketComandos {
	private int port;
	private ServerSocket server;
	private static AtomicLong socketsAceptados = new AtomicLong(0);
	
	public ServidorSocketComandos(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}

	public static AtomicLong getSocketsAceptados() {
		return socketsAceptados;
	}
	
	public static void main(String[] args) {
		ServidorSocketComandos servidor = null;
		
		try {
			servidor = new ServidorSocketComandos(4002);
			System.out.printf("[Servidor] Servidor escuchando en el puerto %d%n", servidor.port);
		} catch (IOException e) {
			System.out.printf("[Servidor] Problemas con el puerto | Error: %s%n", e.getMessage());
		}
		
		Socket socket = null;
		while(true) {
			try {
				socket = servidor.server.accept();
				socketsAceptados.getAndIncrement();
				System.out.printf("%n[Servidor] Servidor aceptando sockets %n");
				new Thread(new GestorServidorSocketComandos(socket)).start();
				
			} catch (IOException e) {
				System.out.printf("%n[Servidor] Problemas al crear conexión con el cliente | Error: %s%n", e.getMessage());
				break;
			}
			
		}
	}
	
	

}

package practica.socket.hilos.practicaexamen2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class ServidorSocketMensajes2 {
	private int port;
	private ServerSocket server;
	private static AtomicLong socketsAceptados = new AtomicLong(0);
	
	public ServidorSocketMensajes2(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}

	public static AtomicLong getSocketsAceptados() {
		return socketsAceptados;
	}
	
	public static void main(String[] args) {
		ServidorSocketMensajes2 servidor = null;
		
		try {
			servidor = new ServidorSocketMensajes2(4042);
			System.out.printf("[Servidor] Escuchando en el puerto %d%n", servidor.port);
		} catch (IOException e) {
			System.out.printf("[Servidor] Problemas en el puerto %d | Error: %s%n", servidor.port, e.getMessage());
		}
		
		Socket socket = null;
		
		while(true) {
			try {
				socket = servidor.server.accept();
				socketsAceptados.incrementAndGet();
				System.out.printf("%n[Servidor] Sockets aceptados: %d%n", socketsAceptados.get());
				new Thread(new GestorServidorSocketMensajes2(socket)).start();
			} catch (IOException e) {
				System.out.printf("%n[Servidor] Problemas al conectar con el cliente | Error: %s%n", e.getMessage());
				break;
			}
			
		}
	}
	
}

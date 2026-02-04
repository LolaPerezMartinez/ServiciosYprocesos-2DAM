package practica.socket.hilos.ejercicioexamen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class ServidorSocketMensaje {
	private int port;
	private ServerSocket server;
	private static AtomicLong socketsAceptados = new AtomicLong(0);
	
	public ServidorSocketMensaje(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}

	

	public static AtomicLong getSocketsAceptados() {
		return socketsAceptados;
	}


	public static void main(String[] args) {
		ServidorSocketMensaje servidor = null;
		
		try {
			servidor = new ServidorSocketMensaje(3002);
			System.out.printf("[Servidor] Conectando al puerto %d.%n", servidor.port);
		} catch (IOException e) {
			System.out.printf("[Servidor] Problemas conectando con el puerto %d | Error: %s%n", servidor.port, e.getMessage());
		}
		
		Socket socket = null;
		while(true) {
			try {
				socket = servidor.server.accept();
				socketsAceptados.getAndIncrement();
				System.out.printf("%n[Servidor] Aceptando sockets");
				
				new Thread(new GestorServidorSocketMensaje(socket)).start();
				
			} catch (IOException e) {
				System.out.printf("%n[Servidor] Problemas conectando con el cliente | Error: %s%n", e.getMessage());
			}
			
		}
	}
	
	
}

package practica.socket.hilos.practicaexamen3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class ServidorSocketMensajes3 {
	private int port;
	private ServerSocket server;
	private static AtomicLong socketsAceptados = new AtomicLong(0);
	
	public ServidorSocketMensajes3(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}

	public static AtomicLong getSocketsAceptados() {
		return socketsAceptados;
	}
	
	public static void main(String[] args) {
		ServidorSocketMensajes3 servidor = null;
		
		try {
			servidor = new ServidorSocketMensajes3(5050);
			System.out.printf("[Servidor] Conexión aceptada en puerto %d%n", servidor.port);
		} catch (IOException e) {
			System.out.printf("[Servidor] Problemas al conectar con el puerto | Error: %s%n", e.getMessage());
		}
		
		Socket socket = null;
		while(true) {
			try {
				socket = servidor.server.accept();
				socketsAceptados.getAndIncrement();
				System.out.printf("[Servidor] Sockets aceptados %d%n", socketsAceptados.get());
				new Thread(new GestorServidorSocketMensajes3(socket)).start();
			} catch (IOException e) {
				System.out.printf("[Servidor] Problemas al conectar con el cliente | Error: %s%n", e.getMessage());
			}
		}
		
	}


	
	
	

}

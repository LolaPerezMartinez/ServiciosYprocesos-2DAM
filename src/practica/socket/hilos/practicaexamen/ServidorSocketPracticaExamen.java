package practica.socket.hilos.practicaexamen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class ServidorSocketPracticaExamen {
	private int port;
	private ServerSocket server;
	private static AtomicLong socketsAceptados = new AtomicLong(0);
	
	public ServidorSocketPracticaExamen(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}

	public static AtomicLong getSocketsAceptados() {
		return socketsAceptados;
	}

	public static void main(String[] args) {
		ServidorSocketPracticaExamen servidor = null;
		
		try {
			servidor = new ServidorSocketPracticaExamen(4003);
			System.out.printf("[Servidor] Aceptado en el puerto %d%n", servidor.port);
		} catch (IOException e) {
			System.out.printf("[Servidor] Problemas al conectar con el puerto %d | Error: %s%n", servidor.port, e.getMessage());
		}
		
		Socket socket = null;
		while(true) {
			try {
				socket = servidor.server.accept();
				socketsAceptados.getAndIncrement();
				new Thread(new GestorSocketPracticaExamen(socket)).start();
			} catch (IOException e) {
				System.out.printf("[Servidor] Problemas al conectar con el cliente | Error: %s%n", e.getMessage());
				break;
			}
		}
	}
	
	

}

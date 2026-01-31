package practica.socket.hilos.mandalista;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
	private int port;
	private ServerSocket server;
	private static int socketsAceptados = 0;
	
	
	public ServidorSocket(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}


	public static void main(String[] args) {
		ServidorSocket server = null;
		
		try {
			server = new ServidorSocket(4004);
			System.out.printf("[Servidor] Conectado al puerto %d%n", server.port);
		} catch (IOException e) {
			System.out.printf("%n[Servidor] Problemas al conectar con el puerto | Error: %s%n", e.getMessage());
		}
		
		Socket socket = null;
		while(true) {
			try {
				socket = server.server.accept();
				socketsAceptados++;
				System.out.printf("%n[Servidor] Sockets aceptados: %d%n", socketsAceptados);
				new Thread(new GestorSocket(socket)).start();
			} catch (IOException e) {
				System.out.printf("%n[Servidor] Problemas al aceptar el socket | Error: %s%n", e.getMessage());
				break;
			}
		}
	
	}

}

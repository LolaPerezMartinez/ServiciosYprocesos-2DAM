package practica.socket.hilos.adivinanzas2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

import practica.socket.hilos.adivinanzas.GestorSocketServer;

public class ServerSocketAdivinanzas {
	private int port;
	private ServerSocket server;
	private static long socketsAceptados = 0;
	
	
	public ServerSocketAdivinanzas(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}


	public static long getSocketsAceptados() {
		return socketsAceptados;
	}

	public static void main(String[] args) {
		ServerSocketAdivinanzas server = null;
		
		try {
			server = new ServerSocketAdivinanzas(5001);
			System.out.printf("Server escuchando en el puerto %d%n", server.port);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.printf("%nTermino el programa para no ejecutar el while y obtener un nullPointerException%n");
			return;
		}
		
		while(true) {
			Socket socket = null;
			try {
				socket = server.server.accept();
				socketsAceptados++;
				System.out.printf("%n[Servidor: %d] Socket aceptado nº%d%n.", server.port, socketsAceptados);
				new Thread(new GestorSocketServer(socket)).start();
				
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
	
	
	

}

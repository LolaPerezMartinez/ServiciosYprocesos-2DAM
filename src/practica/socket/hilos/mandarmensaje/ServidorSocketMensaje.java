package practica.socket.hilos.mandarmensaje;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocketMensaje {
	private int port;
	private ServerSocket server;
	private static int socketsAceptados = 0;
	
	
	public ServidorSocketMensaje(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}


	public static int getSocketsAceptados() {
		return socketsAceptados;
	}
	
	public static void main(String[] args) {
		ServidorSocketMensaje servidor = null;
		
		try {
			servidor = new ServidorSocketMensaje(4141);
			System.out.printf("%n[Servidor] Escuchando en el puerto %d.", servidor.port);
		} catch (IOException e) {
			System.out.printf("%n[Servidor] Problemas al conectar con el puerto | Error: %s%n", e.getMessage());
		}
		
		Socket socket = null;
		while(true) {
			try {
				socket = servidor.server.accept();
				socketsAceptados++;
				System.out.printf("%n[Servidor] Socket aceptado numero: %d%n", socketsAceptados);
				new Thread(new GestorServidorSocketMensaje(socket)).start();
			
			} catch (IOException e) {
				System.out.printf("%n[Servidor] Problemas al conectar con el cliente | Error: %s%n", e.getMessage());
				break;
			}
			
		}
	}
	
	
}

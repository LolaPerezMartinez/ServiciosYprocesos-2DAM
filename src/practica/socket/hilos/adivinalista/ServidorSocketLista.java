package practica.socket.hilos.adivinalista;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocketLista {
	private int port;
	private ServerSocket server;
	private static long socketsAceptados;
	
	public ServidorSocketLista(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}

	public static long getSocketsAceptados() {
		return socketsAceptados;
	}
	
	public static void main(String[] args) {
		ServidorSocketLista servidor = null;
		
		try {
			servidor = new ServidorSocketLista(40040);
			System.out.printf("%n[Servidor] Escuchando en el puerto determinado.%n");
		} catch (IOException e) {
			System.out.printf("%n[Servidor] Problemas al crear el servidor | Error: %s%n", e.getMessage());
			return;
		}
		
		Socket socket = null;
		while(true) {
			try {
				socket = servidor.server.accept();
				socketsAceptados++;
				System.out.printf("%n[Servidor] Sockets aceptados: %d%n", socketsAceptados);
				//hilo
			} catch (IOException e) {
				System.out.printf("%n[Servidor] Problemas al conectar con el cliente | Error: %s%n", e.getMessage());
				break;
			}
			
		}
	}
	
	
	
}

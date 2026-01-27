package practica.socket.hilos.adivinanzaletra;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketLetra {
	private ServerSocket server;
	private int port;
	private static long socketsAceptados = 0;
	
	public ServerSocketLetra(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}
	
	public static long getSocketsAceptados() {
		return socketsAceptados;
	}

	public static void main(String[] args) {
		ServerSocketLetra server = null;
		
		try {
			server = new ServerSocketLetra(7001);
			System.out.printf("%n[Servidor] Servidor creado.%n");
		} catch (IOException e) {
			System.out.printf("%n[Servidor] Problemas al crear el servidor.%n");
			return;
		}
		
		
			
		while(true) {
			Socket miSocket = null;
			try {
				
				miSocket = server.server.accept();
				System.out.printf("%n[Servidor] Socket aceptado %d.%n", socketsAceptados);
				socketsAceptados++;
				
				new Thread(new GestorSocketLetra(miSocket)).start();
			
			} catch (IOException e) {
				System.out.printf("%n[Servidor] Problemas al aceptar el cliente.%n");
				return;
			}
			
		}
	}
	
	
	
	

}

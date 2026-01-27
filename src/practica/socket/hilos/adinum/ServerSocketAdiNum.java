package practica.socket.hilos.adinum;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketAdiNum {
	private int port;
	private ServerSocket server;
	private static int socketsAceptados = 0;
	
	
	public ServerSocketAdiNum(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}
	
	public static int getSocketsAceptados() {
		return socketsAceptados;
	}

	public static void main(String[] args) {
		ServerSocketAdiNum servidor = null;
		
		try {
			servidor =  new ServerSocketAdiNum(3001);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			Socket socket = null;
			try {
				socket = servidor.server.accept();
				socketsAceptados++;
				System.out.printf("%n[Servidor] Socket aceptados: %d.%n", socketsAceptados);
				new Thread(new GestorSocketAdiNum(socket)).start();
			} catch (IOException e) {
				System.out.printf("%n[Servidor] Conexión con cliente rechazada.%n");
				return;
			}
		}
	}
	
	

}

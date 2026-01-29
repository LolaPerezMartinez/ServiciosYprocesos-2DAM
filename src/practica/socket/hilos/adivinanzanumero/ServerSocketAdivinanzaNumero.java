package practica.socket.hilos.adivinanzanumero;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketAdivinanzaNumero {
	private int port;
	private ServerSocket server;
	
	private static long socketsAceptados = 0;

	public ServerSocketAdivinanzaNumero(int port) throws IOException {
		this.port = port;
		server =  new ServerSocket(port);
	}
	
	public static long getSocketsAceptados() {
		return socketsAceptados;
	}

	public static void main(String[] args) {
		ServerSocketAdivinanzaNumero servidor = null;
		
		try {
			servidor = new ServerSocketAdivinanzaNumero(50050);
			System.out.printf("%n[Servidor] Escuchando para aceptar conexión.%n");
		} catch (IOException e) {
			System.out.printf("%n[Servidor] Problemas al crear serverSocket | Error: %s%n", e.getMessage());
			return;
		}
		
		while(true) {
			Socket socket = null;
			try {
				socket = servidor.server.accept();
				socketsAceptados++;
				System.out.printf("%n[Servidor] Sockets aceptados: %d.%n", socketsAceptados);
				new Thread(new GestorSocketAdivinanzaNumero(socket)).start();
			} catch (IOException e) {
				System.out.printf("%n[Servidor] Problemas al aceptar clientes | Error: %s%n", e.getMessage());
				break;
			}
		}
	}
	

}

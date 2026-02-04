package practica.socket.hilos.mensajecomando;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocketMensajeComando {
	private int port;
	private ServerSocket server;
	private static long socketsAceptados = 0;
	
	
	
	public ServidorSocketMensajeComando(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}


	public long getSocketsAceptados() {
		return socketsAceptados;
	}
	
	
	public static void main(String[] args) {
		ServidorSocketMensajeComando servidor = null;
		
		try {
			servidor = new ServidorSocketMensajeComando(3001);
			System.out.printf("[Servidor] Servidor aceptado en el puerto %d%n", servidor.port);
		} catch (IOException e) {
			System.out.printf("[Servidor] Problemas en la aceptación del puerto | Error: %s%n", e.getMessage());
		}
		
		Socket socket = null;
		while(true) {
			try {
				socket = servidor.server.accept();
				socketsAceptados++;
				System.out.printf("[Servidor] Sockets aceptados:%d%n", socketsAceptados);
				new Thread(new GestorSocketMensajeComando(socket)).start();
				
			} catch (IOException e) {
				System.out.printf("[Servidor] Problemas en la conexión con el cliente | Error: %s%n", e.getMessage());
			}
		}
	}
	
	

}

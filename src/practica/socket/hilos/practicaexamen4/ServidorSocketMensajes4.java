package practica.socket.hilos.practicaexamen4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class ServidorSocketMensajes4 {
	private int port;
	private ServerSocket server;
	private static AtomicLong socketsAceptados = new AtomicLong(0);
	
	
	public ServidorSocketMensajes4(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}


	public static AtomicLong getSocketsAceptados() {
		return socketsAceptados;
	}
	
	public static void main(String[] args) {
		ServidorSocketMensajes4 servidor = null;
		
		try {
			servidor = new ServidorSocketMensajes4(5053);
			System.out.printf("[Servidor] Conectado al puerto %d%n", servidor.port);
		} catch (IOException e) {
			System.out.printf("[Servidor] Problemas al conectar con el puerto | Error: %S%n", e.getMessage());
		}
		
		Socket socket = null;
		while(true) {
			try {
				socket = servidor.server.accept();
				socketsAceptados.getAndIncrement();
				System.out.printf("[Servidor] Socket %d aceptado %n", socketsAceptados.get());
				new Thread(new GestorServidorSocketMensaje4(socket)).start();
			} catch (IOException e) {
				System.out.printf("%n[Servidor] Problemas al conectar con el cliente | Error: %S%n", e.getMessage());
				break;
			}
			
		}
	}
	

}

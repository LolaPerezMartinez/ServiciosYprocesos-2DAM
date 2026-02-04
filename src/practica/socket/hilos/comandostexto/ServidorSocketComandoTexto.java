package practica.socket.hilos.comandostexto;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class ServidorSocketComandoTexto {
	private int port;
	private ServerSocket server;
	private static AtomicLong socketsAceptados = new AtomicLong(0);
	
	
	public ServidorSocketComandoTexto(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}


	public static AtomicLong getSocketsAceptados() {
		return socketsAceptados;
	}


	public static void main(String[] args) {
		ServidorSocketComandoTexto servidor = null;
		
		try {
			servidor = new ServidorSocketComandoTexto(2001);
			System.out.printf("[Servidor socket] Servidor conectado en el puerto %d.%n", servidor.port);
		} catch (IOException e) {
			System.out.printf("[Servidor socket] Problemas al conectar con el puerto | Error: %s.%n", e.getMessage());
		}
		
		Socket socket = null;
		while(true) {
			try {
				socket = servidor.server.accept();
				socketsAceptados.getAndIncrement();
				new Thread(new GestorSocketComandosTexto(socket)).start();
			} catch (IOException e) {
				System.out.printf("[Servidor socket] Problemas al conectar con el cliente | Error: %s.%n", e.getMessage());
				break;
			}
		}
	}


	
	
	

}

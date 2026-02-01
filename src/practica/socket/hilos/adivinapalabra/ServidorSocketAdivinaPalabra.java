package practica.socket.hilos.adivinapalabra;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocketAdivinaPalabra {
	private int port;
	private ServerSocket server;
	private static long socketAceptados = 0;
	
	
	public ServidorSocketAdivinaPalabra(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}
	
	public static long getSocketAceptados() {
		return socketAceptados;
	}

	public static void main(String[] args) {
		ServidorSocketAdivinaPalabra servidor = null;
		
		try {
			servidor = new ServidorSocketAdivinaPalabra(7001);
			System.out.printf("%n[Servidor] Servidor escuchando en el puerto %d%n", servidor.port);
		} catch (IOException e) {
			System.out.printf("%n[Servidor] Problemas en la creación del servidor | Error: %s%n", e.getMessage());
		}
		
		Socket socket = null;
		
		while(true) {
			
			try {
				socket = servidor.server.accept();
				socketAceptados++;
				System.out.printf("%n[Servidor] Sockets aceptados %d%n", socketAceptados);
				new Thread(new GestorServerSocketAdivinaPalabra(socket)).start();
			} catch (IOException e) {
				System.out.printf("%n[Servidor] Problemas en la conexión con el cliente | Error: %s%n", e.getMessage());
				break;
			}
		}
	}
	
	
	

}

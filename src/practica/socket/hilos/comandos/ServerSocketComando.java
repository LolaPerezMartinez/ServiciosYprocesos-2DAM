package practica.socket.hilos.comandos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketComando {
	private int port;
	private ServerSocket server;
	private static long peticionesAlservidor = 0;
	
	
	public static long getPeticionesAlservidor() {
		return peticionesAlservidor;
	}
	
	public ServerSocketComando(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}

	public static void main(String[] args) {
		ServerSocketComando servidor = null;
		
		try {
			servidor = new ServerSocketComando(7002);
			System.out.printf("[Servidor] Conectado en el puerto %d%n", servidor.port);
		} catch (IOException e) {
			System.out.printf("[Servidor] Problemas al conectarse al puerto | Error: %s%n", e.getMessage());
		}
		
		Socket socket = null;
		
		while(true) {
			try {
				socket = servidor.server.accept();
				peticionesAlservidor++;
				System.out.printf("%n[Servidor] Aceptando en el puerto %d | Peticiones al servidor: %d%n", servidor.port, peticionesAlservidor);
				new Thread(new GestorServerSocketComando(socket)).start();
			} catch (IOException e) {
				System.out.printf("%n[Servidor] Problemas con la conexión con el cliente | Error: %s%n", e.getMessage());
				break;
			}
			
		}
		
	}
	
	

}

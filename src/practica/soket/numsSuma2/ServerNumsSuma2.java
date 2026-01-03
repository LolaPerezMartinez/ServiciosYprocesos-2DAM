package practica.soket.numsSuma2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerNumsSuma2 {
	private int port;
	private ServerSocket serverSocket;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	
	
	public ServerNumsSuma2(int port) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
	}
	
	private void start() throws IOException {
		System.out.printf("[Servidor: %d] Estableciendo conexión...%n", port);
		socket = serverSocket.accept();
		
		System.out.printf("[Servidor: %d] Conexión establecida.%n", port);
		is = socket.getInputStream();
		os = socket.getOutputStream();
	}
	
	private void stop() throws IOException {
		System.out.printf("[Servidor: %d] Cerrando conexión...%n", port);
		is.close();
		os.close();
		socket.close();
		System.out.printf("[Servidor: %d] Conexión cerrada.%n", port);
		
	}
	public static void main(String[] args) {
		try {
			ServerNumsSuma2 s2 = new ServerNumsSuma2(4001);
			s2.start();
			System.out.printf("[Servidor: %d] Datos recibidos: ", s2.port);
			int suma = 0;
			int numTotales = s2.is.read();
			
			
			for (int i = 0; i < numTotales; i++) {
				int numero = s2.is.read();
				suma += numero;
				System.out.printf(" %d |", numero);
			}

			System.out.println();
			s2.os.write(suma);
			s2.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}

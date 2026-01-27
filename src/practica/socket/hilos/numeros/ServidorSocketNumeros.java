package practica.socket.hilos.numeros;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocketNumeros {
	private int port;
	private ServerSocket serverSocket;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	
	public ServidorSocketNumeros(int port) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
	}
	
	private void start() throws IOException {
		System.out.printf("[Servidor: %d] Estableciendo conexión...", port);
		serverSocket.accept();
		System.out.printf("[Servidor: %d] Conexión establecida.", port);
		is = socket.getInputStream();
		os = socket.getOutputStream();
	}
	
	private void stop() throws IOException {
		System.out.printf("[Servidor: %d] Finalizando conexión...", port);
		is.close();
		os.close();
		socket.close();
		System.out.printf("[Servidor: %d] Conexión finalizada.", port);
	}
	
}

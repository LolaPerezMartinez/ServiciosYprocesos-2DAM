package practica.socket.hilos.numeros;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSocketNumeros {
	private String address;
	private int port;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	
	
	public ClienteSocketNumeros(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	private void start() throws UnknownHostException, IOException {
		System.out.printf("[Cliente: %s:%s] Estableciendo conexión...", address, port);
		socket =  new Socket(address, port);
		System.out.printf("[Cliente: %s:%s] Conexión establecida.", address, port);
		
		is = socket.getInputStream();
		os = socket.getOutputStream();
		
	}
	
	private void stop() throws IOException {
		System.out.printf("[Cliente: %s:%s] Cerrando conexión...", address, port);
		is.close();
		os.close();
		socket.close();
		System.out.printf("[Cliente: %s:%s] Conexión cerrada.", address, port);
	}
	
	public static void main(String[] args) {
		
	}

}

package practica.soket.textoNC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTextoNC {
	private int port;
	private ServerSocket serverSocket;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	
	private PrintWriter pw;
	private InputStreamReader isr;
	private BufferedReader br;
	
	
	public ServerSocketTextoNC(int port) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
	}
	
	public void start() throws IOException {
		System.out.printf("[Servidor: %d] Estableciendo conexion...%n", port);
		socket = serverSocket.accept();
		System.out.printf("[Servidor: %d] Conexión establecida.%n", port);
		is = socket.getInputStream();
		os = socket.getOutputStream();
	}
	
	public void stop() throws IOException {
		System.out.printf("[Servidor: %d] Cerrando conexion...%n", port);
		is.close();
		os.close();
		socket.close();
		//Cerramos porque solo vamos a hacer una comunicación no necesitamos tener el server escuchando constantemente
		serverSocket.close();
		System.out.printf("[Servidor: %d] Conexión cerrada.%n", port);
	}
	
	public void abrirCanalesTexto() {
		System.out.printf("[Servidor: %d] Abriendo canales de texto...%n", port);
		
		pw = new PrintWriter(os, true);
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		
		System.out.printf("[Servidor: %d] Canales de texto abiertos.%n", port);
		
	}
	
	public void cerrarCanalesTexto() throws IOException {
		System.out.printf("[Servidor: %d] Cerrando canales de texto...%n", port);
		
		pw.close();
		br.close();
		isr.close();
		
		System.out.printf("[Servidor: %d] Canales de texto cerrados.%n", port);
	}
	
	
	
	public static void main(String[] args) {
		try {
			ServerSocketTextoNC s1 = new ServerSocketTextoNC(8080);
			s1.start();
			s1.abrirCanalesTexto();
			
			String palabraRecibida = s1.br.readLine();
			System.out.printf("[Servidor: %d] Palabra recibida: %s%n",s1.port, palabraRecibida);
			String palabraEnviada = palabraRecibida.toUpperCase();
			s1.pw.println(palabraEnviada);
			System.out.printf("[Servidor: %d] Palabra enviada: %s%n",s1.port, palabraEnviada);
			
			s1.cerrarCanalesTexto();
			s1.stop();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

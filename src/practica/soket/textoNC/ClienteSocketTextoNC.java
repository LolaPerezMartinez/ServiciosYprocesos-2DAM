package practica.soket.textoNC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClienteSocketTextoNC {
	private String address;
	private int port;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	
	private PrintWriter pw;
	private InputStreamReader isr;
	private BufferedReader br;
	
	
	public ClienteSocketTextoNC(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void start() throws UnknownHostException, IOException {
		System.out.printf("[Cliente: %s:%d] Solicitando conexión...%n", address, port);
		socket = new Socket(address, port);
		System.out.printf("[Cliente: %s:%d] Conexión establecida.%n", address, port);
		is = socket.getInputStream();
		os = socket.getOutputStream();
	}
	
	public void stop() throws IOException {
		System.out.printf("[Cliente: %s:%d] Cerrando conexión...%n", address, port);
		is.close();
		os.close();
		socket.close();
		System.out.printf("[Cliente: %s:%d] Conexión cerrada.%n", address, port);
	}
	
	public void abrirCanalesTexto() {
		System.out.printf("[Cliente: %s:%d] Abriendo canales de texto...%n", address, port);
		pw = new PrintWriter(os,true);
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		System.out.printf("[Cliente: %s:%d] Canales de texto abiertos.%n", address, port);
	}
	
	public void cerrarCanalesTexto() throws IOException {
		System.out.printf("[Cliente: %s:%d] Cerrando canales de texto...%n", address, port);
		pw.close();
		br.close();
		isr.close();
		System.out.printf("[Cliente: %s:%d] Canales de texto cerrados.%n", address, port);
	}
	
	public static void main(String[] args) {
		ClienteSocketTextoNC c1 = new ClienteSocketTextoNC("localhost", 8080);
		
		try {
			c1.start();
			c1.abrirCanalesTexto();
			
			String palabraEnviada = "hola";
			c1.pw.println(palabraEnviada);
			System.out.printf("[Cliente: %s:%d] Palabra enviada: %s%n", c1.address, c1.port, palabraEnviada);
			String palabraRecibida = c1.br.readLine();
			System.out.printf("[Cliente: %s:%d] Palabra recibida: %s%n", c1.address, c1.port, palabraRecibida);
			
			c1.cerrarCanalesTexto();
			c1.stop();
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

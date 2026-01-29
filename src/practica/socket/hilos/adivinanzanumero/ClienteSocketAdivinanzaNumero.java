package practica.socket.hilos.adivinanzanumero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSocketAdivinanzaNumero {
	private String address;
	private int port;
	private int intentos;
	
	
	public ClienteSocketAdivinanzaNumero(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void generarCliente() {
		try (Socket socket= new Socket(address, port);
			 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			
			for (int i = 1; i <= 1000; i++) {
				String numeroEnviado = Integer.toString(i);
				
				pw.println(numeroEnviado);
				
				String respuesta = br.readLine();
				intentos++;
				
				if("Acertaste".equals(respuesta)) {
					System.out.printf("%n[Cliente] Numero acertado | Numero de intentos: %d%n", intentos);
					break;
				}
			}
			
		} catch (UnknownHostException e) {
			System.out.printf("%n[Cliente] Problemas con el host | Error: %s%n", e.getMessage());
		} catch (IOException e) {
			System.out.printf("%n[Cliente] Problemas al conectar con el servidor | Error: %s%n", e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			ClienteSocketAdivinanzaNumero cliente = new ClienteSocketAdivinanzaNumero("localhost", 50050);
			cliente.generarCliente();
			
		}
	}


}

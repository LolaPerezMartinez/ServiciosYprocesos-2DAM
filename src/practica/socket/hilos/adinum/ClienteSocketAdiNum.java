package practica.socket.hilos.adinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClienteSocketAdiNum {
	private String address;
	private int port;
	private int intentos;
	
	
	public ClienteSocketAdiNum(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void generarCliente () {
		try (Socket socket =  new Socket(address, port);
			 InputStream is = socket.getInputStream();
			 OutputStream os = socket.getOutputStream();
			 PrintWriter pw = new PrintWriter(os, true);
			 BufferedReader br =  new BufferedReader(new InputStreamReader(is))){
			
			for (int lanzamiento = 0; lanzamiento < 1000; lanzamiento++) {
				String datoEnviado = Integer.toString(lanzamiento);
				pw.println(datoEnviado);
				intentos++;
				
				String respuesta = br.readLine();
				
				if("Acertaste".equals(respuesta)) {
					System.out.printf("%n[Cliente] Dato acertado después de %d intentos%n", intentos);
					break;
				}
			}
			
		}catch(UnknownHostException e) {
			 System.out.println("No se puede encontrar el host: " + e.getMessage());
			 
		}catch(IOException e) {
			System.out.println("Error de E/S: " + e.getMessage());
			
		}
		
	}
	
	public static void main(String[] args) {
		ClienteSocketAdiNum cliente = new ClienteSocketAdiNum("localhost", 3001);
		cliente.generarCliente();
	}

}

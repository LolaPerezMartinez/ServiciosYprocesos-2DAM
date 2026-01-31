package practica.socket.hilos.mandalista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteSocket {
	private String address;
	private int port;
	
	
	public ClienteSocket(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void jugar(String mensaje) {
		try (Socket socket = new Socket(address, port);
			 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			
			String datoEnviado = mensaje;
			pw.println(datoEnviado);
			System.out.printf("%nMensaje enviado: %s%n", datoEnviado);
			
			String respuesta = br.readLine();
			System.out.printf("Mensaje recibido: %s%n", respuesta);
			
		}catch (IOException e) {
			System.out.printf("%n[Cliente] Problemas al conectar con el servidor | Error: %s%n", e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		ClienteSocket c1 = new ClienteSocket("localhost", 4004);
		c1.jugar("#Numero aleatorio#2#11#");
		
		ClienteSocket c2 =  new ClienteSocket("localhost", 4004);
		c2.jugar("#Numero aleatorio#20#40#");
		
		ClienteSocket c3 = new ClienteSocket("localhost", 4004);
		c2.jugar("#Listado numeros#5#14#");
		
		ClienteSocket c4 = new ClienteSocket("localhost", 4004);
		c4.jugar("#Listado numeros#2#11#");
		
		ClienteSocket c5 = new ClienteSocket("localhost", 4004);
		c5.jugar("#Fin#");
		
		ClienteSocket c6 = new ClienteSocket("localhost", 4004);
		c6.jugar("#Numero aleatorio#2#11");
		
		ClienteSocket c7 = new ClienteSocket("localhost", 4004);
		c7.jugar("#Fin#2#");
		
		ClienteSocket c8 = new ClienteSocket("localhost", 4004);
		c8.jugar("#Numero aleatorio#2#");
		
		ClienteSocket c9 = new ClienteSocket("localhost", 4004);
		c9.jugar("#Numero aleatorio#2#k#");
		
		ClienteSocket c10 = new ClienteSocket("localhost", 4004);
		c10.jugar("#Numero aleatorio#2#10.5#");
		
		ClienteSocket c11 = new ClienteSocket("localhost", 4004);
		c11.jugar("#Dame aleatorio#2#15#");
	}

}

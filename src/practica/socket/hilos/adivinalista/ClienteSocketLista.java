package practica.socket.hilos.adivinalista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteSocketLista {
	private String address;
	private int port;
	
	public ClienteSocketLista(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void jugar(String mensaje) {
		try(Socket socket = new Socket(address, port);
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));){
			
			String datoEnviado = mensaje;
			pw.println(datoEnviado);
			System.out.printf("%nMensaje enviado: %s%n", datoEnviado);
			
			String respuesta = br.readLine();
			System.out.printf("Mensaje recibido: %s%n",respuesta);
			
		}catch (IOException e) {
			System.out.printf("%n[Cliente] Problemas al conectar con el servidor | Error: %s%n", e.getMessage());
		}
		
	}
	public static void main(String[] args) {
		ClienteSocketLista c1 = new ClienteSocketLista("localhost", 40040);
		c1.jugar("#Numero aleatorio#2#11#");
		
		ClienteSocketLista c2 =  new ClienteSocketLista("localhost", 40040);
		c2.jugar("#Numero aleatorio#20#40#");
		
		ClienteSocketLista c3 = new ClienteSocketLista("localhost", 40040);
		c2.jugar("#Listado numeros#5#14#");
		
		ClienteSocketLista c4 = new ClienteSocketLista("localhost", 40040);
		c4.jugar("#Listado numeros#2#11#");
		
		ClienteSocketLista c5 = new ClienteSocketLista("localhost", 40040);
		c5.jugar("#Fin#");
		
		ClienteSocketLista c6 = new ClienteSocketLista("localhost", 40040);
		c6.jugar("#Numero aleatorio#2#11");
		
		ClienteSocketLista c7 = new ClienteSocketLista("localhost", 40040);
		c7.jugar("#Fin#2#");
		
		ClienteSocketLista c8 = new ClienteSocketLista("localhost", 40040);
		c8.jugar("#Numero aleatorio#2#");
		
		ClienteSocketLista c9 = new ClienteSocketLista("localhost", 40040);
		c9.jugar("#Numero aleatorio#2#k#");
		
		ClienteSocketLista c10 = new ClienteSocketLista("localhost", 40040);
		c10.jugar("#Numero aleatorio#2#10.5#");
		
		ClienteSocketLista c11 = new ClienteSocketLista("localhost", 40040);
		c11.jugar("#Dame aleatorio#2#15#");
		
		
		
		
			
	}
	
	
	
}

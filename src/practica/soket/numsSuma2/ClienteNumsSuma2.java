package practica.soket.numsSuma2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class ClienteNumsSuma2 {
	private String address;
	private int port;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private int numerosEnviados;
	private List<Integer> listaNumerosRandom;

	
	private static RandomGenerator random = RandomGenerator.getDefault();
	
	public ClienteNumsSuma2(String address, int port, int numerosEnviados) {
		this.address = address;
		this.port = port;
		this.numerosEnviados = numerosEnviados <= 0 ? 1 : numerosEnviados > 10 ? 10 :  numerosEnviados;
		
		
		listaNumerosRandom = new ArrayList<>();
		for (int i = 0; i < numerosEnviados; i++) {
			listaNumerosRandom.add(random.nextInt(1, 101));
		}
			
	}
	
	private void start() throws UnknownHostException, IOException  {
		System.out.printf("[Cliente: %s:%d] Estableciendo conexión...%n", address, port);
		socket = new Socket(address, port);
		
		System.out.printf("[Cliente: %s:%d] Conexión establecida.%n", address, port);
		is = socket.getInputStream();
		os = socket.getOutputStream();
		
		
	}
	private void stop() throws IOException {
		System.out.printf("[Cliente: %s:%d] Cerrando conexión...%n", address, port);
		is.close();
		os.close();
		socket.close();
		System.out.printf("[Cliente: %s:%d] Conexión cerrada.%n", address, port);
	}
	
	public static void main(String[] args) {
		ClienteNumsSuma2 c2 = new ClienteNumsSuma2("localhost", 4001, 3);
		try {
			c2.start();
			c2.os.write(c2.listaNumerosRandom.size());
			System.out.printf("[Cliente: %s:%d] El cliente ha enviado los siguientes numeros: ", c2.address, c2.port);
			System.out.print("[");
			
			for (int i = 0; i < c2.listaNumerosRandom.size(); i++) {
				c2.os.write(c2.listaNumerosRandom.get(i));
				if(i <= c2.listaNumerosRandom.size() -2) {
					System.out.printf("%d, ", c2.listaNumerosRandom.get(i));
				}else {
					System.out.printf("%d", c2.listaNumerosRandom.get(i));
				}
			}
			System.out.printf("]%n");
			
			int datoRecibido = c2.is.read();
			System.out.printf("[Cliente: %s:%d] La suma de los números enviados es: %d.%n", c2.address, c2.port, datoRecibido);
			c2.stop();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

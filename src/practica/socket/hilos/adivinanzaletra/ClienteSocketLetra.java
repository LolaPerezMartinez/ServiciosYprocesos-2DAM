package practica.socket.hilos.adivinanzaletra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.random.RandomGenerator;

public class ClienteSocketLetra {
	private String address;
	private int port;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private PrintWriter pw;
	private InputStreamReader isr;
	private BufferedReader br;
	private int intentos;
	
	private static RandomGenerator random = RandomGenerator.getDefault();
	
	
	public ClienteSocketLetra(String address, int port) throws UnknownHostException, IOException {
		this.address = address;
		this.port = port;
		socket = new Socket(address, port);
	    os = socket.getOutputStream();
	    is = socket.getInputStream();
	    pw = new PrintWriter(os, true);
	    isr = new InputStreamReader(is);
	    br = new BufferedReader(isr);
	}
	
	public static void main(String[] args) {

	    for (int numeroclientes = 0; numeroclientes <= 10; numeroclientes++) {
	        ClienteSocketLetra cliente = null;

	        try {
	        	cliente = new ClienteSocketLetra("localhost", 7001);
	            ArrayList<String> letrasposibles = new ArrayList<>(Arrays.asList("a", "e", "i", "o", "u"));


	            for (int i = 0; i < letrasposibles.size(); i++) {
	                String letraRandom = letrasposibles.get(random.nextInt(0, letrasposibles.size()));

	                cliente.pw.println(letraRandom);
	                String datoRecibido = cliente.br.readLine();
	                cliente.intentos++;

	                if ("Acertaste".equals(datoRecibido)) {
	                    System.out.printf("[Cliente] Dato acertado (%s) después de %d intentos%n", letraRandom, cliente.intentos);
	                    break;
	                }
	            }

	            
	            cliente.pw.close();
	            cliente.br.close();
	            cliente.isr.close();
	            cliente.os.close();
	            cliente.is.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	  }
	}

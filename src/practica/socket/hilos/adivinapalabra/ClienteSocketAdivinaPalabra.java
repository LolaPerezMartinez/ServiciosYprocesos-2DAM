package practica.socket.hilos.adivinapalabra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class ClienteSocketAdivinaPalabra {
	private String address;
	private int port;
	private int intentos;
	private long id;
	private static long nextId = 1;
	private static RandomGenerator random =  RandomGenerator.getDefault();
	
	
	public ClienteSocketAdivinaPalabra(String address, int port) {
		this.address = address;
		this.port = port;
		id = nextId++;
	}
	
	public void generarIntentos() {
		
		try (Socket socket = new Socket(address, port);
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));){
			
			String [] palabrasDisponibles = JuegoPalabra.getListaPalabras();
			List<String> palabrasEnviadas = new ArrayList<>();
			String palabraRandom;
			String datoEnviado;
			
			while (intentos < palabrasDisponibles.length) {
			    palabraRandom = palabrasDisponibles[random.nextInt(0, palabrasDisponibles.length)];
			    if (palabrasEnviadas.contains(palabraRandom)) {
			        continue;
			    }

			    palabrasEnviadas.add(palabraRandom);
			    datoEnviado = palabraRandom;
			    pw.println(datoEnviado);
			    intentos++;
				
				String respuesta = br.readLine();
				
				if("Acertaste".equals(respuesta)) {
					System.out.printf("%n[Cliente %d] Palabra acertada: %s | Intentos: %d%n", id, datoEnviado, intentos);
					break;
				}
				if("Fallaste".equals(respuesta) && palabrasDisponibles.length == intentos) {
					System.out.printf("%n[Cliente %d] GAME OVER | Intentos: %d%n", id, intentos);
					break;
				}
			}
			
		} catch (UnknownHostException e) {
			System.out.printf("%n[Cliente] Problemas con el host | Error: %s%n", e.getMessage());
		} catch (IOException e) {
			System.out.printf("%n[Cliente] Problemas en la comunicacion con el servidor | Error: %s%n", e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		ClienteSocketAdivinaPalabra c1 = new ClienteSocketAdivinaPalabra("localhost", 7001);
		c1.generarIntentos();
		
		ClienteSocketAdivinaPalabra c2 = new ClienteSocketAdivinaPalabra("localhost", 7001);
		c2.generarIntentos();
		
		ClienteSocketAdivinaPalabra c3 = new ClienteSocketAdivinaPalabra("localhost", 7001);
		c3.generarIntentos();
		
		ClienteSocketAdivinaPalabra c4 = new ClienteSocketAdivinaPalabra("localhost", 7001);
		c4.generarIntentos();
	}

}

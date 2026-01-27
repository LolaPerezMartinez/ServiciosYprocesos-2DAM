package practica.socket.hilos.adivinanzas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * La clase ServidorTCP implementa un servidor TCP básico. Esta clase es
 * responsable de aceptar conexiones de socket de clientes y de iniciar un nuevo
 * hilo para manejar cada conexión.
 */
public class ServidorTCP {
	private int port;
	private ServerSocket server;
	private static long socketAceptados;
	
	/**
	 * Obtiene el número total de conexiones de socket aceptadas por este servidor.
	 * 
	 * @return El número de sockets aceptados.
	 */
	public static long getSocketAceptados() {
		return socketAceptados;
	}


	/**
	 * Constructor de ServidorTCP.
	 * 
	 * @param port El puerto en el que el servidor escuchará las conexiones
	 *             entrantes.
	 * @throws IOException Si ocurre un error de E/S al abrir el puerto del
	 *                     servidor.
	 */
	
	public ServidorTCP(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
	}
	
	/**
	 * Punto de entrada principal del programa. Este método inicia el servidor en el
	 * puerto 8081. Entra en un bucle infinito para aceptar y manejar conexiones de
	 * clientes. Maneja las excepciones de E/S relacionadas con la creación del
	 * servidor y la aceptación de conexiones.
	 */
	public static void main(String[] args) {
		ServidorTCP servidor = null;
		
		try {
			servidor = new ServidorTCP(4000);
		}catch(IOException e) {
			e.printStackTrace();
			return; // si algo ha ido mal finaliza el método
		}
		
		while(true) {
			Socket miSocket = null;
			try {
				miSocket = servidor.server.accept();
				System.out.printf("[Servidor : %d]Socket aceptado.%n", servidor.port);
				socketAceptados++;
				//AQUI COMIENZA EL HILO
				new Thread(new GestorSocketServer(miSocket)).start();
			}catch (IOException e) {
				e.printStackTrace();
				return; // si algo ha ido mal finaliza el método
			}
		}
		
		
		
		
		
		
	}
	
	

}

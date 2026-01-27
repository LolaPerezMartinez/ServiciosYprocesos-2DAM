package practica.socket.hilos.adivinanzas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

/**
 * GestorSocketServer es una clase que implementa la interfaz Runnable
 * para manejar la comunicación con un cliente en un hilo separado.
 * Esta clase se encarga de abrir los streams de entrada y salida,
 * leer los datos enviados por el cliente, procesarlos y enviar una respuesta.
 */
public class GestorSocketServer implements Runnable{
	private Socket socket;
	private JuegoAdivinaNumero juego;
	private OutputStream os;
	private InputStream is;
	private InputStreamReader isr;
	private PrintWriter pw;
	private BufferedReader br;
	
	/*AtomicLong es una clase de Java que sirve para guardar y modificar un número (long) de forma segura 
	 * cuando hay varios hilos (threads) trabajando a la vez.*/
	private static AtomicLong peticionesAlServidor = new AtomicLong(0);
	
	public GestorSocketServer(Socket socket) {
		this.socket = socket;
		//la sesión de un cliente concreto
		//10 clientes → 10 gestores → 10 juegos distintos
		juego = new JuegoAdivinaNumero();
	}
	
	/**
     * Método run que se ejecuta cuando el hilo del servidor se inicia.
     * Este método configura los streams de comunicación y procesa los mensajes
     * recibidos del cliente. Responde a cada mensaje y cierra la conexión
     * cuando se completa la interacción.
     */
	@Override
	public void run() {
		 // Configuración de streams y manejo de la comunicación
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
			pw = new PrintWriter(os, true);
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
		}catch(IOException e) {
			System.out.printf("[Gestor Sockets] Error abriendo streams del socket.%n");
			 return; // Finaliza el método si no se pueden abrir los streams
			
		}
		
		 // Bucle de procesamiento de mensajes
		while(true) {
			String datoLeido;
			try {
				//Si el cliente envía una línea → devuelve un String
				datoLeido = br.readLine();
				//Obtiene el valor e incrementa
				peticionesAlServidor.getAndIncrement();
				if(datoLeido != null) {
					//respuesta guarda exactamente el mensaje que devuelve el juego al comprobar el número que ha enviado el cliente
					String respuesta = juego.verificarIntento(datoLeido);
					//manda al cliente la respuesta: 🎉¡Has acertado!🎉" || "Lo siento, no has acertado" || "Respuesta no válida"
					pw.println(respuesta);
					//Cuando un cliente gana, muestro por pantalla cuántos clientes han pasado por el servidor y cuántas peticiones se han procesado en total
					if("🎉¡Has acertado!🎉".equals(respuesta)) {
						System.out.printf("(Gestor sockets) %d sockets aceptados y %d peticiones recibidas%n",
											//.get es un método de AtomicLong
											ServidorTCP.getSocketAceptados(), peticionesAlServidor.get());
						 break; // Salir del bucle si el cliente acierta el número
					}
					
				//Si el cliente cierra el socket → devuelve null
				//Es una señal normal de “el otro lado se ha ido”
				}else {
					break;// Salir del bucle si el cliente cierra la conexión
				}
			}catch (IOException e) {
				 System.out.println("(Gestor sockets) Error leyendo el buffered reader");
	                break; // Salir del bucle en caso de error de E/S
			}
		}
		
		//Cierre de recursos
		try {
			pw.close();
            br.close();
            isr.close();
            is.close();
            os.close();
		}catch(IOException e) {
			System.out.println("(Gestor sockets) Error cerrando streams del socket");
		}
		
		
		
		
	}

}

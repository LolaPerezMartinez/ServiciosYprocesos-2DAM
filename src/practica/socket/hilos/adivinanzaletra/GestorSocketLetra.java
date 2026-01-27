package practica.socket.hilos.adivinanzaletra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorSocketLetra implements Runnable {
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private PrintWriter pw;
	private InputStreamReader isr;
	private BufferedReader br;
	private JuegoAdivinaLetra juego;

	private static AtomicLong peticionesAlServidor = new AtomicLong(0);

	public GestorSocketLetra(Socket socket) {
		this.socket = socket;
		juego = new JuegoAdivinaLetra();
	}

	@Override
	public void run() {
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
			pw = new PrintWriter(os, true);
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

		} catch (IOException e) {
			System.out.printf("%n[GestorSockets] Problemas al abrir stream.%n");
			return;
		}

		while (true) {
			String datoLeido;
			try {
				datoLeido = br.readLine();
				peticionesAlServidor.getAndIncrement();
				if (datoLeido != null) {
					String respuesta = juego.verificarIntento(datoLeido);

					pw.println(respuesta);

					if ("Acertaste".equals(respuesta)) {
						System.out.printf("%n[Gestor de sockets] Sockets aceptados: %d | Peticiones recibidas: %d.%n",
								ServerSocketLetra.getSocketsAceptados(), peticionesAlServidor.get());
						break;
					}
				} else {
					System.out.printf("%n[Gestor de sockets] Cliente cerró la conexión.%n");
					break;
				}
			} catch (IOException e) {
				System.out.printf("%n[Gestor de sockets] Error al leer con bufferedReader.%n");
				break;
			}
		}
	}

}

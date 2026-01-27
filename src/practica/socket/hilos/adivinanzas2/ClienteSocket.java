package practica.socket.hilos.adivinanzas2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSocket {
	private String address;
	private int port;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private PrintWriter pw;
	private InputStreamReader isr;
	private BufferedReader br;
	private int intentos;

	public ClienteSocket(String address, int port) throws UnknownHostException, IOException {
		this.address = address;
		this.port = port;
		socket = new Socket(address, port);
		is = socket.getInputStream();
		os = socket.getOutputStream();
		pw = new PrintWriter(os, true);
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
	}

	public static void main(String[] args) {
		ClienteSocket c1;

		for (int clientes = 0; clientes < 10; clientes++) {
			try {
				c1 = new ClienteSocket("localhost", 5001);
				for (int i = 1; i <= 1000; i++) {
					String datoEnviado = Integer.toString(i);
					c1.pw.println(datoEnviado);

					String datoRecibido = c1.br.readLine();

					if ("Acierto".equals(datoRecibido)) {
						System.out.printf("%n[Cliente] Respuesta correcta. Numero de intentos: %d.%n", c1.intentos);
						break;
					}

				}

				c1.pw.close();
				c1.br.close();
				c1.isr.close();
				c1.os.close();
				c1.is.close();
			} catch (IOException e) {

			}
		}

	}

}

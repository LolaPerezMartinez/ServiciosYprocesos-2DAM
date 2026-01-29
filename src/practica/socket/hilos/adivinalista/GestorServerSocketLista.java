package practica.socket.hilos.adivinalista;

import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class GestorServerSocketLista implements Runnable{
	private Socket socket;
	private JuegoLista juego;
	private static AtomicLong peticionesAlServidor = new AtomicLong(0);
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

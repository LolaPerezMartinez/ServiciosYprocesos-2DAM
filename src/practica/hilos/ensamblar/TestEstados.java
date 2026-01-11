package practica.hilos.ensamblar;

import java.util.ArrayList;
import java.util.List;

public class TestEstados {
	public static void main(String[] args) {
		List<Thread.State> state = new ArrayList<>();
		RobotR rr1 = new RobotR("R-1", 8);
		
		Thread hilo = new Thread(rr1);
		
		System.out.printf("COMIENZAN LOS ESTADOS DE %s%n", hilo.getName());
		state.add(hilo.getState());
		
		hilo.start();
		state.add(hilo.getState());
		
		while(hilo.isAlive()) {
			state.add(hilo.getState());
			System.out.printf("%nHilo %s || Estado %s%n", hilo.getName(), hilo.getState());
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		state.add(hilo.getState());
		System.out.printf("%nLISTA DE ESTADOS: %s", state);
		System.out.printf("%nLA LONGITUD DE LA LISTA ES DE %d", state.size());
	}
	

}

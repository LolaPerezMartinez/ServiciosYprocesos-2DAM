package practica.hilos.cintatransportadora;

import java.util.ArrayList;
import java.util.List;

public class TestEstados {
	public static void main(String[] args) {
		CintaTransportadoraR cr1 = new CintaTransportadoraR("1", 10);
		Thread h1 = new Thread(cr1);
		List<Thread.State> state = new ArrayList<>();
		
		//System.out.printf("Hilo [%s] || Estado: %s%n", h1.getName(), h1.getState());
		state.add(h1.getState());
		
		h1.start();
		while(h1.isAlive()){
			try {
				System.out.printf("Hilo [%s] || Estado: %s%n", h1.getName(), h1.getState());
				state.add(h1.getState());
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		state.add(h1.getState());
		
		System.out.printf("%nLISTA DE ESTADOS: %s%n", state);
		System.out.printf("Longitud de lista de estados: %d.%n", state.size());
	}

}

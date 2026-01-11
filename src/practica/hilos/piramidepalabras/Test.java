package practica.hilos.piramidepalabras;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		PiramidePalabra p1 = new PiramidePalabra("Hola");
		List<Thread.State> state = new ArrayList<>();
		Thread t1 =	new Thread(p1);
		
		state.add(t1.getState());
		System.out.printf(t1.getName());
		t1.start();
		while(t1.isAlive()) {
			try {
				state.add(t1.getState());
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		state.add(t1.getState());
		System.out.println(state);
	}
	
}

package practica.countdown;

import java.util.ArrayList;
import java.util.List;

public class TestListaEstados {
	public static void main(String[] args) {
		
		System.out.printf("%nCrear lista de estados%n");
		System.out.printf("-----------------------%n");
		
		List<Thread.State> states = new ArrayList<>();
		Thread.State state;

		Thread t3 = new Thread(new CountDown("C3", 7));
		
		/*state1 = t3.getState();
		states.add(state1);*/
		//System.out.print(states);
		
		/*t3.start();
		Thread.State state2;
		state2 = t3.getState();
		states.add(state2);
		System.out.print(states);*/
		
		state = t3.getState();
		states.add(state);
		t3.start();
		while(t3.isAlive()) {
			state = t3.getState();
			states.add(state);
			try {
				Thread.sleep(CountDown.getMilis()/2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println();
		}
		System.out.printf("%n%s | Estado: %s%n", t3.getName(), t3.getState());
		states.add(t3.getState());
		
		System.out.printf("%nEstados por los que pasó el hilo%n");
		states.forEach(System.out::println);
		System.out.printf("Estados almacenados: %d%n", states.size());
	}
}

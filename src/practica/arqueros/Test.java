package practica.arqueros;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args) {
		Arquero a1 = new Arquero("1", 5);
		Arquero a2 = new Arquero("2", 8);
		
		ArqueroT at1 = new ArqueroT("3", 4);
		ArqueroT at2 = new ArqueroT("4", 6);
		
		ArqueroR ar1 = new ArqueroR("5", 3);
		ArqueroR ar2 = new ArqueroR("6", 9);
		
		LanzadorArquero la1 = new LanzadorArquero(new Arquero("7", 4));
		LanzadorArquero la2 = new LanzadorArquero(new Arquero("8", 6));
		
		ArqueroR ar3 = new ArqueroR("9", 3);
		ArqueroR ar4 = new ArqueroR("10", 9);
		
//		System.out.printf("%nMONOHILO%n");
//		a1.disparar();
//		a2.disparar();
//		
//		System.out.printf("%nMULTIHILO CON THREAD%n");
//		at1.start();
//		at2.start();
//		
//		System.out.printf("%nMULTIHILO CON RUNNABLE%n");
//		new Thread(ar1).start();
//		new Thread(ar2).start();
//		
//		System.out.printf("%nMULTIHILO CON LANZADOR%n");
//		new Thread(la1).start();
//		new Thread(la2).start();
		
//		System.out.printf("%nEXECUTORS%n");
//		ExecutorService ex1 = Executors.newFixedThreadPool(3);
//		ex1.execute(ar1);
//		ex1.execute(ar2);
//		ex1.execute(ar3);
//		ex1.execute(ar4);
//		
//		ex1.shutdown();
//		
		List<Thread.State> state = new ArrayList<>();
		Thread hilo = new Thread(ar4);
		
		state.add(hilo.getState());
		
		hilo.start();
		while(hilo.isAlive()) {
			try {
				//System.out.printf("%n%s -> Estado: %s%n", hilo.getName(), hilo.getState());
				state.add(hilo.getState());
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		state.add(hilo.getState());
		
		System.out.printf("%nLista de estados de hilo %s: %s%n",hilo.getName(), state);
		System.out.printf("%nlongitud de lista de estados: %d%n", state.size());
		
	
		
		
	}

}

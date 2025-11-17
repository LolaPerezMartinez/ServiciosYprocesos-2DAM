package practica.lanzabolas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args) {
		Lanzabolas l1 = new Lanzabolas("L-1");
		Lanzabolas l2 = new Lanzabolas("L-2");
		Lanzabolas l3 = new Lanzabolas("L-3");
				
//		ExecutorService ex1 = Executors.newFixedThreadPool(2);
//		ex1.execute(l1);
//		ex1.execute(l2);
//		ex1.execute(l3);
		
//		ExecutorService ex2 = Executors.newSingleThreadExecutor();
//		ex2.execute(l1);
//		ex2.execute(l2);
//		ex2.execute(l3);
		
		ExecutorService ex3 = Executors.newFixedThreadPool(9);
		
		for (int i = 0; i < 3; i++) {
			ex3.execute(l1);
			ex3.execute(l2);
			ex3.execute(l3);
		}
	}
}

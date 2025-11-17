package executors.bolas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Run {
	public static void main(String[] args) {
		Lanzabolas l1 = new Lanzabolas("L-1");
		Lanzabolas l2 = new Lanzabolas("L-2");
		Lanzabolas l3 = new Lanzabolas("L-3");
		
		ExecutorService e1 = Executors.newFixedThreadPool(3);
		ExecutorService e2 = Executors.newSingleThreadExecutor();
		ExecutorService e3 = Executors.newFixedThreadPool(9);
		
		/*System.out.printf("%nExecutors 1%n");
		e1.execute(l1);
		e1.execute(l2);
		e1.execute(l3);
		e1.execute(l1);
		
		System.out.printf("%nExecutors 2%n");
		e2.execute(l1);
		e2.execute(l2);
		e2.execute(l3);*/
		
		System.out.printf("%nExecutors 3%n");
		for (int i= 0 ; i < 3; i++) {
			e3.execute(l1);
			e3.execute(l2);
			e3.execute(l3);
		}
		
		e3.shutdown();
		
		
	}

}

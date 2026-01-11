package practica.hilos.ensamblar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args) {
		Robot r1 = new Robot("R-1", 5);
		Robot r2 = new Robot("R-2", 7);
		
		RobotT rt1 = new RobotT("R-3", 6);
		RobotT rt2 = new RobotT("R-4", 9);
		
		RobotR rr1 = new RobotR("R-5", 4);
		RobotR rr2 = new RobotR("R-6", 8);
		RobotR rr3 = new RobotR("R-7", 6);
		RobotR rr4 = new RobotR("R-8", 3);
		
//		System.out.printf("%n-MONOHILO-%n");
//		r1.ensamblar();
//		r2.ensamblar();
//		
//		System.out.printf("%n-MULTIHILO CON THREAD-%n");
//		rt1.start();
//		rt2.start();
//		
//		System.out.printf("%n-MULTIHILO CON RUNNABLE-%n");
//		new Thread(rr1).start();
//		new Thread(rr2).start();
		
		System.out.printf("%n-EXECUTORS-%n");
		ExecutorService ex1 = Executors.newFixedThreadPool(3);
		
		ex1.execute(rr1);
		ex1.execute(rr2);
		ex1.execute(rr3);
		ex1.execute(rr4);
	}

}

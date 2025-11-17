package practica.countdown;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		CountDown c1 = new CountDown("C1", 5);
		CountDown c2 = new CountDown("C2", 8);
		
		Thread t1 = new Thread(c1);
		Thread t2 = new Thread(c2);
		
		System.out.printf("%nHilos sin iniciar%n");
		System.out.printf("------------------%n");
		System.out.printf("%s | Estado: %s%n", t1.getName(), t1.getState());
		System.out.printf("%s | Estado: %s%n", t2.getName(), t1.getState());
		
		System.out.printf("%nHilos iniciados%n");
		System.out.printf("------------------%n");
		t1.start();
		t2.start();
		System.out.printf("%s | Estado: %s%n", t1.getName(), t1.getState());
		System.out.printf("%s | Estado: %s%n", t1.getName(), t1.getState());
		System.out.printf("%s | Estado: %s%n", t2.getName(), t2.getState());
		
		System.out.printf("%nEstados hasta que el hilo termine%n");
		System.out.printf("------------------------------------%n");
		while(t1.isAlive()) {
			System.out.printf("%s | Estado: %s%n", t1.getName(), t1.getState());
			try {
				Thread.sleep(CountDown.getMilis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("%s | Estado: %s%n", t1.getName(), t1.getState());
		
		
		
		
		
	}

}

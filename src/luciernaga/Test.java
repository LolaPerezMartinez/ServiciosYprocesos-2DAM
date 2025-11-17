package luciernaga;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args) {
		Luciernaga l1 = new Luciernaga("L-1", 10);
		Luciernaga l2 = new Luciernaga("L-2", 15);
		Luciernaga l3 = new Luciernaga("L-3", 5);
		
		System.out.printf("%nLuciérnagas sin multihilo");
		System.out.printf("%n-------------------------%n");
		System.out.println(l1.toString());
		System.out.println(l2.toString());
		System.out.println(l3.toString());
		
		l1.enciende();
		//l2.enciende();
		l3.enciende();
		
		
		//System.out.println(l1.toString());
		//System.out.println(l2.toString());
		//System.out.println(l3.toString());
		
		System.out.printf("%nLuciérnagas con Thread");
		System.out.printf("%n----------------------%n");
		LuciernagaT lt1 = new LuciernagaT("LT-1", 13);
		LuciernagaT lt2 = new LuciernagaT("LT-2", 15);
		LuciernagaT lt3 = new LuciernagaT("LT-3", 5);
		
		lt1.start();
		lt2.start();
		lt3.start();
		
		System.out.printf("%nLuciérnagas con Runnable");
		System.out.printf("%n-------------------------%n");
		LuciernagaR lr1 = new LuciernagaR("LR-1", 6);
		LuciernagaR lr2 = new LuciernagaR("LR-2", 8);
		LuciernagaR lr3 = new LuciernagaR("LR-3", 10);
		
		new Thread(lr1).start();
		new Thread(lr2).start();
		new Thread(lr3).start();
		
		System.out.printf("%nPool de 3 hilos%n");
		ExecutorService exe1 = Executors.newFixedThreadPool(3);
		exe1.execute(lr1);
		exe1.execute(lr2);
		exe1.execute(lr3);
		
		
	}

	

}

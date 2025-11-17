package practica.luciernaga;

public class Test {
	public static void main(String[] args) {
		Luciernaga l1 = new Luciernaga("L-1", 10);
		Luciernaga l2 = new Luciernaga("L-2", 5);
		Luciernaga l3 = new Luciernaga("L-3", 9);
		
		LuciernagaT lt1 = new LuciernagaT("L-1", 10);
		LuciernagaT lt2 = new LuciernagaT("L-2", 7);
		LuciernagaT lt3 = new LuciernagaT("L-3", 9);
		
		LuciernagaR lr1 = new LuciernagaR("L-1", 10);
		LuciernagaR lr2 = new LuciernagaR("L-2", 7);
		LuciernagaR lr3 = new LuciernagaR("L-3", 9);
		
		
		
		//l2.enciende();
		/*System.out.println("Monohilo");
		l1.enciende();
		l2.enciende();
		l3.enciende();*/
		
		/*System.out.println("Multihilo con thread");
		lt1.start();
		lt2.start();
		lt3.start();*/
		
		System.out.println("Multihilo con Runnable");
		new Thread(lr1).start();
		new Thread(lr2).start();
		new Thread(lr3).start();
	}

}

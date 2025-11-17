package practica.botella;

public class Test {
	public static void main(String[] args) {
		BotellaThread bt1 =new BotellaThread("B-1", '⁘', 6);
		BotellaThread bt2 =new BotellaThread("B-2", '※', 10);
		BotellaThread bt3 =new BotellaThread("B-3", '⁓', 7);
		System.out.printf("%nBotella extends Threads%n");
		bt1.start();
		bt2.start();
		bt3.start();
		
		System.out.println();
		
		BotellaRunnable br1 = new BotellaRunnable("B-1", '⁘', 6);
		BotellaRunnable br2 = new BotellaRunnable("B-2", '※', 10);
		BotellaRunnable br3 = new BotellaRunnable("B-3", '⁓', 7);
		System.out.printf("%nBotella implements Runnable%n");
		new Thread(br1).start();;
		new Thread(br2).start();;
		new Thread(br3).start();;
		
		System.out.printf("%nLanzador Vaciar Botella extends Threads%n");
		LanzadorVaciarBotella lb1 = new LanzadorVaciarBotella(new Botella("B-1", '⁘', 6));
		LanzadorVaciarBotella lb2 = new LanzadorVaciarBotella(new Botella("B-2", '※', 10));
		LanzadorVaciarBotella lb3 = new LanzadorVaciarBotella(new Botella("B-3", '⁓', 7));
		lb1.start();
		lb2.start();
		lb3.start();
	}

}

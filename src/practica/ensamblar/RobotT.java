package practica.ensamblar;

public class RobotT extends Thread{
	

	private int id;
	private String modelo;
	private int piezasTotales;
	private static int nextId = 1;
	
	private static long milis = 400;

	public RobotT(String modelo, int piezasTotales) {
		this.modelo = modelo;
		this.piezasTotales = piezasTotales;
		id = nextId++;
	}
	
	public void ensamblar() {
		System.out.printf("%nEl robot modelo %s comienza a ensamblar.%n", modelo);
		for (int i = 1; i <= piezasTotales; i++) {
			System.out.printf("%nEl robot modelo %s ensambla pieza %d.%n", modelo, i);
			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("%nEl robot modelo %s termina de ensamblar.%n", modelo);
	}
	
	@Override
	public void run() {
		ensamblar();
	}

}

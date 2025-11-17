package practica.cintatransportadora;

public class CintaTransportadora {
	private String id;
	private int ciclos;
	
	
	private static long milis = 400;

	public CintaTransportadora(String id, int ciclos) {
		this.id = id;
		this.ciclos = ciclos < 0 ? 0 : ciclos;
		
		
	}
	
	public void mover () {
		long tiempo = System.currentTimeMillis();
		System.out.printf("%nCinta [id:%s] iniciada.%n", id);
		for (int i = 1; i <= ciclos; i++) {
			try {
				System.out.printf("Cinta [id:%s] || Ciclos: %d%n", id, i);
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.printf("%nCinta [id:%s] acabó sus ciclos.%n", id);
		System.out.printf("%nCinta [id:%s] tardó %d.%n", id, tiempo);
		
	}
	
	

}

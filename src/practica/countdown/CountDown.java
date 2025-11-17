package practica.countdown;

public class CountDown implements Runnable{
	private String nombre;
	private int numeroInicio;
	private static long milis = 600;
	
	
	public CountDown(String nombre, int numeroInicio) {
		this.nombre = nombre;
		this.numeroInicio = numeroInicio;
	}


	@Override
	public void run() {
		System.out.printf("Empieza la cuenta atrás de %s%n", nombre);
		for (int i = numeroInicio; i > 0; i--) {
			System.out.printf("[%s] numero: %d%n",nombre, i);
			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
			}
		}
		System.out.printf("%nAcaba la cuenta atras de %s%n", nombre);
		
	}


	public static long getMilis() {
		return milis;
	}
}

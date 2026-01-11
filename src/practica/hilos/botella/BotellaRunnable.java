package practica.hilos.botella;

public class BotellaRunnable implements Runnable{
	private String nombre;
	private char caracter;
	private int cantidad;
	private static long milis = 300;
	
	

	public BotellaRunnable(String nombre, char caracter, int cantidad) {
		this.nombre = nombre;
		this.caracter = caracter;
		this.cantidad = cantidad;
	}



	@Override
	public void run() {
		while(cantidad > 0) {
			System.out.print(caracter);
			cantidad--;
			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	

}

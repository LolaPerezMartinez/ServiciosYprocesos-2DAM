package practica.botella;

public class BotellaThread extends Thread{
	private String nombre;
	private char caracter;
	private int cantidad;
	private static long milis= 300;
	
	
	public BotellaThread(String nombre, char caracter, int cantidad) {
		this.nombre = nombre;
		this.caracter = caracter;
		this.cantidad = cantidad;
	}
	
	public void vaciar() {
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

	@Override
	public void run() {
		vaciar();
	}

	@Override
	public String toString() {
		return "BotellaThread [nombre=" + nombre + ", caracter=" + caracter + ", cantidad=" + cantidad + "]";
	}
	
	
}

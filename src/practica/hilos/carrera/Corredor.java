package practica.hilos.carrera;

public class Corredor extends Thread{
	private String nombre;
	private static long velocidad = 250;
	
	
	
	
	public Corredor(String nombre) {
		this.nombre = nombre;
	}
	
	
	public void run() {
		long inicio = System.currentTimeMillis();
		for (int i = 0; i < 50; i+=10) {
			System.out.printf("%s ha avanzado %d metros%n", nombre, i);
			try {
				Thread.sleep(velocidad);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("%s ha llegado a la meta!%n", nombre);
		long fin = System.currentTimeMillis();
		System.out.printf("%s llegó a la meta en %d milisegundos.%n", nombre, fin-inicio);
	}

}

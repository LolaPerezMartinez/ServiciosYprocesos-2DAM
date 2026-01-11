package practica.hilos.fabrica;

public class Robot implements Runnable{
	private String nombre;
	private static long ensamblaje = 300;
	
	public Robot(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		long inicio = System.currentTimeMillis();
		System.out.printf("%nEl robot %s comenzó a ensamblarse.%n", nombre);
		try {
			Thread.sleep(ensamblaje);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%n¡El robot %s ha terminado de ensamblarse!%n", nombre);
		
		long fin = System.currentTimeMillis();
		
		System.out.printf("%nEl tiempo de ensamblaje del robot %s ha sido de %d milisegundos.%n", nombre, fin -inicio);
	}

}

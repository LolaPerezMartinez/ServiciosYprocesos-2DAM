package practica.hilos.cajero;

public class Cajero extends Thread{
	private String nombre;
	private static int mili = 100;
	
	
	public Cajero(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public void run() {
		int numTotalClientes = 5;
		
		for (int i = 0; i < numTotalClientes; i++) {
			System.out.printf("%s comienza a atender al cliente 1%n", nombre);
			try {
				Thread.sleep(mili);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("%s termina a atender al cliente 1%n", nombre);
		}
		System.out.printf("%nTodos los cajeros han terminado su trabajo.%n");
	}
	
	
	

}

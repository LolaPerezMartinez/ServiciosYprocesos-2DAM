package practica.hilos.fabrica;

public class Fabrica {
	public static void main(String[] args) {
		Robot r1 = new Robot("Astra-9");
		Robot r2 = new Robot("Kibo");
		Robot r3 = new Robot("Volt-X");
		
		new Thread(r1).start();
		new Thread(r2).start();
		new Thread(r3).start();
	}

}

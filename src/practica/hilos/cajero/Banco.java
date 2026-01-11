package practica.hilos.cajero;

public class Banco {
	public static void main(String[] args) {
		Cajero c1 = new Cajero("Cajero 1");
		Cajero c2 = new Cajero("Cajero 2");
		Cajero c3 = new Cajero("Cajero 3");
		c1.start();
		c2.start();
		c3.start();
	}

}

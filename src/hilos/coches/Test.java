package hilos.coches;

public class Test {
	public static void main(String[] args) {
		Coches c1 = new Coches("Toyota", 7);
		Coches c2 = new Coches("Dacia", 8);
		Coches c3 = new Coches("BMW", 10);
		
		new Thread(c1).start();
		new Thread(c2).start();
		new Thread(c3).start();
	}

}

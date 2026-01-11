package practica.hilos.carrera;

public class Carrera {
	public static void main(String[] args) {
		Corredor c1 = new Corredor("Paula");
		Corredor c2 = new Corredor("Pablo");
		Corredor c3 = new Corredor("Ana");
		
		c1.start();
		c2.start();
		c3.start();
	}

}

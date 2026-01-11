package practica.hilos.luciernagalanzadera;

public class Test {
	public static void main(String[] args) {
		LuciernagaLanzadera l1 = new LuciernagaLanzadera(new Luciernaga("L-1", 10));
		
		l1.start();
	}

}

package practica.hilos.luciernaga2;

public class Test {
	public static void main(String[] args) {
		Luciernaga l1 = new Luciernaga("L-1", 5);
		Luciernaga l2 = new Luciernaga("L-2", 8);
		
		l1.enciende();
		l2.enciende();
	}

}

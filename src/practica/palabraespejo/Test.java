package practica.palabraespejo;

public class Test {
public static void main(String[] args) {
	PalabraEspejo p1 = new PalabraEspejo("Solar".toUpperCase());
	
	new Thread(p1).start();
}
}

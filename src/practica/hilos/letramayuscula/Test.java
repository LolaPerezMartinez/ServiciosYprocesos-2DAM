package practica.hilos.letramayuscula;

public class Test {
	public static void main(String[] args) {
		LetraMayusculaSB p1 = new LetraMayusculaSB("Hello");
		
		LetraMayuscula p2 = new LetraMayuscula("World");
		
		//new Thread(p1).start(); 
		
		new Thread(p2).start();
	}

}

package practica.socket.hilos.adivinapalabra;

public class Prueba {
	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			System.out.println(i); 
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}

package concurrencia.contador;


public class ContadorRoto {
	private int vendidas = 0;
	
	public void venderEntradas() {
		vendidas++;
	}

	public int getVendidas() {
		return vendidas;
	}
	
	public static void main(String[] args) throws InterruptedException {
		ContadorRoto contador = new ContadorRoto();
		Thread [] cajeros = new Thread[5];
		
		for (int i = 0; i < 5; i++) {
			cajeros[i] = new Thread(() -> {
				for (int j = 0; j < 1000; j++) {
					contador.venderEntradas();
				}
			});
			cajeros[i].start();
		}
		for (Thread cajero : cajeros) {
			cajero.join();
		}
		
		System.out.println("Esperado: 5000");
		System.out.printf("%nObtenido: %d", contador.vendidas);
	}
}

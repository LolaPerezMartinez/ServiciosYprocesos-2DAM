package concurrencia.contador;


public class ContadorSincronizado {
	private int vendidas = 0;
	
	public synchronized	void venderEntradas() {
		vendidas++;
	}

	public synchronized	int getVendidas() {
		return vendidas;
	}
	
	public static void main(String[] args) throws InterruptedException {
		ContadorSincronizado contador = new ContadorSincronizado();
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

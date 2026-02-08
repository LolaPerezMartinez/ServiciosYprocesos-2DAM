package concurrencia.contador;

import java.util.concurrent.atomic.AtomicInteger;

public class ContadorAtomico {
	private AtomicInteger vendidas = new AtomicInteger(0);
	
	public void venderEntradas() {
		vendidas.incrementAndGet();
	}

	public int getVendidas() {
		return vendidas.get();
	}
	
	public static void main(String[] args) throws InterruptedException {
		ContadorAtomico contador = new ContadorAtomico();
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
		System.out.printf("%nObtenido: %d", contador.vendidas.get());
	}
}

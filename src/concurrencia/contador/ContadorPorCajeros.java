package concurrencia.contador;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ContadorPorCajeros {
	private ConcurrentHashMap<String, AtomicInteger> ventas = new ConcurrentHashMap<>();
	
	
	public void venderEntradas(String cajero) {
		ventas.computeIfAbsent(cajero, k -> new AtomicInteger(0)).incrementAndGet();
	}
	
	public void mostrarEstadisticas() {
		System.out.printf("%n=== Ventas por cajero ===%n");
		int total = 0;
		
		for (String cajero : ventas.keySet()) {
			int cantidad = ventas.get(cajero).get();
			System.out.println(cajero + ":" + cantidad + " entradas");
			total += cantidad;
		}
		System.out.printf("%nTOTAL %d entradas", total);
	}
	
	public static void main(String[] args) throws InterruptedException {
		ContadorPorCajeros contador = new ContadorPorCajeros();
		Thread [] cajeros = new Thread[5];
		
		for (int i = 0; i < 5; i++) {
			final String nombreCajero = "Cajero-" + (i + 1);
			cajeros[i] = new Thread(() ->{
				for (int j = 0; j < 10; j++) {
					contador.venderEntradas(nombreCajero);
				}
			});
			cajeros[i].start();
		}
		
		for (Thread cajero : cajeros) {
			cajero.join();
		}
		
		contador.mostrarEstadisticas();
	}
	
	
	
	
	
	
}

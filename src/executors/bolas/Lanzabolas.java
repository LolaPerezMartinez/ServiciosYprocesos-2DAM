package executors.bolas;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Lanzabolas implements Runnable{
	private String nombre;

	public Lanzabolas(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {
		LocalDateTime ahora = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
		System.out.printf("%nLanzando bola desde %s a las %s.%n", nombre, ahora.format(formato));
		
	}
	
	

}

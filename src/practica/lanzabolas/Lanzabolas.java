package practica.lanzabolas;

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
		DateTimeFormatter formateo = DateTimeFormatter.ofPattern("HH:mm:ss");
		System.out.printf("Lanzando bolas desde %s a las %s%n", nombre, ahora.format(formateo));
		
	}


	@Override
	public String toString() {
		return "Lanzabolas [nombre=" + nombre + "]";
	}
	
	

}

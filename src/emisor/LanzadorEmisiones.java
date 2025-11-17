package emisor;

public class LanzadorEmisiones extends Thread{
	private String nombre;
	private Emisor emisor;
	
	
	public LanzadorEmisiones(String nombre, Emisor emisor) {
		this.nombre = nombre;
		this.emisor = emisor;
	}


	public String getNombre() {
		return nombre;
	}


	public Emisor getEmisor() {
		return emisor;
	}


	@Override
	public void run() {
		emisor.emite();
	}


	@Override
	public String toString() {
		return "LanzadorEmisiones [nombre=" + nombre + ", emisor=" + emisor + "]";
	}
	
	
	
}

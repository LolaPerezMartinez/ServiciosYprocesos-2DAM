package emisor;

public class Emisor {
	private String nombre;
	private char caracter;
	private int segundos;
	private static long milis = 300;
	
	private static final String VERDE = "\u001B[32m";
	private static final String ROJO = "\u001B[31m";
	private static final String RESET = "\u001B[0m";
	
	public Emisor(String nombre, char caracter, int segundos) {
		this.nombre = nombre;
		this.caracter = caracter;
		this.segundos = segundos;
	}

	public Emisor(char caracter, int segundos) {
		this("sin nombre", caracter, segundos);
	}

	public String getNombre() {
		return nombre;
	}

	public void emite() {
		int segundos = 10;
		for (int i = 0; i < segundos; i++) {
			try {
				if(i <= 0) {
					System.out.print(VERDE + caracter);
				}else if(i < segundos - 1) {
					System.out.print(RESET + caracter);
				}else {
					System.out.print(ROJO + caracter);
				}
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "Emisor [nombre=" + nombre + ", caracter=" + caracter + ", segundos=" + segundos + "]";
	}
	
	

}

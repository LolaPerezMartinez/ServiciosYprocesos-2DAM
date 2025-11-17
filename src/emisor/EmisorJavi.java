package emisor;

public class EmisorJavi {
	private String nombre;
	private char caracter;
	private int segundos;
	private static long milis = 300;
	
	private static final String VERDE = "\u001B[32m";
	private static final String ROJO = "\u001B[31m";
	private static final String RESET = "\u001B[0m";
	
	public EmisorJavi(String nombre, char caracter, int segundos) {
		this.nombre = nombre;
		this.caracter = caracter;
		this.segundos = segundos;
	}

	public EmisorJavi(char caracter, int segundos) {
		this("sin nombre", caracter, segundos);
	}

	public String getNombre() {
		return nombre;
	}

	public void emite() {
		long timeStampMax = System.currentTimeMillis() + segundos * 1000;
		
		System.out.print(VERDE + caracter);
		while(System.currentTimeMillis() < timeStampMax) {
			System.out.print(caracter);
			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(ROJO + caracter);
	}

	@Override
	public String toString() {
		return "Emisor [nombre=" + nombre + ", caracter=" + caracter + ", segundos=" + segundos + "]";
	}
	
	public static void main(String[] args) {
		EmisorJavi ej1 = new EmisorJavi('⁜', 10);
		ej1.emite();
	}
	

}

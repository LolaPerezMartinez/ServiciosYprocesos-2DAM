package practica.hilos.revelador;

public class Revelador {
	private String palabra;
	private static long milis = 300;
	
	
	public Revelador(String palabra) {
		this.palabra = palabra;
	}
	
	public void revelar() {
		char [] arrayPalabra = palabra.toCharArray();
		
		for (int i = 0; i < arrayPalabra.length; i++) {
			for (int j = 0; j < arrayPalabra.length; j++) {
				if(i <= j) {
					System.out.printf("- ");
				}else {
					System.out.printf("%c ", arrayPalabra[j]);
				}
			}
			System.out.println();
		}
	}

}

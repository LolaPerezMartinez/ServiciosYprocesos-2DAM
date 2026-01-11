package practica.hilos.piramidepalabras;

public class PiramidePalabra implements Runnable{
	private String palabra;
	private static long milis = 300;

	
	public PiramidePalabra(String palabra) {
		this.palabra = palabra;
	}


	@Override
	public void run() {
		char [] arrayPalabra = palabra.toCharArray();
		
		System.out.printf("%nPalabra : %s%n", palabra.toUpperCase());
		String palabraAscendente = null;
		String palabraDescendente = null;
		for (int i = 0; i < arrayPalabra.length; i++) {
			try {
				palabraAscendente = palabra.substring(0,i+1);
				System.out.println(palabraAscendente);
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		for (int i = arrayPalabra.length; i > 0; i--) {
			try {
				Thread.sleep(milis);
				palabraDescendente = palabra.substring(0, i-1);
				System.out.println(palabraDescendente);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
			
		
		
		
		
	}

}

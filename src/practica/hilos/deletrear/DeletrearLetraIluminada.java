package practica.hilos.deletrear;


public class DeletrearLetraIluminada implements Runnable {
	private String palabra;
	private static long milis = 400;
	
	private static final String VERDE = "\u001B[32m";
	private static final String RESET = "\u001B[0m";
	
	
	public DeletrearLetraIluminada(String palabra) {
		this.palabra = palabra;
	}


	@Override
	public void run() {
		char [] arrayPalabra = palabra.toCharArray();
		System.out.printf("Deletreando \"%s\":%n", palabra);
		for (int i = 0; i < arrayPalabra.length; i++) {
			 System.out.printf("[");
			for (int j = 0; j < arrayPalabra.length; j++) {
				try {
					if(i == j) {
						System.out.printf("%s%c%s", VERDE, arrayPalabra[j], RESET);
					}else {
						System.out.printf("%c",arrayPalabra[j]);
					}
					Thread.sleep(milis);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			System.out.printf("]%n");
		}
		
	}
	
	
	
	
	

}

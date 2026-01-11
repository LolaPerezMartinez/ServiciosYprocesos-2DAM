package practica.hilos.letramayuscula;

public class LetraMayuscula implements Runnable{
	private String palabra;
	private static long milis = 300;
	
	public LetraMayuscula(String palabra) {
		this.palabra = palabra;
	}

	@Override
	public void run() {
		char [] arrayPalabra = palabra.toCharArray();
		System.out.printf("%nPalabra : %s%n", palabra.toUpperCase());
		for (int i = 0; i < arrayPalabra.length; i++) {
			for (int j = 0; j < arrayPalabra.length; j++) {
				try {
					if(i == j) {
						String letra = arrayPalabra[j] + "";
						System.out.printf("%s ", letra.toUpperCase());
					}else {
						System.out.printf("%s ", arrayPalabra[j]);
					}
					
					Thread.sleep(milis);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println();
		}
		
	}
	
	
	

}

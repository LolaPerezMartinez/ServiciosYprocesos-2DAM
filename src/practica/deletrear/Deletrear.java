package practica.deletrear;


public class Deletrear implements Runnable {
	private String palabra;
	private static long milis = 400;

	public Deletrear(String palabra) {
		this.palabra = palabra;
	}

	@Override
	public void run() {
		char [] arrayPalabra = palabra.toCharArray();
		
		System.out.printf("%nDeletreando [%s] %n", palabra);
		for (int i = 0; i < arrayPalabra.length; i++) {
			for (int j = 0; j < arrayPalabra.length; j++) {
				if(i == j) {
					System.out.printf("%c ", arrayPalabra[j]);
				}else {
					System.out.print("- ");
				}
				try {
					Thread.sleep(milis);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println();
		}
	}
	
	
	
	
	

}

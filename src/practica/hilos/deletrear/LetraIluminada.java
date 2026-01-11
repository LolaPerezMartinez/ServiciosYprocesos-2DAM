package practica.hilos.deletrear;



public class LetraIluminada implements Runnable {
	private String palabra;
	private static long milis = 300;
	
	private static final String VERDE = "\u001B[32m";
	private static final String RESET = "\u001B[0m";
	
	

	public LetraIluminada(String palabra) {
		this.palabra = palabra;
	}



	@Override
	public void run() {
		char [] arrayPalabra = palabra.toCharArray();
		
		System.out.printf("%nDeletreando \"%s\"%n", palabra);
		for (int i = 0; i < arrayPalabra.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < arrayPalabra.length; j++) {
				try {
					Thread.sleep(milis);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(i == j) {
					sb.append(VERDE + palabra.charAt(j) + RESET);
				}else {
					sb.append(palabra.charAt(j));
				}
			}
			System.out.printf("%s%n", sb);
			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
		

}

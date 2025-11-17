package practica.revelacionpalabra;

public class Revelacion implements Runnable {
	private String palabra;
	private static long milis = 400;

	public Revelacion(String palabra) {
		this.palabra = palabra;
	}

	@Override
	public void run() {

		char[] arrayPalabra = palabra.toCharArray();
		System.out.printf("%nPalabra : %s%n".formatted(palabra.toUpperCase()));
		for (int i = 0; i <= arrayPalabra.length; i++) {
			System.out.print("[");
			for (int j = 0; j < arrayPalabra.length; j++) {
				try {
					if (i <= j) {
						System.out.printf("-");
					} else {
						System.out.printf("%s", arrayPalabra[j]);
					}
					Thread.sleep(milis);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.print("]");
			System.out.println();
		}
	}

}

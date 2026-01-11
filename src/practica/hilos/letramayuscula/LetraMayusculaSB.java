package practica.hilos.letramayuscula;

public class LetraMayusculaSB implements Runnable{
	private String palabra;
	private static long milis = 400;
	
	public LetraMayusculaSB(String palabra) {
		this.palabra = palabra;
	}

	@Override
	public void run() {
		char [] arrayPalabra = palabra.toCharArray();
		for (int i = 0; i < arrayPalabra.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < arrayPalabra.length; j++) {
				try {
					if(i == j) {
						String letra = arrayPalabra[j] + "";
						sb.append(letra.toUpperCase());
					}else {
						sb.append(arrayPalabra[j]);
					}
					Thread.sleep(milis);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println(sb);
		}
		
	}
	
	
	

}

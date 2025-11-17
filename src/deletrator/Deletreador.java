package deletrator;

public class Deletreador implements Runnable{
	private String palabra;
	
	private static long milis = 300;

	public Deletreador(String palabra) {
		this.palabra = palabra;
	}


	@Override
	public void run() {
		String caracter = "-";
		char [] arrayChar = palabra.toCharArray();
		
		for (int i = 0; i < arrayChar.length; i++) {
			System.out.printf("%nDeletreando %s : [",palabra);
			for (int j = 0; j < arrayChar.length; j++) {
				try {
					Thread.sleep(milis);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(i == j) {
					System.out.print(arrayChar[j]);
				}else {
					System.out.print(caracter);
				}
				
			}
			System.out.print("]");
		}
		System.out.printf("%nFin de la palabra %s.%n", palabra);
		
	}
	
	
	@Override
	public String toString() {
		return "Deletreador [palabra=" + palabra + "]";
	}

}

package deletrator;



public class Bucles {
	public static void main(String[] args) {
		String palabra = "Hola";
		char [] arrayPalabra = palabra.toCharArray();
		for (int i = 0; i < arrayPalabra.length; i++) {
			System.out.printf("%s ",arrayPalabra[i]);
		}
		
		System.out.println();
		char simbolo = '*';
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(i == j) {
					System.out.print(simbolo);
				}else {
					System.out.print("-");
				}
			}
			System.out.println();
		}
		
		
		
		
		
	}
	
	
}

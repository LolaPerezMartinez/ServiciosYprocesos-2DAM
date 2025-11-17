package practica.arqueros;


public class Arquero {
	private String nombre;
	private int flechas;
	private double distanciaObjeto;
	
	private static long milis = 400;
	
	
	
	public Arquero(String nombre, int flechas) {
		this.nombre = nombre;
		this.flechas = flechas;
	}

	public void disparar() {
		distanciaObjeto = System.currentTimeMillis();
		System.out.printf("%nArquero [%s] inicia los disparos.%n", nombre);
		for (int i = 1; i <= flechas; i++) {
			try {
				System.out.printf("%nArquero [%s] || Flecha %d%n", nombre, i);
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("%nArquero [%s] acaba los disparos.%n", nombre);
		System.out.printf("%nArquero [%s] recorrió : %.2f.%n", nombre, distanciaObjeto);
		
	}
}

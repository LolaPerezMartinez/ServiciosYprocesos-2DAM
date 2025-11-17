package practica.luciernaga;

public class Luciernaga {
	private String nombre;
	private boolean encendido;
	private int energia;
	private static long milis = 400;

	public Luciernaga(String nombre, int energia) {
		this.nombre = nombre;
		this.energia = energia < 1 ? 1 : energia > 50 ? 50 : energia;
	}

	public void enciende() {
		boolean energiaSuficiente = energia > 5;
		if (!encendido && energiaSuficiente) {
			System.out.printf("%nLuciernaga [%s] empieza con %d de energia.%n", nombre, energia);
			encendido = true;
			decrementarEnergia();

		} else if (encendido && energiaSuficiente) {
			System.out.printf("%nLuciernaga [%s] empieza con %d de energia.%n", nombre, energia);
			decrementarEnergia();
		} else {
			System.out.printf("%nLuciernaga [%s] no tiene energía suficiente para encenderse.%n", nombre);
		}

	}

	private void decrementarEnergia() {
		while (energia > 0) {
			System.out.printf("%nLuciernaga [%s] || Energia %d.%n", nombre, energia);
			energia--;
			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		encendido = false;
		System.out.printf("%nLuciernaga [%s] agotó la energia.%n", nombre);
	}

}

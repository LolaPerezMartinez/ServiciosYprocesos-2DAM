package practica.hilos.luciernaga2;

public class Luciernaga {
	private String nombre;
	private boolean encendida;
	private int energia;

	private static long milis = 400;

	public Luciernaga(String nombre, int energia) {
		this.nombre = nombre;
		this.energia = energia < 1 ? 1 : energia > 50 ? 50 : energia;
	}

	public void enciende () {
		if(!encendida && energia > 0) {
			System.out.printf("%nLuciernaga %s se enciende y empieza a disminuir su energia.%n", nombre);
			encendida = true;
			reducirEnergia();
		}else if(encendida && energia > 0) {
			System.out.printf("%nLuciernaga %s ya estaba encendida y empieza a disminuir su energia.%n", nombre);
			reducirEnergia();
		}else {
			System.out.printf("%nLuciernaga %s no tiene energia suficiente.%n", nombre);
		}
	}
	
	private void reducirEnergia() {
		while(energia > 0) {
			energia--;
			System.out.printf("%nLuciernaga %s || Energia %d%n", nombre, energia);
			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		encendida = false;
		System.out.printf("%nLuciernaga %s se apaga.%n", nombre);
	}
}

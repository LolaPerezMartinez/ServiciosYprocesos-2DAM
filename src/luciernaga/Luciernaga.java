package luciernaga;

public class Luciernaga {
	private String nombre;
	private boolean encendido;
	private int energia;
	
	private static int milis = 300;

	public Luciernaga(String nombre, int energia) {
		this.nombre = nombre;
		this.energia = energia < 1 ? 1 : energia > 50 ? 50 : energia;
	}
	
	public void enciende () {
		boolean energiaSuficiente = energia > 5;
		if(energiaSuficiente && !encendido) {
			encendido = true;
			reducirEnergia();
			
		}else if(encendido && energiaSuficiente) {
			reducirEnergia();
			}
		
	}
	
	private int reducirEnergia() {
		while(energia >= 0) {
			System.out.printf("%nNombre : %s || Energia: %d", nombre, energia);
			energia--;
			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("%nLa luciernaga %s ya esta agotada y se va a dormir.%n", nombre);
		return energia;
	}

	@Override
	public String toString() {
		return "Luciernaga [nombre=" + nombre + ", encendido=" + encendido + ", energia=" + energia + "]";
	}
	
	

}

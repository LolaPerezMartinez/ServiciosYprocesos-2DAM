package practica.luciernaga;

public class LuciernagaT extends Thread{
	private String nombre;
	private boolean encendido;
	private int energia;
	private static long milis = 400;
	
	
	
	public LuciernagaT(String nombre, int energia) {
		this.nombre = nombre;
		this.energia = energia < 1 ? 1 : energia > 50 ? 50 : energia;
	}
	
	public void enciende() {
		boolean energiaSuficiente = energia > 2;
		System.out.printf("%nLuciernaga [%s] empieza con energia %d.%n", nombre, energia);
		if(!encendido && energiaSuficiente) {
			encendido = true;
			disminuirEnergia();
		}else if(encendido && energiaSuficiente) {
			disminuirEnergia();
		}
		System.out.printf("%nLuciernaga [%s] agotó la bateria.%n", nombre);
		
	}
	
	private void disminuirEnergia() {
		while(energia >= 0) {
			try {
				System.out.printf("%nLuciernaga [%s] || Energia [%d] %n", nombre, energia);
				energia--;
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void run() {
		enciende();
	}
	
	
}

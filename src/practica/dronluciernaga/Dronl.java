package practica.dronluciernaga;

public class Dronl {
	private int id;
	private String modelo;
	private int energia;
	private boolean encendido;
	private static int nextId = 1;
	private static long milis = 400;
	
	
	
	public Dronl(String modelo, int energia) {
		id = nextId++;
		this.modelo = modelo;
		this.energia = energia;
	}
	
	public void volar() {
		boolean energiaSuficiente = energia > 5;
		if(!encendido && energiaSuficiente) {
			encendido = true;
			reducirEnergia();
		}else if(encendido && energiaSuficiente){
			reducirEnergia();
		}
	}
	
	private void reducirEnergia() {
		System.out.printf("%nDron %s comienza a volar.%n", modelo);
		while(energia > 0) {
			System.out.printf("%nDron %s || Energía %s%n", modelo, energia);
			try {
				energia--;
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		encendido = false;
		System.out.printf("%nDron %s agotó su energia || Energia: %d.%n", modelo, energia);
	}
	

}

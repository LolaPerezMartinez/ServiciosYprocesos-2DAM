package practica.hilos.coches;

public class Coches implements Runnable{
	private String modelo;
	private boolean enMarcha;
	private int combustible;
	
	private static long milis= 500;

	
	public Coches(String modelo, int combustible) {
		this.modelo = modelo;
		this.combustible = combustible < 5 ? 5 : combustible > 100 ? 100 : combustible;
	}
	
	public void arrancar() {
		boolean combustibleSuficiente = combustible > 5;
		if(combustibleSuficiente && !enMarcha) {
			enMarcha = true;
			reducirCombustible();
		}else if(combustibleSuficiente && enMarcha) {
			reducirCombustible();
		}
	}
	
	private int reducirCombustible() {
		while(combustible > 0){
			try {
				System.out.printf("%nCoche %s con %d%% de combustible.", modelo, combustible);
				combustible--;
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("%nSe agotó el combustible del coche %s.", modelo);
		return combustible;
	}

	@Override
	public void run() {
		arrancar();
		
	}

}

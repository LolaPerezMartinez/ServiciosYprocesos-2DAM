package practica.carreradron;

public class Dron {
	private int id;
	private String modelo;
	private int metrosVuelo;
	private static long velocidad = 300;
	private static int nextId = 1;
	
	
	
	public Dron(String modelo, int metrosVuelo) {
		this.modelo = modelo;
		this.metrosVuelo = metrosVuelo < 10 ? 10 : metrosVuelo;
		id = nextId++;
	}
	
	public void volar () {
		long tiempoInicio = System.currentTimeMillis();
		System.out.printf("%nDron [id:%d] modelo %s comienza su vuelo.%n", id, modelo);
		for (int i = 0; i < metrosVuelo; i+=10) {
			try {
				System.out.printf("%nDron [id:%d] modelo %s avanza %d metros.%n", id, modelo, i);
				Thread.sleep(velocidad);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long tiempoFinal = System.currentTimeMillis();
		System.out.printf("%nDron [id:%d] modelo %s acaba su vuelo en un tiempo de %d milisegundos.%n", id, modelo, tiempoFinal- tiempoInicio);
	}



}

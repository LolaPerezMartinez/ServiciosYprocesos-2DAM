package practica.hilos.cintatransportadora;

public class LanzadorCinta extends Thread{
	private CintaTransportadora cinta;

	public LanzadorCinta(CintaTransportadora cinta) {
		this.cinta = cinta;
	}

	@Override
	public void run() {
		cinta.mover();
	}
	
	
	

}

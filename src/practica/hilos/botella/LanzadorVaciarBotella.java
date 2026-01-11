package practica.hilos.botella;

public class LanzadorVaciarBotella extends Thread{
	private Botella botella;

	public LanzadorVaciarBotella(Botella botella) {
		this.botella = botella;
	}

	@Override
	public void run() {
		botella.vaciar();
	}
	
	

}

package practica.arqueros;

public class LanzadorArquero implements Runnable{
	private Arquero arquero;

	
	public LanzadorArquero(Arquero arquero) {
		this.arquero = arquero;
	}


	@Override
	public void run() {
		arquero.disparar();
		
	}

}

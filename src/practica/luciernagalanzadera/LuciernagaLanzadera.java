package practica.luciernagalanzadera;

public class LuciernagaLanzadera extends Thread{
	private Luciernaga luciernaga;

	public LuciernagaLanzadera(Luciernaga luciernaga) {
		this.luciernaga = luciernaga;
	}

	@Override
	public void run() {
		luciernaga.enciende();
	}
	
	
	

}

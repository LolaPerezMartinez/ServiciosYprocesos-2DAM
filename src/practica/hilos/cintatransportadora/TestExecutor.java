package practica.hilos.cintatransportadora;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutor {
	public static void main(String[] args) {
		CintaTransportadora c1 = new CintaTransportadora("1", 6);
		CintaTransportadora c2 = new CintaTransportadora("2", 8);
		
		CintaTransportadoraT ct1 = new CintaTransportadoraT("3", 4);
		CintaTransportadoraT ct2 = new CintaTransportadoraT("4", 5);
		
		CintaTransportadoraR cr1 = new CintaTransportadoraR("5", 9);
		CintaTransportadoraR cr2 = new CintaTransportadoraR("6", 3);
		
		LanzadorCinta lc1 = new LanzadorCinta(new CintaTransportadora("7", 10));
		LanzadorCinta lc2 = new LanzadorCinta(new CintaTransportadora("8", 2));
		
		ExecutorService ex1 = Executors.newFixedThreadPool(3);
		ex1.execute(lc1);
		ex1.execute(lc2);
		ex1.execute(ct1);
		ex1.execute(cr2);
		
		ex1.shutdown();
	}

}

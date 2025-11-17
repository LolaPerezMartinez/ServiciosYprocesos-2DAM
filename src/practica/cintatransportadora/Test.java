package practica.cintatransportadora;

public class Test {
	public static void main(String[] args) {
		CintaTransportadora c1 = new CintaTransportadora("1", 6);
		CintaTransportadora c2 = new CintaTransportadora("2", 8);
		
		CintaTransportadoraT ct1 = new CintaTransportadoraT("3", 4);
		CintaTransportadoraT ct2 = new CintaTransportadoraT("4", 5);
		
		CintaTransportadoraR cr1 = new CintaTransportadoraR("5", 9);
		CintaTransportadoraR cr2 = new CintaTransportadoraR("6", 3);
		
		LanzadorCinta lc1 = new LanzadorCinta(new CintaTransportadora("7", 10));
		LanzadorCinta lc2 = new LanzadorCinta(new CintaTransportadora("8", 2));
		
		System.out.printf("%nMonohilo");
		c1.mover();
		c2.mover();
		
		System.out.printf("%nMultihilo con Thread");
		ct1.start();
		ct2.start();
		
		System.out.printf("%nMultihilo con Runnable");
		new Thread(cr1).start();
		new Thread(cr2).start();
		
		System.out.printf("%nMultihilo con Lanzador");
		lc1.start();
		lc2.start();
		
		
	}

}

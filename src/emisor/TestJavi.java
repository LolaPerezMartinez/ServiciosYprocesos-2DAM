package emisor;

public class TestJavi {
	public static void main(String[] args) {

		LanzadorEmisionesJavi lj1 = new LanzadorEmisionesJavi("L-1", new EmisorJavi("E-1", '※', 5));
		LanzadorEmisionesJavi lj2 = new LanzadorEmisionesJavi("L-2", new EmisorJavi("E-2", '+', 7));
		LanzadorEmisionesJavi lj3 = new LanzadorEmisionesJavi("L-3", new EmisorJavi("E-3", '-', 10));

		System.out.println(lj1);
		System.out.println(lj2);
		lj1.start();
		// l2.start();
		
		for (int i = 0; i < 25; i++) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(lj1);
		}
		// System.out.println(l2);
		try {
			Thread.sleep(6_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(lj1);
	
	}

	
}

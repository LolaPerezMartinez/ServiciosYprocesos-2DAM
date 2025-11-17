package emisor;

public class Test {
public static void main(String[] args) {
	
	Emisor e1 = new Emisor("E-1",'※', 5);
	Emisor e2 = new Emisor("E-2",'⁜', 7);
	Emisor e3 = new Emisor("E-3",'⁘', 10);
	
	System.out.printf("Lanzadores%n");
	LanzadorEmisiones le1 = new LanzadorEmisiones("L-1", e1);
	LanzadorEmisiones le2 = new LanzadorEmisiones("L-2", e2);
	LanzadorEmisiones le3 = new LanzadorEmisiones("L-3", e3);
	System.out.println(le1);
	System.out.println(le2);
	System.out.println(le3);
	
	System.out.printf("%nFlujo lanzadores%n");
	le1.start();
	le2.start();
	le3.start();
}
}

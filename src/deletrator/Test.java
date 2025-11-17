package deletrator;

public class Test {
	public static void main(String[] args) {
		//Deletreador d1 = new Deletreador("Hola");
		Deletreador d2 = new Deletreador("Mola");
		
		//System.out.printf("%nDeletrear palabra Hola");
		//new Thread(d1).start();
		
		
		System.out.printf("%nDeletrear palabra Mola");
		new Thread(d2).start();
	}

}

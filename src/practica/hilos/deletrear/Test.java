package practica.hilos.deletrear;

public class Test {
	public static void main(String[] args) {
		Deletrear p1 = new Deletrear("Hola");
		
		//LetraIluminada li1 = new LetraIluminada("Jose");
		
		new Thread(p1).start();
		
		//new Thread(li1).start();
		
		//DeletrearLetraIluminada di1 = new DeletrearLetraIluminada("World");
		
		//new Thread(di1).start();
	}

}

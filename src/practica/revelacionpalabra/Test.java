package practica.revelacionpalabra;

public class Test {
public static void main(String[] args) {
	Revelacion p1 = new Revelacion("Luna");
	
	new Thread(p1).start();
}
}

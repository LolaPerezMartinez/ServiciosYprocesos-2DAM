package practica.hilos.dronluciernaga;

public class Test {
public static void main(String[] args) {
	Dronl dl1 = new Dronl("D-1", 8);
	Dronl dl2 = new Dronl("D-2", 7);
	
	dl1.volar();
	dl2.volar();
}
}

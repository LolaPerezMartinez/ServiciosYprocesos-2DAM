package practica.socket.hilos.adivinanzas2;

import java.util.random.RandomGenerator;

public class JuegoAdivinanza {
	private int numeroEscondido;
	private static RandomGenerator random = RandomGenerator.getDefault();
	
	public JuegoAdivinanza () {
		numeroEscondido = random.nextInt(1, 1001);
	}
	
	public String verificarIntento(String intento) {
		try {
			int numeroIntento = Integer.parseInt(intento);
			return numeroIntento == numeroEscondido ? "Acierto" : "Fallo";
		}catch(NumberFormatException e) {
			return "Formato no valido";
		}
	}
}

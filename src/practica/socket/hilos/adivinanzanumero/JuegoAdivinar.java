package practica.socket.hilos.adivinanzanumero;

import java.util.random.RandomGenerator;

public class JuegoAdivinar {
	private int numeroSecreto;
	private int intentos;
	
	private static RandomGenerator random = RandomGenerator.getDefault();

	public JuegoAdivinar() {
		numeroSecreto = random.nextInt(0, 500);
	}
	
	public String verificarIntento (String intento) {
		try {
			int respuesta = Integer.parseInt(intento);
			return respuesta == numeroSecreto ? "Acertaste" : "Fallaste";
			
		}catch(NumberFormatException e) {
			return "Formato incorrecto";
		}
	}
	
	
	

}

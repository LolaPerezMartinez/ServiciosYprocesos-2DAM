package practica.socket.hilos.adinum;

import java.util.random.RandomGenerator;

public class AdivinarNumero {
	private int numeroEscondido;
	private RandomGenerator random = RandomGenerator.getDefault();
	
	public AdivinarNumero() {
		numeroEscondido = random.nextInt(1, 1001);
	}
	
	public String verificarIntento (String intento) {
		
		try {
			int respuesta = Integer.parseInt(intento);
			return numeroEscondido == respuesta ? "Acertaste" : "Fallaste";
			
		}catch(NumberFormatException e) {
			return "Formato incorrecto";
			
		}
	}
}

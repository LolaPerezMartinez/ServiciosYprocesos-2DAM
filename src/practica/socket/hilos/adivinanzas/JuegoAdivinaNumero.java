package practica.socket.hilos.adivinanzas;

import java.util.random.RandomGenerator;

/**
 * La clase JuegoAdivinaNumero implementa la lógica para un juego simple
 * donde se debe adivinar un número generado aleatoriamente.
 */
public class JuegoAdivinaNumero {
	private int numeroEscondido;
	private static RandomGenerator random = RandomGenerator.getDefault();
	
	public JuegoAdivinaNumero() {
		numeroEscondido = random.nextInt(1, 1001);
	}
	
	/**
     * Verifica si el intento del jugador es igual al número generado aleatoriamente.
     * 
     * @param intento El intento del jugador como un String.
     * @return "Acertado" si el intento coincide con el número escondido, "No acertado" en caso contrario.
     *         Si el intento no es un número válido, también devuelve "No acertado".
     */
	
	public String verificarIntento(String intento) {
		try {
			int numeroIntentado = Integer.parseInt(intento);
			return numeroIntentado == numeroEscondido ? "🎉¡Has acertado!🎉" : "Lo siento, no has acertado";
		}catch(NumberFormatException e) {
			return "Respuesta no válida";
		}
	}
}

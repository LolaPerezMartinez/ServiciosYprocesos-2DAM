package practica.socket.hilos.adivinapalabra;

import java.util.random.RandomGenerator;

public class JuegoPalabra {
	private String palabraEscondida;
	private static String[] listaPalabras = { "clavel", "montaña", "reloj", "nube", "ventana", "faro", "cuaderno",
			"bosque", "taza", "estrella" };
	private static RandomGenerator random = RandomGenerator.getDefault();
	
	
	public static String[] getListaPalabras() {
		return listaPalabras;
	}
	
	public JuegoPalabra() {
		palabraEscondida = listaPalabras[random.nextInt(0, listaPalabras.length)];
	}

	public String verificarIntento(String intento) {
		if(intento.contains("fin")) {
			return "Juego terminado";
		}
		if (intento.isBlank()) {
			return "Error: la palabra no puede estar vacía.";
		}
		char[] arrayIntento = intento.toCharArray();
		for (int i = 0; i < arrayIntento.length; i++) {
			if (!Character.isLetter(arrayIntento[i])) {

				return "Error: la palabra sólo puede contener letras.";
			}

		}
		
		String intentoMinuscula = intento.toLowerCase();
		return intentoMinuscula.equals(palabraEscondida) ? "Acertaste" : "Fallaste";

	}

}
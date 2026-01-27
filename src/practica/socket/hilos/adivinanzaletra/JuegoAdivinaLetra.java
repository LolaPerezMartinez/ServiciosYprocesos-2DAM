package practica.socket.hilos.adivinanzaletra;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class JuegoAdivinaLetra {
	private String letraEscondida;
	private List<String> letrasPosibles;
	
	private static RandomGenerator random = RandomGenerator.getDefault();

	public JuegoAdivinaLetra() {
		letrasPosibles = new ArrayList<>();
		letrasPosibles.add("a");
		letrasPosibles.add("e");
		letrasPosibles.add("i");
		letrasPosibles.add("o");
		letrasPosibles.add("u");
		
		letraEscondida = letrasPosibles.get(random.nextInt(0, letrasPosibles.size()));
	}
	
	public String verificarIntento (String letra) {
		
		try {
			String letraIntentada = letra;
			return letraIntentada == letraEscondida ? "Acertaste" : "Fallaste";
			
		}catch(Exception e) {
			return "Letra no válida";
			
		}
	}

}

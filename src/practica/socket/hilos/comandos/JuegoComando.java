package practica.socket.hilos.comandos;

import java.util.random.RandomGenerator;

public class JuegoComando {
	private static RandomGenerator random = RandomGenerator.getDefault();
	
	public String verificarIntento(String enunciado) {
		int primerNumero;
		int segundoNumero;
	
		
		if(!(enunciado.startsWith("#") && enunciado.endsWith("#"))) {
			return "#Error# | El comando debe empezar y terminar con #";
		}
		
		String enunciadoCortado = enunciado.substring(1, enunciado.length() - 1);//Mensaje: Listado numeros#2#8
		String [] arrayEnunciado = enunciadoCortado.split("#"); //[listado numeros, 2, 8]
		
		String textoEnunciado = arrayEnunciado[0];
	
		if("Fin".equalsIgnoreCase(textoEnunciado)) {
			if(arrayEnunciado.length > 1) {
				return "#Error# | El comando Fin solo puede tener un parámetro";
			}
			return "#Finalizado#";
		}
		
		if(arrayEnunciado.length != 3) {
			return "#Error# | Todos los comandos excepto fin deben tener 3 parámetros [mensaje, numero1, numero2]";
		}
		
		String primerNumEnunciado = arrayEnunciado[1];
		String segundoNumEnunciado = arrayEnunciado[2];
		
		try {
			primerNumero = Integer.parseInt(primerNumEnunciado);
			segundoNumero = Integer.parseInt(segundoNumEnunciado);
		}catch (NumberFormatException e) {
			return "#Error# | Los parámetros deben ser numéricos";
		}
		
		if("Listado numeros".equalsIgnoreCase(textoEnunciado)) {
			StringBuilder sb = new StringBuilder();
			for (int i = primerNumero; i <= segundoNumero; i++) {
				sb.append(i);
				if(i < segundoNumero) {
					sb.append("|");
				}
			}
			return sb.toString();
		}
		
		if("Numero aleatorio".equalsIgnoreCase(textoEnunciado)) {
			return Integer.toString(random.nextInt(primerNumero, segundoNumero + 1));
		}
		
		return "#Error# | Comando no disponible.";
	}

}

package practica.socket.hilos.practicaexamen2;

import java.util.random.RandomGenerator;

public class ManejadorComandos {
	private RandomGenerator random = RandomGenerator.getDefault();
	
	public String verificarComando(String comando) {
		int primerNumero;
		int segundoNumero;
		
		if(comando == null || comando.isBlank()) {
			return "Error | El comando no puede ser nulo ni estar en blanco";
		}
		
		if(!(comando.startsWith("#") && comando.endsWith("#"))) {
			return "Error | El comando debe empezar y terminar con #";
		}
		
		String comandoSinAlmohadilla = comando.substring(1, comando.length() - 1);
		String [] arrayComando = comandoSinAlmohadilla.split("#", -1);
		
		String textoComando = arrayComando[0].trim();
		String primerNum = arrayComando[1].trim();
		String segundoNum = arrayComando[2].trim();
		
		if("fin".equalsIgnoreCase(textoComando)) {
			if(arrayComando.length > 1) {
				return "Error | El comando fin debe contener un sólo parámetro";
			}else {
				return "#Finalizado#";
			}
		}
		
		if(arrayComando.length != 3) {
			return "Error | El comando debe contener tres parámetros";
		}
		
		try {
			primerNumero = Integer.parseInt(primerNum);
			segundoNumero = Integer.parseInt(segundoNum);
		}catch (NumberFormatException e) {
			return "Error | El segundo y tercer parámetro debe ser un número";
		}
		
		if(primerNumero > segundoNumero) {
			return "Error | El primer numero debe ser menor que el segundo";
		}
		
		if("listado numeros".equalsIgnoreCase(textoComando)) {
			StringBuilder sb = new StringBuilder();
			for (int i = primerNumero; i <= segundoNumero; i++) {
				sb.append(i);
				if(i < segundoNumero) {
					sb.append("|");
				}
			}
			return sb.toString();
		}
		
		if("numero aleatorio".equalsIgnoreCase(textoComando)) {
			return Integer.toString(random.nextInt(primerNumero, segundoNumero + 1));
		}
		
		return "Error | El comando no existe";
	}

}

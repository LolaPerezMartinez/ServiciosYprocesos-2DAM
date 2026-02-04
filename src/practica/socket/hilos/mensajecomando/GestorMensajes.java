package practica.socket.hilos.mensajecomando;

import java.util.random.RandomGenerator;

public class GestorMensajes {
	private static RandomGenerator random = RandomGenerator.getDefault();
	
	public String verificarMensaje(String enunciado) {
		int primerNum;
		int segundoNum;
		
		if(!(enunciado.startsWith("#") && enunciado.endsWith("#"))) {
			return "#Error# | El mensaje debe empezar y terminar por #";
		}
		
		String mensajeCortado = enunciado.substring(1, enunciado.length() - 1);
		String [] arrayEnunciado = mensajeCortado.split("#", -1);
		
		String texto = arrayEnunciado[0];
		if("fin".equalsIgnoreCase(texto)) {
			if(arrayEnunciado.length > 1) {
				return "#Error# | El comando fin no debe contener otros parámetros";
			}
			return "OK";
		}
		
		if(arrayEnunciado.length != 3) {
			return "#Error# | El comando debe contener 3 parámetros";
		}
		
		boolean textoEsRango = "rango".equalsIgnoreCase(texto);
		boolean textoEsAleatorio = "aleatorio".equalsIgnoreCase(texto);
		if(textoEsRango || textoEsAleatorio) {
			try {
				primerNum = Integer.parseInt(arrayEnunciado[1]);
				segundoNum = Integer.parseInt(arrayEnunciado[2]);
				
				if(textoEsRango) {
					StringBuilder sb = new StringBuilder();
					for (int i = primerNum; i <= segundoNum; i++) {
						sb.append(i);
						if(i < segundoNum) {
							sb.append(", ");
						}
					}
					return sb.toString();
				}
				
				if(textoEsAleatorio) {
					return Integer.toString(random.nextInt(primerNum, segundoNum + 1));
				}
				
			}catch (Exception e) {
				return "#Error# | Los comandos de rango y aleatorio deben contener 2 parámetros numéricos";
			}
		}
		
		
		if("contar".equalsIgnoreCase(texto)) {
			String palabraAContar = arrayEnunciado[1];
			String caracterRepetido = arrayEnunciado[2];
			if(palabraAContar.length() < 3) {
				return "#Error# | El segundo parámetro de contar debe ser una palabra de al menos 3 letras";
			}
			
			if(!contieneLetras(palabraAContar)) {
				return "#Error# | El segundo parámetro de contar no puede ser un número";
			}
			
			if(caracterRepetido.length() > 1) {
				return "#Error# | El tercer parámetro de contar sólo puede tener un carácter";
			}
			
			if(!contieneLetras(caracterRepetido)) {
				return "#Error# | El tercer parámetro de contar no puede ser un número";
			}
			
			int numVeces = 0 ;
			char letra = caracterRepetido.charAt(0);
			for (int i = 0; i < palabraAContar.length(); i++) {
				if(palabraAContar.charAt(i) == letra) {
					numVeces++;
				}
			}
			return Integer.toString(numVeces);
		}
		
		
		
		
		return "#Error# | El comando no existe.";
	}
	
	private boolean contieneLetras(String parametro) {
	    for (int i = 0; i < parametro.length(); i++) {
	        if (!Character.isLetter(parametro.charAt(i))) {
	            return false;
	        }
	    }
	    return true;
	}
	
	
	

}

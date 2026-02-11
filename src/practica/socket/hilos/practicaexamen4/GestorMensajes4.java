package practica.socket.hilos.practicaexamen4;

import java.util.random.RandomGenerator;

public class GestorMensajes4 {
	private static RandomGenerator random = RandomGenerator.getDefault();
	
	public String verificarintento(String mensaje) {
		int primerNumero;
		int segundoNumero;
		
		if(mensaje == null || mensaje.isBlank()) {
			return "El mensaje no puede ser null ni estar vacío";
		}
		
		if(!(mensaje.startsWith("#") && mensaje.endsWith("#"))) {
			return "El mensaje tiene que empezar y terminar con #";
		}
		
		String mensajeLimpio = mensaje.substring(1, mensaje.length() - 1);
		String [] arrayMensaje = mensajeLimpio.split("#", - 1);
		
		String textoComando = arrayMensaje[0].trim();
		
		if("fin".equalsIgnoreCase(textoComando)) {
			if(arrayMensaje.length != 1) {
				return "El mensaje fin no tiene más parámetros";
			}else {
				return "#Finalizado#";
			}
		}
		
		if(arrayMensaje.length != 3) {
			return "El mensaje listado numeros y numero aleatorio solo puede tener 3 parámetros";
		}
		
		String num1 = arrayMensaje[1].trim();
		String num2 = arrayMensaje[2].trim();
		
		try {
			primerNumero = Integer.parseInt(num1);
			segundoNumero = Integer.parseInt(num2);
			
		}catch (NumberFormatException e) {
			return "El mensaje listado numeros y numero aleatorio solo puede tener 2 parámetros numéricos";
		}
		
		if(primerNumero > segundoNumero) {
			return "El primer numero debe ser menor que el segundo";
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
		
		return "Comando no disponible";
	}

}

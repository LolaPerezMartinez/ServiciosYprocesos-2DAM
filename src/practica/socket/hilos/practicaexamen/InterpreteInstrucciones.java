package practica.socket.hilos.practicaexamen;

import java.util.random.RandomGenerator;

public class InterpreteInstrucciones {
	private static RandomGenerator random = RandomGenerator.getDefault();
	
	public String verificarMensaje(String mensaje) {
		int primerNumero;
		int segundoNumero;
		
		if(mensaje == null || mensaje.isBlank()) {
			return "#Error# | El mensaje no puede ser nulo ni estar en blanco";
		}
		
		mensaje = mensaje.trim();
		
		if(!(mensaje.startsWith("#") && mensaje.endsWith("#"))) {
			return "#Error# | El mensaje tiene que empezar y terminar por #";
		}
		
		String mensajeSinAlmohadillas = mensaje.substring(1, mensaje.length() - 1);
		String [] arrayMensaje = mensajeSinAlmohadillas.split("#", - 1);
		
		if(arrayMensaje.length != 3) {
			return "#Error# | El comando debe tener 3 parametros [mensaje, numero1, numero2]";
		}
		
		String texto = arrayMensaje[0].trim();
		
		if("fin".equalsIgnoreCase(texto)) {
			if(arrayMensaje.length != 1) {
				return "#Error# | El comando fin debe tener 1 sólo parametro";
			}
			return "#Finalizado#";
		}
		
		String arg1 = arrayMensaje[1].trim();
		String arg2 = arrayMensaje[2].trim();
		
		try {
			primerNumero = Integer.parseInt(arg1);
			segundoNumero = Integer.parseInt(arg2);
			
		}catch (NumberFormatException e) {
			return "#Error# | Los dos argumentos del comando deben ser números";
		}
		
		if(primerNumero > segundoNumero) {
			return "#Error# | El primer argumento numérico debe ser mayor que el segundo";
		}
		
		if("listado numeros".equalsIgnoreCase(texto)) {
			StringBuilder sb = new StringBuilder();
			for (int i = primerNumero; i <= segundoNumero; i++) {
				sb.append(i);
				if(i < segundoNumero) {
					sb.append("|");
				}
			}
			return sb.toString();
		}
		
		if("numero aleatorio".equalsIgnoreCase(texto)) {
			return Integer.toString(random.nextInt(primerNumero, segundoNumero + 1));
		}
		
		return "#Error# | Comando no existente";
	}

}

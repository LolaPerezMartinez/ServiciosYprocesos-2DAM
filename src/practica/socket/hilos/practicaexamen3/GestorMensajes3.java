package practica.socket.hilos.practicaexamen3;

import java.util.random.RandomGenerator;

public class GestorMensajes3 {
	private RandomGenerator random = RandomGenerator.getDefault();
	
	public String verificarMensaje(String mensaje) {
		int primerNum;
		int segundoNum;
		
		if(mensaje == null || mensaje.isBlank()) {
			return "El comando no puede ser nulo o vacío";
		}
		
		if(!(mensaje.startsWith("#") && mensaje.endsWith("#"))) {
			return "El comando debe empezar y terminar por #";
		}
		
		String mensajeSinAlmohadillas = mensaje.substring(1, mensaje.length() - 1);//fin | numero aleatorio#2#9
		String [] arrayMensaje = mensajeSinAlmohadillas.split("#", - 1);
		
		String textoComando = arrayMensaje[0].trim();
		
		
		if("fin".equalsIgnoreCase(textoComando)) {
			if(arrayMensaje.length != 1) {
				return "El comando fin solo tiene un parámetro";
			}else {
				return "#Finalizado#";
			}
		}
		
		
		if(arrayMensaje.length != 3) {
			return "El comando solo puede tener 3 parámetros";
		}
		
		String arg1 = arrayMensaje[1].trim();
		String arg2 = arrayMensaje[2].trim();
		
		try {
			primerNum = Integer.parseInt(arg1);
			segundoNum = Integer.parseInt(arg2);
			
		}catch (NumberFormatException e) {
			return "El parámetro 2 y 3 tiene que ser número";
		}
		
		if(primerNum > segundoNum) {
			return "El primer numero debe ser menor que el segundo";
		}
		
		if("listado numeros".equalsIgnoreCase(textoComando)) {
			StringBuilder sb = new StringBuilder();
			for (int i = primerNum; i <= segundoNum; i++) {
				sb.append(i);
				if(i < segundoNum) {
					sb.append("|");
				}
			}
			return sb.toString();
		}
		
		if("numero aleatorio".equalsIgnoreCase(textoComando)) {
			return Integer.toString(random.nextInt(primerNum, segundoNum + 1));
			
		}
		
		return "Comando no disponible";
	}

}

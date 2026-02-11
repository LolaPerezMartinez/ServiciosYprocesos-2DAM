package practica.socket.hilos.comandosdiferentes;

public class PracticandoGestorComandos {
	
	public String verificarMensaje(String mensaje) {
		if(mensaje == null || mensaje.isBlank()) {
			return "El mensaje no debe ser null o estar vacio";
		}
		
		String [] arrayMensaje = mensaje.split("\\|", - 1);
		
		if(arrayMensaje.length != 4) {
			return "El mensaje debe contener 4 parámetros";
		}
		
		String comandoMensaje = arrayMensaje[0].trim();
		String arg1 = arrayMensaje[1].trim();
		String arg2 = arrayMensaje[2].trim();
		String arg3 = arrayMensaje[3].trim();
		
		if(!esGuion(arg3)) {
			return "El mensaje debe terminar en _";
		}
		
		if("salir".equalsIgnoreCase(comandoMensaje)) {
			if(!esGuion(arg1) || !esGuion(arg2)) {
				return "En el comando salir los argumentos 1 y 2 tienen que ser _";
			}else {
				return "FIN";
			}
		}
		
		if("repetir".equalsIgnoreCase(comandoMensaje)) {
			if(!sonLetras(arg1)) {
				return "En el comando repetir el argumento 1 deben ser palabras";
			}
			
			if(!esNumero(arg2)) {
				return "En el comando repetir el argumento 2 deben ser un numero";
			}
			
			int numRepeticiones = Integer.parseInt(arg2);
			String resultado = "";
			for (int i = 0; i < numRepeticiones; i++) {
				if(i > 0) {
					resultado += " ";
				}
				resultado += arg1;
			}
			return resultado;
		}
		
		
		if("contar".equalsIgnoreCase(comandoMensaje)) {
			if(arg1.length() <= 1) {
				return "En el comando contar el argumento 1 deben ser mas de una letra";
			}
			if(!sonLetras(arg1)) {
				return "En el comando contar el argumento 1 deben ser palabras";
			}
			if(arg2.length() != 1) {
				return "En el comando contar el argumento 2 deben ser una letra";
			}
			
			char letra = arg2.charAt(0);
			int numVeces = 0;
			for (int i = 0; i < arg1.length(); i++) {
				if(arg1.charAt(i) == letra) {
					numVeces++;
				}
			}
			return Integer.toString(numVeces);
		}
		
		if("mayusculas".equalsIgnoreCase(comandoMensaje)) {
			if(!esGuion(arg2)) {
				return "En el comando mayusculas el argumento 2 es _";
			}
			
			if(!sonLetras(arg1)) {
				return "En el comando mayusculas el argumento 1 son letras";
			}
			
			return arg1.toUpperCase();
		}
		
		if("invertir".equalsIgnoreCase(comandoMensaje)) {
			if(!esGuion(arg2)) {
				return "En el comando invertir el argumento 2 es _";
			}
			
			if(!sonLetras(arg1)) {
				return "En el comando invertir el argumento 1 son letras";
			}
			
			StringBuilder sb = new StringBuilder();
			for (int i = arg1.length() - 1; i >= 0; i--) {
				sb.append(arg1.charAt(i));
			}
			
			return sb.toString();
		}
		
		return "Comando no disponible";
	}
	
	private boolean esGuion(String mensaje) {
		if(mensaje == null) {
			return false;
		}
		return mensaje.trim().equals("_");
	}
	
	private boolean sonLetras(String mensaje) {
		for (int i = 0; i < mensaje.length(); i++) {
			if(!(Character.isLetter(mensaje.charAt(i)))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean esNumero(String mensaje) {
		for (int i = 0; i < mensaje.length(); i++) {
			if(!Character.isDigit(mensaje.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		PracticandoGestorComandos p1 = new PracticandoGestorComandos();
		System.out.println(p1.verificarMensaje("repetir|hola|3|_"));
		System.out.println(p1.verificarMensaje("contar|banana|a|_"));
		System.out.println(p1.verificarMensaje("mayusculas|cuaderno|_|_"));
		System.out.println(p1.verificarMensaje("invertir|frigorifico|_|_"));
		
		
	}
	
	
	
	
	
	
	
	
}

package practica.socket.hilos.mandarmensaje;

public class GestorMensajes {
	
	public String verificarMensaje(String mensaje) {
		if(mensaje == null || mensaje.isBlank()) {
			return "Error | El mensaje no puede estar en blanco";
		}
		
		if(!(mensaje.endsWith("_"))) {
			return "Error | El mensaje solo puede terminar con _";
		}
	
		String [] partesMensaje = mensaje.split("\\|");
		
		if(partesMensaje.length != 4) {
			return "Error | El mensaje tiene que contener 4 partes";
		}
		
		String mensajeSinFinal = mensaje.substring(0, mensaje.length() - 1);
		String [] arrayMensaje = mensajeSinFinal.split("\\|", - 1); //[REPETIR, hola, 3]  | [SALIR, _, _]
		
		String textoMensaje = arrayMensaje[0].trim();
		String arg1 = arrayMensaje[1].trim();
		String arg2 = arrayMensaje[2].trim();
		
		if(!(sonLetras(textoMensaje))) {
			return "Error | La primera parte del comando es una palabra";
		}
		
		if("salir".equalsIgnoreCase(textoMensaje)) {
			if(!esGuion(arg1) || !esGuion(arg2)) {
				return "Error | En el comando salir solo puede haber _";
			}else {
				return "Finalizado";
			}
		}
		
		if("repetir".equalsIgnoreCase(textoMensaje)) {
			if(!(sonLetras(arg1))) {
				return "Error | En el comando repetir el primer argumento debe ser una palabra";
			}
			if(!(esNumero(arg2))) {
				return "Error | En el comando repetir el segundo argumento debe ser un numero";
			}
			
			int arg2Num = Integer.parseInt(arg2);
			
			return (" " + arg1).repeat(arg2Num);
		}
		
		if("contar".equalsIgnoreCase(textoMensaje)) {
			if(!(sonLetras(arg1) || sonLetras(arg2))) {
				return "Error | En el comando contar el primer y segundo argumento debe contener letras";
			}
			if((arg2.length() != 1)) {
				return "Error | En el comando contar el segundo argumento debe ser una letra";
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
		
		if("mayusculas".equalsIgnoreCase(textoMensaje)) {
			if(!(sonLetras(arg1))) {
				return "Error | En el comando mayusculas el primer argumento debe ser una palabra";
			}
			
			if(!(esGuion(arg2))) {
				return "Error | En el comando mayusculas el segundi argumento debe ser _";
			}
			
			return arg1.toUpperCase();
		}
		
		if("invertir".equalsIgnoreCase(textoMensaje)) {
			if(!(sonLetras(arg1))) {
				return "Error | En el comando invertir el primer argumento debe ser una palabra";
			}
			
			if(!(esGuion(arg2))) {
				return "Error | En el comando invertir el segundi argumento debe ser _";
			}
			
			StringBuilder sb = new StringBuilder();
			for (int i = arg1.length() - 1; i >= 0; i--) {
				sb.append(arg1.charAt(i));
			}
			
			return sb.toString();
		}
		
		
		return "Error | Comando no existente";
	}
	
	private boolean esGuion(String mensaje){
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
	
	public boolean esNumero(String mensaje) {
		for (int i = 0; i < mensaje.length(); i++) {
			if(!(Character.isDigit(mensaje.charAt(i)))) {
				return false;
			}
		}
		return true;
	}
	
	

}

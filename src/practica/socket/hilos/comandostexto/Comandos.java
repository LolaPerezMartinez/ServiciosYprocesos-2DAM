package practica.socket.hilos.comandostexto;

public class Comandos {
	
	public String verificarComandos(String mensaje) {
		if(!(mensaje.endsWith("_"))) {
			return "#Error# | El comando debe terminar en _";
		}
		 
		String [] arrayEnunciado = mensaje.split("\\|");
		if(arrayEnunciado.length != 4) {
			System.out.printf("%nLongitud del enunciado: %d%n", arrayEnunciado.length);
			return "#Error# | Los comandos deben contener 4 parámetros";
		}
		
		String enunciadoSinGuion = mensaje.substring(0, mensaje.length() - 1);//Elimino el ultimo _
		String [] arrayEnunciadoSinGuion = enunciadoSinGuion.split("\\|");//[salir, _ , _  ] [repetir, hola, 3]
		
		String textoMensaje = arrayEnunciadoSinGuion[0];
		String arg1 =  arrayEnunciadoSinGuion[1];
		String arg2 =  arrayEnunciadoSinGuion[2];
		
		
		
		if(!sonLetras(textoMensaje.trim())) {
			return "#Error# | El primer comando debe contener solo letras";
		}
		
		if("salir".equalsIgnoreCase(textoMensaje.trim())) {
			if(arg1.equals("_") && arg2.equals("_")) {
				return "FIN";
			}
			return "#Error# | Los args de fin deben ser _";
		}
		
		if("repetir".equalsIgnoreCase(textoMensaje.trim())) {
			if(!(sonLetras(arg1))) {
				return "#Error# | En repetir el arg1 debe ser una palabra";
			}
			if(!(sonNumeros(arg2))) {
				return "#Error# | En repetir el arg2 debe ser número";
			}
			
			return arg1.repeat(Integer.parseInt(arg2));
			
		}else if("contar".equalsIgnoreCase(textoMensaje.trim())) {
			if(!(arg2.length() == 1)) {
				return "#Error# | En contar el arg2 debe ser una sola letra";
			}
			if((!(sonLetras(arg1) && sonLetras(arg2)))) {
				return "#Error# | En contar el arg1 y el arg2 deben contener letras";
			}
			char letra = arg2.charAt(0);
			int numVeces = 0;
			for (int i = 0; i < arg1.length(); i++) {
				if(arg1.charAt(i) == letra) {
					numVeces++;
				}
			}
			return Integer.toString(numVeces);
			
		}else if("mayusculas".equalsIgnoreCase(textoMensaje.trim())) {
			if(!(arg2.equals("_"))) {
				return "#Error# | En mayusculas el arg2 debe ser _";
			}
			if((palabraValida(arg1))) {
				return "#Error# | En mayusculas el arg1 debe ser una palabra";
			}
			if(sonNumeros(arg1)) {
				return "#Error# | En mayusculas el arg1 no puede contener numeros";
			}
			
			return arg1.toUpperCase();
		}
		
		return "ERROR";
	}
	
	private boolean sonLetras(String mensaje) {
		for (int i = 0; i < mensaje.length(); i++) {
			if(!Character.isLetter(mensaje.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean sonNumeros(String mensaje) {
		for (int i = 0; i < mensaje.length(); i++) {
			if(!Character.isDigit(mensaje.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean palabraValida(String palabra) {
		return !palabra.equals("_") && sonLetras(palabra);
	}
	
	public static void main(String[] args) {
		Comandos c1 = new Comandos();
		System.out.println(c1.verificarComandos("REPETIR|Hola|3|_"));
		System.out.println(c1.verificarComandos("contar|banana|a|_"));
		System.out.println(c1.verificarComandos("contar|mapa|a|_"));
		System.out.println(c1.verificarComandos("mayusculas|mapa|_|_"));
		System.out.println(c1.verificarComandos("mayusculas|Patito|_|_"));
		
		System.out.printf("%n--ERRORES--%n");
		
		System.out.printf("%n-Repetir-%n");
		System.out.println(c1.verificarComandos("REPETIR|Hola|_|_"));
		System.out.println(c1.verificarComandos("REPETIR|52|_|_"));
		System.out.println(c1.verificarComandos("REPETIR|_|_|_"));
		
		System.out.printf("%n-Contar-%n");
		System.out.println(c1.verificarComandos("contar|banana|_|_"));
		System.out.println(c1.verificarComandos("contar|6551|_|_"));
		System.out.println(c1.verificarComandos("contar|cuaderno|22|_"));
		
		System.out.printf("%n-Mayusculas-%n");
		System.out.println(c1.verificarComandos("mayusculas|_|_|_"));
		System.out.println(c1.verificarComandos("mayusculas|25|_|_"));
		
		System.out.printf("%n-Salir-%n");
		System.out.println(c1.verificarComandos("salir|_|_|_"));
		System.out.println(c1.verificarComandos("salir|j|_|_"));
		System.out.println(c1.verificarComandos("salir|_|j|_"));
		System.out.println(c1.verificarComandos("salir|_|_|j"));
		
	}
}

package practica.socket.hilos.comandosdiferentes;

public class GestoriaComandos {
	private static final String CMD_REPETIR = "Repetir";
	private static final String CMD_CONTAR = "Contar";
	private static final String CMD_MAYUSCULAS = "Mayusculas";
	private static final String CMD_INVERTIR = "Invertir";
	private static final String CMD_SALIR = "Salir";
	
	public String verificarComando(String mensaje) {
		if(mensaje == null || mensaje.isBlank()) {
			return "#Error# | El mensaje no puede ser nulo ni estar vacío";
		}
		
		mensaje = mensaje.trim();
		
		if(!(mensaje.endsWith("_"))) {
			return "#Error# | El mensaje debe terminar con _";
		}
		
		String [] mensajePartes = mensaje.split("\\|", -1);
		if(mensajePartes.length != 4) {
			return "#Error# | El mensaje debe tener 4 partes";
		}
		
		String mensajeReducido = mensaje.substring(0, mensaje.length() - 1);
		String [] mensajeArray = mensajeReducido.split("\\|", - 1);
		
		String textoMensaje = mensajeArray[0].trim();
		String arg1 = mensajeArray[1].trim();
		String arg2 = mensajeArray[2].trim();
		
		if(CMD_SALIR.equalsIgnoreCase(textoMensaje)) {
			if(esGuionBajo(arg1) && esGuionBajo(arg2)) {
				return "FIN";
			}
			return "#Error# | El comando salir solo puede tener guiones bajos";
		}
		
		//REPETIR
		if(CMD_REPETIR.equalsIgnoreCase(textoMensaje)) {
			if(!sonLetras(arg1)) {
				return "#Error# | El arg1 del comando repetir debe ser letras";
			}
			
			if(!esNumero(arg2)) {
				return "#Error# | El arg2 del comando repetir debe ser un numero";
			}
			
			return arg1.repeat(Integer.parseInt(arg2));
		}
		
		//CONTAR
		if(CMD_CONTAR.equalsIgnoreCase(textoMensaje)) {
			if(arg1.length() <= 2) {
				return "#Error# | El arg1 del comando contar debe tener 2 o más letras";
			}
			
			if(!sonLetras(arg2) || arg2.length() > 1) {
				return "#Error# | El arg2 del comando contar debe ser una letra";
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
		
		//MAYUSCULAS
		if(CMD_MAYUSCULAS.equalsIgnoreCase(textoMensaje)) {
			if(!esGuionBajo(arg2)) {
				return "#Error# | El arg2 del comando mayusculas debe ser guion bajo";
			}
			if(!sonLetras(arg1)) {
				return "#Error# | El arg1 del comando mayusculas deben ser letras";
			}
			return arg1.toUpperCase();
		}
		
		
		//Invertir
		if(CMD_INVERTIR.equalsIgnoreCase(textoMensaje)) {
			if(!esGuionBajo(arg2)) {
				return "#Error# | El arg2 del comando invertir debe ser guion bajo";
			}
			if(!sonLetras(arg1)) {
				return "#Error# | El arg1 del comando mayusculas deben ser letras";
			}
			
			StringBuilder sb = new StringBuilder();
			for (int i = arg1.length() - 1; i >= 0; i--) {
				sb.append(arg1.charAt(i));
			}
			return sb.toString();
		}
		
		return "#Error# | Comando no encontrado";
	}
	
	private boolean sonLetras(String texto) {
		for (int i = 0; i < texto.length(); i++) {
			if(!(Character.isLetter(texto.charAt(i)))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean esGuionBajo(String texto) {
		if(texto.trim().equals("_")) {
			return true;
		}
		return false;
	}
	
	private boolean esNumero(String texto) {
		for (int i = 0; i < texto.length(); i++) {
			if(!Character.isDigit(texto.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		GestoriaComandos c1 = new GestoriaComandos();
		System.out.println(c1.verificarComando("invertir| murcielago|_|_"));
		System.out.println(c1.verificarComando("mayusculas|batidora|_|_"));
		System.out.println(c1.verificarComando("contar |caracola|aa|_"));
		System.out.println(c1.verificarComando("salir |_|aa|_"));
		
	}
}

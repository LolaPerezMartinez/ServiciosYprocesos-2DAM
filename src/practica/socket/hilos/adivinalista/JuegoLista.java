package practica.socket.hilos.adivinalista;

import java.util.random.RandomGenerator;

public class JuegoLista {
	
	private static RandomGenerator random = RandomGenerator.getDefault();
	
	
	public String handlerMensajes (String mensaje) {
		int primerNumero;
		int segundoNumero;
		
		if(!mensaje.startsWith("#") || !mensaje.endsWith("#")) {
			return "Error: el mensaje debe empezar y terminar con '#'";
		}
		
		String mensajeLimpio = mensaje.substring(1, mensaje.length()-1);
		String [] arrayMensaje = mensajeLimpio.split("#");//[mensaje, numero1, numero2]
		
		String mensajeEnviadoPorCliente = arrayMensaje[0];
		
		if(mensajeEnviadoPorCliente.equals("Fin")) {
			if(arrayMensaje.length != 1) {
				return "Error: 'Fin' no debe llevar parámetros numéricos";
			}
			return "Finalizado";
		}
		
		if(arrayMensaje.length != 3) {
			return "Error: el comando debe tener 3 partes [comando, numeroInicio, numeroFin]";
		}
		
		try {
			primerNumero = Integer.parseInt(arrayMensaje[1]);
			segundoNumero = Integer.parseInt(arrayMensaje[2]);
		}catch(NumberFormatException e) {
			return "Error: los parámetros deben ser números enteros válidos";
		}
		
		if(mensajeEnviadoPorCliente.equals("Listado numeros")) {
			StringBuilder sb = new StringBuilder();
			for (int i = primerNumero; i <= segundoNumero; i++) {
				sb.append(i);
				if(i < segundoNumero) {
					sb.append("|");
				}
			}
			return sb.toString();
			
		}else if(mensajeEnviadoPorCliente.equals("Numero aleatorio")){
			int numeroAleatorio = random.nextInt(primerNumero, segundoNumero + 1);
			return Integer.toString(numeroAleatorio);
		}
		return "Error: comando desconocido";		
		
	}
	
	
}

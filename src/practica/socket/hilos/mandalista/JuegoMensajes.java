package practica.socket.hilos.mandalista;

import java.util.random.RandomGenerator;

public class JuegoMensajes {
	private static RandomGenerator random = RandomGenerator.getDefault();
	

	public String handlerMensaje(String mensaje) {
		int numInicial;
		int numFinal;
		
		if (!(mensaje.startsWith("#") && mensaje.endsWith("#"))) {
			return "Error: el mensaje debe empezar y terminar con #";
		}

		String mensajeLimpio = mensaje.substring(1, mensaje.length() - 1);// [Numero aleatorio#2#11]
		String[] arrayMensaje = mensajeLimpio.split("#");//los mensajes quedan sin # y en array
		
		String textoMensaje = arrayMensaje[0];
		if(textoMensaje.equals("Fin")) {
			if(arrayMensaje.length > 1) {
				return "Error: el mensaje Fin no contiene parámetros numéricos";
			}else {
				return "#Finalizado#";
			}
		}
		
		
		if(!(arrayMensaje.length == 3)) {
			return "Error: el mensaje debe contener 3 parámetros [mensaje, numero1, numero2]";
		}
		
		
		try {
			numInicial = Integer.parseInt(arrayMensaje[1]);
			numFinal = Integer.parseInt(arrayMensaje[2]);
			
		}catch (NumberFormatException e) {
			return "Error: los parámetros deben ser números enteros";
		}
		
		
		if(textoMensaje.equals("Listado numeros")) {
			StringBuilder sb = new StringBuilder();
			
			for (int i = numInicial; i <= numFinal; i++) {
				sb.append(i);
				if(i < numFinal) {
					sb.append("|");
				}
			}
			return sb.toString();
		}
		
		if(textoMensaje.equals("Numero aleatorio")) {
			return Integer.toString(random.nextInt(numInicial, numFinal + 1));
		}
		
		return "Error: comando no disponible";
	}

	public static void main(String[] args) {
		JuegoMensajes j1 = new JuegoMensajes();
		//System.out.println(Arrays.toString(j1.handlerMensaje("#Numero aleatorio#5#7#")));
		//j1.handlerMensaje("#Numero aleatorio#2#11#")

	}

}

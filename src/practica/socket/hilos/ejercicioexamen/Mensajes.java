package practica.socket.hilos.ejercicioexamen;

import java.util.random.RandomGenerator;

public class Mensajes {
	private static RandomGenerator random = RandomGenerator.getDefault();

	public String verificarMensaje(String mensaje) {

		int primerNumero;
		int segundoNumero;

		if (mensaje == null || mensaje.isBlank())
			return "#Error#";
		mensaje = mensaje.trim();

		if (!(mensaje.startsWith("#") && mensaje.endsWith("#"))) {
			return "#Error# | El mensaje debe comenzar y terminar por #";
		}

		String mensajeSinAlmohadillas = mensaje.substring(1, mensaje.length() - 1);
		String[] mensajeArray = mensajeSinAlmohadillas.split("#");// [fin] [listado numero, 2, 5]

		String textoMensaje = mensajeArray[0].trim();

		if ("fin".equalsIgnoreCase(textoMensaje)) {
			if (mensajeArray.length != 1) {
				return "#Error# | El comando fin solo puede tener un parámetro.";
			}
			return "#Finalizado#";
		}

		if (mensajeArray.length != 3) {
			return "#Error# | El comando debe contener 3 parámetros separados por #.";
		}

		String arg1 = mensajeArray[1].trim();
		String arg2 = mensajeArray[2].trim();

		try {
			primerNumero = Integer.parseInt(arg1);
			segundoNumero = Integer.parseInt(arg2);

		} catch (NumberFormatException e) {
			return "#Error# | Los argumentos 1 y 2 tienen que ser numéricos";
		}

		if (primerNumero > segundoNumero) {
			return "#Error# | El primer numero no puede ser mayor que el segundo";
		}

		if ("listado numeros".equalsIgnoreCase(textoMensaje.trim())) {
			StringBuilder sb = new StringBuilder();

			for (int i = primerNumero; i <= segundoNumero; i++) {
				sb.append(i);

				if (i < segundoNumero) {
					sb.append("|");
				}
			}
			return sb.toString();
		}

		if ("numero aleatorio".equalsIgnoreCase(textoMensaje.trim())) {
			return Integer.toString(random.nextInt(primerNumero, segundoNumero + 1));
		}

		return "#Error# | Comando no disponible";
	}

	public static void main(String[] args) {
		Mensajes m1 = new Mensajes();
		System.out.println(m1.verificarMensaje("#listado numeros#1#6#"));
		System.out.println(m1.verificarMensaje("#listado numeros#f#6#"));
		System.out.println(m1.verificarMensaje("#numero aleatorio#1#6#"));
		System.out.println(m1.verificarMensaje("#numero aleatorio#s#6#"));
		System.out.println(m1.verificarMensaje("#fin#"));
		System.out.println(m1.verificarMensaje("#fin#9#"));
	}

}

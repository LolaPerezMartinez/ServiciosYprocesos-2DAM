package practica.socket.hilos.adivinanzastry;

import java.util.Random;

/**
 * La clase JuegoAdivinaNumero implementa la lógica para un juego simple
 * donde se debe adivinar un número generado aleatoriamente.
 */
public class JuegoAdivinaNumero {
    private int numeroEscondido;

    /**
     * Constructor de JuegoAdivinaNumero.
     * Genera un número aleatorio entre 1 y 1000 (inclusive) que el jugador debe adivinar.
     */
    public JuegoAdivinaNumero() {
        numeroEscondido = new Random().nextInt(1000) + 1;
    }

    /**
     * Verifica si el intento del jugador es igual al número generado aleatoriamente.
     * 
     * @param intento El intento del jugador como un String.
     * @return "Acertado" si el intento coincide con el número escondido, "No acertado" en caso contrario.
     *         Si el intento no es un número válido, también devuelve "No acertado".
     */
    public String verificarIntento(String intento) {
        try {
            int numeroIntento = Integer.parseInt(intento);
            return numeroIntento == numeroEscondido ? "Acertado" : "No acertado";
        } catch (NumberFormatException e) {
            return "No acertado";
        }
    }
}
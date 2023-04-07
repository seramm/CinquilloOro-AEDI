/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que contiene la lógica interna del juego.
 * @author seram
 */
public class Juego {

	private final IU iu;
	private Baraja baraja;
	private List<Jugador> jugadores = new LinkedList<>();

	/**
	 * Crea un juego con su interfaz de usuario.
	 * @param iu interfaz de usuario del juego.
	 */
	public Juego(IU iu) {
		this.iu = iu;

	}

	/**
	 * Inicia una partida. Pregunta número de jugadores, los crea, reparte las cartas e inicia el juego.
	 */
	public void jugar() {
		//preguntar cuantos van a jugar
		//crear los jugadores
		//repartir las cartas entre los jugadores
		//mostrar el estado de los jugadores
		//indicar quien empieza la partida
		jugadores = iu.leeDatosJugadores();

		// Reparto de la baraja
		while (!baraja.getBaraja().isEmpty()) {
			for (Jugador i : jugadores) {
				i.anadirCarta(baraja.getBaraja().remove(0)); // Añadido de la primera carta de la baraja a la mano del jugador.
			}
		}

		//Mostrar la mano de cada jugador
		for (Jugador jugador : jugadores) {
			jugador.toString();
		}
	}

}

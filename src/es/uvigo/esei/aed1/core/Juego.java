/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;
import java.util.LinkedList;
import java.util.List;

public class Juego {

	private final IU iu;

	List<Carta> baraja = new LinkedList<>();
	private Baraja barajaClass = new Baraja(baraja);

	private List<Jugador> jugadores = new LinkedList<>();

	public Juego(IU iu) {
		this.iu = iu;

	}

	public void jugar() {
		//preguntar cuantos van a jugar
		//crear los jugadores
		//repartir las cartas entre los jugadores
		//mostrar el estado de los jugadores
		//indicar quien empieza la partida
		barajaClass.crearBaraja();
		barajaClass.barajarBaraja();
		jugadores = iu.leeDatosJugadores();
		// Reparto de la baraja
		while (!barajaClass.getBaraja().isEmpty()) {
			for (Jugador i : jugadores) {
				i.anadirCarta(barajaClass.getBaraja().remove(0)); // Añadido de la primera carta de la baraja a la mano del jugador.
			}
		}
		//Mostrar la mano de cada jugador
		for (Jugador jugador : jugadores) {
			iu.mostrarMensaje(jugador.toString());
		}
	}

}

/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;
import es.uvigo.esei.aed1.core.Baraja;
import es.uvigo.esei.aed1.core.Jugador;
import java.util.LinkedList;
import java.util.List;

public class Juego {

	private final IU iu;
	private Baraja baraja;
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
		jugadores = iu.leeDatosJugadores();

		// Reparto de la baraja
		while (!baraja.getBaraja().isEmpty()) {
			for (Jugador i : jugadores) {
				Carta carta = baraja.getBaraja().remove(0); // La carta que se coge se guarda en carta y se elimina de la baraja.
				i.anadirCarta(carta);
			}
		}
	}

}

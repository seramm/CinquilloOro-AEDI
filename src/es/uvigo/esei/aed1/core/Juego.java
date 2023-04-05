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
	}

}

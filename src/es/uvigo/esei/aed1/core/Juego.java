/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Clase que contiene la lógica interna del juego.
 * @author seram
 */
public class Juego {

	private final IU iu;

	List<Carta> baraja = new LinkedList<>();
	private Baraja barajaClass = new Baraja(baraja);

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
		barajaClass.crearBaraja();
		barajaClass.barajarBaraja();
		jugadores = iu.leeDatosJugadores();
		// Reparto de la baraja
		while (!barajaClass.getBaraja().isEmpty()) {
			for (Jugador i : jugadores) {
				i.anadirCarta(barajaClass.getBaraja().remove(0)); // Añadido de la primera carta de la baraja a la mano del jugador.
			}
		}
		//Mostrar jugadores
		iu.mostrarJugadores(jugadores);
		//Mostrar jugador que empieza
		jugadorAleatorio("El jugador inicial es: ");
	}

	public void jugadorAleatorio(String msg) {
		Random rand = new Random();
		StringBuilder text = new StringBuilder();
		text.append(msg);
		text.append(jugadores.get(rand.nextInt(jugadores.size())).getNombre());
		iu.mostrarMensaje(text.toString());
	}

}

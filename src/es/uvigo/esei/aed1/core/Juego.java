/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Clase que contiene la lógica interna del juego.
 *
 * @author seram
 */
public class Juego {

	private final IU iu;
	private Baraja baraja= new Baraja();
	private Mesa mesa;
	private List<Jugador> jugadores = new LinkedList<>();

	/**
	 * Crea un juego con su interfaz de usuario.
	 *
	 * @param iu interfaz de usuario del juego.
	 */
	public Juego(IU iu) {
		this.iu = iu;
		mesa = new Mesa();
	}

	/**
	 * Inicia una partida. Pregunta número de jugadores, los crea, reparte las
	 * cartas y selecciona el jugador inicial.
	 */
	public void jugar() {
		StringBuilder separador = new StringBuilder();
		StringBuilder text = new StringBuilder();
		for(int i = 0; i < 50; i++) {
			separador.append('#');
		}
		text.append(separador).append("\n\n\t\tJuego Cinquillo Oro\n").append("\t\t    6 de Copas\n\n").append(separador);
		iu.mostrarMensaje(text.toString());

		Collection<String> nombres = iu.leeDatosJugadores();
		for(String i : nombres){
			jugadores.add(new Jugador(i));
		}
		iu.mostrarMensaje("\nBarajando");
		baraja.barajarBaraja();		// Barajado
		iu.mostrarMensaje("Baraja mezclada");

		iu.mostrarMensaje("\nRepartiendo cartas");
		// Reparto de la baraja
		while (!baraja.getBaraja().isEmpty()) {
			for (Jugador i : jugadores) {
				i.anadirCarta(baraja.getBaraja().remove(0)); // Añadido de la primera carta de la baraja a la mano del jugador.
			}
		}
		//Mostrar jugadores
		iu.mostrarJugadores(jugadores);
		//Mostrar jugador que empieza
		jugadorAleatorio("\tEl jugador inicial es: ");

		iu.mostrarJugadores(jugadores);

		text = new StringBuilder();
		text.append("\n\n").append(separador).append("\n");
		iu.mostrarMensaje(text.toString());

		for(Jugador i : jugadores) {
			turno(i);
		}
		
	}

	public void turno(Jugador jugador) {
		boolean puede = false;
		Carta carta;
		iu.mostrarTurno(jugador, mesa);

		while(puede == false) {
			carta = iu.pedirCarta(jugador);
			System.out.println(carta.toString());
			if(jugador.getMano().contains(carta)) {
				puede = mesa.ponerCarta(carta);
				jugador.quitarCarta(carta);
			} else {
				iu.mostrarMensaje("No tienes la carta " + carta.toString());
			}
			iu.mostrarJugador(jugador);
		}
	}

	/**
	 * Escoge un jugador aleatorio de los contenidos en List<Jugador> jugadores.
	 *
	 * @param msg texto a mostrar antes del jugador seleccionado.
	 */
	private void jugadorAleatorio(String msg) {
		iu.mostrarMensaje("\n\nEscogiendo jugador aleatorio");
		Random rand = new Random(System.currentTimeMillis());
		Jugador jugadorRand = jugadores.get(rand.nextInt(jugadores.size()));
		StringBuilder text = new StringBuilder();
		text.append(msg);
		text.append(jugadorRand.getNombre());
		iu.mostrarMensaje(text.toString());
		Collections.rotate(jugadores, jugadores.indexOf(jugadorRand));
	}

}

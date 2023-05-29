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
	private Baraja baraja;
	private Mesa mesa;
	private List<Jugador> jugadores = new LinkedList<>();
	private int multiplicador = 0;
	private Jugador jugadorAs;
	private boolean as = false;

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

		StringBuilder text = new StringBuilder();
		text.append(iu.separador).append("\n\n\t\tJuego Cinquillo Oro\n").append("\t\t    6 de Copas\n\n").append(iu.separador);
		iu.mostrarMensaje(text.toString());

		Collection<String> nombres = iu.leeDatosJugadores();
		for (String i : nombres) {
			jugadores.add(new Jugador(i));
		}

		//Indice del jugador actual
		Jugador jugadorActual = jugadores.get(0);

		while (as == false) {
			baraja = new Baraja();
			iu.mostrarMensaje("\nBarajando");
			baraja.barajarBaraja();		// Barajado
			iu.mostrarMensaje("Baraja mezclada");

			iu.mostrarMensaje("\nRepartiendo cartas");
			// Reparto de la baraja
			while (!baraja.barajaVacia()) {
				for (Jugador i : jugadores) {
					i.anadirCarta(baraja.getBaraja().remove(0)); // Añadido de la primera carta de la baraja a la mano del jugador.

					//Guardar el jugador con el As de Oros en caso de que haya que sumarle los puntos de As más adelante
					if (i.getMano().contains(new Carta(1, Carta.PALOS.OROS))) {
						jugadorAs = i;
					}
				}
			}
			//Mostrar jugadores
			iu.mostrarJugadores(jugadores);
			//Mostrar jugador que empieza
			jugadorAleatorio();

			text = new StringBuilder();
			text.append("\n\n").append(iu.separador).append("\n\n");
			text.append("\t\tInicio del juego\n\n").append(iu.separador).append("\n");
			iu.mostrarMensaje(text.toString());

			//Rotacion de turnos de forma circular
			while (!jugadorActual.manoVacia()) {
				for (int i = 0; i < jugadores.size(); i++) {

					jugadorActual = jugadores.get(i);
					turno(jugadorActual);

					if (jugadorActual.manoVacia()) {
						break;
					}

				}
			}
			//Asignacion de puntos de partida
			jugadorActual.anadirPuntos(4);

			//Cada ronda los puntos del as de oros valen más
			multiplicador = multiplicador + 2;

			iu.mostrarMensaje(mesa.toStringGraph());
			iu.mostrarMensaje("El ganador es: " + jugadorActual.getNombre() + "\n");
			if (as == true) {
				break;
			}
			iu.mostrarMensaje(iu.separador);
			iu.mostrarMensaje("Nuevo juego: \n");

			//Reseteo de la mesa y la baraja
			mesa = new Mesa();

			//Vaciado de las manos de los jugadores
			for (Jugador i : jugadores) {
				i.getMano().clear();
			}
		}

		//Suma de los puntos de As al jugador que tenía el As, guardado previamente
		jugadorAs.anadirPuntos(multiplicador);
		iu.mostrarMensaje("Se ha colocado el as de oros \n");

		int max = Integer.MIN_VALUE;
		for (Jugador i : jugadores) {
			if (i.getPuntos() > max) {
				max = i.getPuntos();
			}
			iu.mostrarMensaje(i.getNombre() + ": " + i.getPuntos() + "\n");
		}
		iu.mostrarMensaje("Ganador/es: \n");
		for (Jugador i : jugadores) {
			if (i.getPuntos() == max) {
				iu.mostrarMensaje(i.getNombre() + "\n");
			}
		}

	}

	public void turno(Jugador jugador) {
		boolean puede = false;
		Carta carta;
		iu.mostrarTurno(jugador, mesa);

		if (puedeSeguir(jugador) == true) {
			while (puede == false) {
				carta = iu.pedirCarta(jugador);

				if (jugador.tieneCarta(carta)) {
					if (carta.equals(new Carta(1, Carta.PALOS.OROS))) {
						as = true;
					}
					puede = mesa.ponerCarta(carta);
					if(puede) {
						jugador.quitarCarta(carta);
					}

				} else {
					iu.mostrarMensaje("No tienes la carta " + carta.toString());
				}
			}
		} else {
			iu.mostrarMensaje("Turno saltado");
		}
	}

	/**
	 * Escoge un jugador aleatorio de los contenidos en List<Jugador> jugadores.
	 *
	 * @param msg texto a mostrar antes del jugador seleccionado.
	 */
	private void jugadorAleatorio() {
		iu.mostrarMensaje("\n\nEscogiendo jugador aleatorio");
		Random rand = new Random(System.currentTimeMillis());
		Jugador jugadorRand = jugadores.get(rand.nextInt(jugadores.size()));
		StringBuilder text = new StringBuilder();
		text.append("\tEl jugador inicial es: ");
		text.append(jugadorRand.getNombre());
		iu.mostrarMensaje(text.toString());
		Collections.rotate(jugadores, -jugadores.indexOf(jugadorRand));
	}

	private boolean puedeSeguir(Jugador jugador) {
		boolean puede = false;

		for (int i = 0; i < jugador.getMano().size(); i++) {
			if (jugador.getMano().get(i).getNumero() == 5) {
				puede = true;
			}
		}

		if (puede == false) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < jugador.getMano().size(); j++) {
					if (mesa.getCartas()[i].peekFirst() == null || mesa.getCartas()[i].peekLast() == null) {
						break;
					} else if (mesa.getCartas()[i].peekFirst().cartaProxima(jugador.getMano().get(j))) {
						puede = true;
						break;
					} else if (mesa.getCartas()[i].peekLast().cartaProxima(jugador.getMano().get(j))) {
						puede = true;
						break;
					}

				}

			}
		}

		return puede;
	}

}

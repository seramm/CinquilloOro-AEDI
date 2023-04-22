/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;
import java.util.Collection;
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
		StringBuilder inicio = new StringBuilder();
		for(int i = 0; i < 50; i++) {
			separador.append('#');
		}
		inicio.append(separador).append("\n\n\t\tJuego Cinquillo Oro\n").append("\t\t    6 de Copas\n\n").append(separador);
		iu.mostrarMensaje(inicio.toString());

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
	}

	public void turno(Jugador jugador, Mesa mesa) {
		boolean puede = false;
		iu.mostrarTurno(jugador, mesa);

		for (int i = 0; i < jugador.getMano().size(); i++) {
			for (int j = 0; j < 4; j++) {
				if (jugador.getMano().get(i).getNumero() == mesa.getCartas()[j].element().getNumero() + 1) {
					puede = true;
				} else if (jugador.getMano().get(i).getNumero() == mesa.getCartas()[j].element().getNumero() - 1) {
					puede = true;
				}
			}
		}
		if (puede == true) {  //Si puede jugar al menos una carta se sigue con el turno
			iu.mostrarMensaje("Selecciona una carta para jugar: \n");
			//Implementar eleccion de carta
		} else {
			iu.mostrarMensaje("No puedes jugar ninguna carta\n");
		}   //Si no puede jugar ninguna carta se le avisa y se acaba el turno

	}

	/**
	 * Escoge un jugador aleatorio de los contenidos en List<Jugador> jugadores.
	 *
	 * @param msg texto a mostrar antes del jugador seleccionado.
	 */
	private void jugadorAleatorio(String msg) {
		iu.mostrarMensaje("\n\nEscogiendo jugador aleatorio");
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder text = new StringBuilder();
		text.append(msg);
		text.append(jugadores.get(rand.nextInt(jugadores.size())).getNombre());
		iu.mostrarMensaje(text.toString());
	}

}

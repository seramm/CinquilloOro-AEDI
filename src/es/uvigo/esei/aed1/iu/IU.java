/**
 * Representa la interfaz del juego del Cinquillo-Oro, en este proyecto va a ser una entrada/salida en modo texto
 * Se recomienda una implementación modular.
 */
package es.uvigo.esei.aed1.iu;

import java.util.List;
import java.util.LinkedList;
import es.uvigo.esei.aed1.core.Jugador;
import es.uvigo.esei.aed1.core.Mesa;
import es.uvigo.esei.aed1.core.Carta;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que contiene la entrada/salida.
 *
 * @author seram
 */
public class IU {

	public final String separador;
	private final Scanner teclado;

	/**
	 * Crea una interfaz de usuario, añadiendo una nueva entrada.
	 */
	public IU() {
		teclado = new Scanner(System.in).useDelimiter("\r?\n");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 50; i++) {
			sb.append('#');
		}
		separador = sb.toString();
	}

	/**
	 * Lee un entero introducido de teclado.
	 *
	 * @param msg el mensaje a visualizar.
	 * @return el número, como entero
	 */
	public int leeNum(String msg) {
		do {
			System.out.print(msg);

			try {
				return teclado.nextInt();
			} catch (InputMismatchException exc) {
				teclado.next();
				System.out.println("Entrada no válida. Debe ser un entero.");
			}
		} while (true);
	}

	/**
	 * Lee un String introducido de teclado.
	 *
	 * @param msg el mensaje a visualizar.
	 * @return el String.
	 */
	public String leeString(String msg) {
		System.out.print(msg);
		return teclado.next();
	}

	/**
	 * Lee un String introducido de teclado.
	 *
	 * @param msg el mensaje a visualizar.
	 * @param args parámetros para la visualización
	 * @return el String.
	 */
	public String leeString(String msg, Object... args) {
		System.out.printf(msg, args);
		return teclado.next();
	}

	/**
	 * Muestra por pantalla un mensaje.
	 *
	 * @param msg el mensaje a mostrar.
	 */
	public void mostrarMensaje(String msg) {
		System.out.println(msg);
	}

	/**
	 * Muestra por pantalla un mensaje.
	 *
	 * @param msg el mensaje a mostrar
	 * @param args parámetros para la visualización.
	 */
	public void mostrarMensaje(String msg, Object... args) {
		System.out.printf(msg, args);
	}

	/**
	 * Lee por teclado el número de jugadores y sus nombres.
	 *
	 * @return lista de jugadores, como List.
	 */
	public Collection<String> leeDatosJugadores() {
		int n;
		List<String> jugadores = new LinkedList<>();
		do {
			n = leeNum("Introduce número de jugadores: ");
		} while (n < 3 || n > 4);

		System.out.print("\n");

		for (int i = 0; i < n; i++) {
			String nombre;
			do {
				nombre = leeString("\tIntroduce nombre del jugador " + (i + 1) + ": ");
			} while (nombre.isBlank() || nombre.isEmpty());
			jugadores.add(nombre);
		}
		return jugadores;
	}

	/**
	 * Muestra en pantalla la información sobre un jugador.
	 *
	 * @param jugador el jugador a mostrar.
	 */
	public void mostrarJugador(Jugador jugador) {
		mostrarMensaje(jugador.toString());

	}

	/**
	 * Muestra en pantalla la información de los jugadores
	 *
	 * @param jugadores jugadores a mostrar.
	 */
	public void mostrarJugadores(Collection<Jugador> jugadores) {
		mostrarMensaje("Mostrando jugadores\n");
		for (Jugador jugador : jugadores) {
			mostrarJugador(jugador);
		}
	}

	public void mostrarTurno(Jugador jugador, Mesa mesa) {
		mostrarMensaje("\n\nEstado del turno: \t");
		System.out.println(mesa.toStringGraph());
		mostrarJugador(jugador);
	}

	public Carta pedirCarta(Jugador jugador) {
		Carta toret = null;
		boolean valido;

		
		do {
			try {
				String carta = leeString("Carta que quieres poner(El formato es NN-X o NNX, donde NN es el número y X la inicial del palo): ");
				toret = Carta.stringToCarta(carta);
				valido = true;
			} catch(NumberFormatException exc) {
				valido = false;
			}
		} while(!valido);

		return toret;

	}
}

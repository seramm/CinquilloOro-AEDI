/*
* Representa la Mesa de juego. 
* Estructura: se utilizar� un TAD adecuado. Piensa que hay 4 palos y de cada palo se pueden colocar cartas 
* por cualquiera de los dos extremos (un array con 4 doblescolas parece lo m�s adecuado). La DobleCola se coment� en clase de teor�a
* Funcionalidad: saber si es posible colocar una carta en la mesa, colocar una carta en la mesa, mostrar la mesa
 */
package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;

import java.util.Deque;
import java.util.ArrayDeque;

public class Mesa {

	private Deque<Carta>[] cartas;

	//constructor
	public Mesa() {
		cartas = new Deque[4];
		for(int i = 0; i < 4; i++) {
			cartas[i] = new ArrayDeque<>();
		}
	}

	public void ponerCarta(Carta carta) {
		Deque<Carta> dequeCarta = cartas[carta.getPalo().ordinal()];

		if (dequeCarta.isEmpty()) {
			if (carta.getNumero() == 5) {
				dequeCarta.add(carta);
				System.out.println("Has puesto la carta " + carta.toString());
			} else {
				System.out.println("Carta no válida.");
			}
		} else if (carta.getNumero() == (dequeCarta.getFirst().getNumero() - 1)) {
			dequeCarta.addFirst(carta);
			System.out.println("Has puesto la carta " + carta.toString());
		} else if (carta.getNumero() == (dequeCarta.getLast().getNumero() + 1)) {
			dequeCarta.addLast(carta);
			System.out.println("Has puesto la carta " + carta.toString());
		} else {
			System.out.println("La carta " + carta.toString() + " no es válida.");
		}
	}

	@Override
	public String toString() {
	}

}

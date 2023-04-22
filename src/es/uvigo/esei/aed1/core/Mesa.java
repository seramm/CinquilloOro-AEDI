/*
* Representa la Mesa de juego. 
* Estructura: se utilizar� un TAD adecuado. Piensa que hay 4 palos y de cada palo se pueden colocar cartas 
* por cualquiera de los dos extremos (un array con 4 doblescolas parece lo m�s adecuado). La DobleCola se coment� en clase de teor�a
* Funcionalidad: saber si es posible colocar una carta en la mesa, colocar una carta en la mesa, mostrar la mesa
 */
package es.uvigo.esei.aed1.core;

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

	//a�adir m�s funcionalidades
	// mostrar el estado de la mesa
	@Override
	public String toString() {
	}

}

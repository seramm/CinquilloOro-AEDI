/*
* Representa la baraja española pero con 8 y 9, en total 48 cartas, 4 palos, valores de las cartas de 1 a 12. 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */
package es.uvigo.esei.aed1.core;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class Baraja {

	private List<Carta> baraja = new LinkedList<>();

	public Baraja(List<Carta> baraja) {
		this.baraja = baraja;
	}

	public List<Carta> getBaraja() {
		return baraja;
	}

	public void crearBaraja() {
		for (Carta.PALOS palo : Carta.PALOS.values()) {
			for (int i = 1; i <= 12; i++) {
				Carta nuevaCarta = new Carta(i, palo);
				baraja.add(nuevaCarta);
			}
		}
	}

	public void barajarBaraja() {
		Random randomness = new Random(System.currentTimeMillis());
		Collections.shuffle(baraja, randomness);
	}

}

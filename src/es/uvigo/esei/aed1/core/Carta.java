/*
 * Representa una carta, formada por un número y su palo correspondiente
 */
package es.uvigo.esei.aed1.core;

/**
 * Representa una carta, formada por un número y su palo correspondiente.
 *
 * @author seram
 */
public class Carta {

	/**
	 * Enumerado de los palos disponibles
	 */
	public enum PALOS {
		BASTOS, COPAS, ESPADAS, OROS
	};
	private final int numero;
	private final PALOS palo;

	/**
	 * Define una carta con su número y palo
	 */
	public Carta(int numero, PALOS palo) {
		this.numero = numero;
		this.palo = palo;
	}

	/**
	 * Devuelve el número de la carta.
	 *
	 * @return el número de la carta, como entero.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Devuelve el palo de la carta.
	 *
	 * @return el palo de la carta, como Carta.PALO.
	 */
	public PALOS getPalo() {
		return palo;
	}

	@Override
	public boolean equals(Object object) {
		boolean toret = false;
		if(object instanceof Carta) {
			toret = (this.numero == ((Carta) object).numero) && (this.palo == ((Carta) object).palo);
		}
		return toret;
	}
	@Override
	public String toString() {
		StringBuilder toret = new StringBuilder();

		toret.append(numero).append(" de ");

		if (null == getPalo()) {
			toret.append("oros");
		} else {
			switch (getPalo()) {
				case BASTOS:
					toret.append("bastos");
					break;
				case COPAS:
					toret.append("copas");
					break;
				case ESPADAS:
					toret.append("espadas");
					break;
				case OROS:
					toret.append("oros");
					break;
			}
		}

		return toret.toString();
	}

}

/*
 * Representa una carta, formada por un número y su palo correspondiente
 */
package es.uvigo.esei.aed1.core;

/**
 * Representa una carta, formada por un número y su palo correspondiente.
 * @author seram
 */
public class Carta {

	/**
	 * Enumerado de los palos disponibles
	 */
	public enum PALOS {
		BASTOS, COPAS, ESPADAS, OROS
	};
	private int numero;
	private PALOS palo;

	/**
	 * Define una carta con su número y palo
	 */
	public Carta(int numero, PALOS palo) {
		this.numero = numero;
		this.palo = palo;
	}

	/**
	 * Cambia el número de la carta.
	 * @param numero número de la carta.
	 * @throws Exception si el número no se encuentra entre 1 y 12, incluídos.
	 */
	public void setNumero(int numero) throws Exception {
		this.numero = numero;
		if (numero < 1 || numero > 12) {
			throw new Exception("Número de carta no válido. No se encuentra en 1 y 12.");
		}
	}

	/**
	 * Cambia el palo de la carta.
	 * @param palo palo de la carta.
	 */
	public void setPalo(PALOS palo) {
		this.palo = palo;
	}

	/**
	 * Devuelve el número de la carta.
	 * @return el número de la carta, como entero.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Devuelve el palo de la carta.
	 * @return el palo de la carta, como Carta.PALO.
	 */
	public PALOS getPalo() {
		return palo;
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

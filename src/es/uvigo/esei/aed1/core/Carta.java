/*
 * Representa una carta, formada por un número y su palo correspondiente
 */
package es.uvigo.esei.aed1.core;

public class Carta {

	public enum PALOS {
		BASTOS, COPAS, ESPADAS, OROS
	};
	private int numero;
	private PALOS palo;

	public Carta(int numero, PALOS palo) {
		this.numero = numero;
		this.palo = palo;
	}

	public void setNumero(int numero) throws Exception {
		this.numero = numero;
		if (numero < 1 || numero > 12) {
			throw new Exception("Número de carta no válido. No se encuentra en 1 y 12.");
		}
	}

	public void setPalo(PALOS palo) {
		this.palo = palo;
	}

	public int getNumero() {
		return numero;
	}

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

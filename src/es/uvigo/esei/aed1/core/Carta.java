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

	//Colores para cada palo
	public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RESET = "\u001B[0m";

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

	public static Carta stringToCarta(String carta) {
		boolean encontrado = false;
		PALOS[] palos = PALOS.values();

		String[] splitCarta = carta.split("-");
		int num = Integer.parseInt(splitCarta[0]);
		PALOS paloCarta = PALOS.BASTOS;

		for (int i = 0; i < palos.length && !encontrado; i++) {
			if (splitCarta[1].toUpperCase().charAt(0) == palos[i].toString().charAt(0)) {
				paloCarta = palos[i];
				encontrado = true;
			}
		}

		return new Carta(num, paloCarta);

	}

	@Override
	public boolean equals(Object object) {
		boolean toret = false;
		if (object instanceof Carta) {
			toret = (this.numero == ((Carta) object).numero) && (this.palo == ((Carta) object).palo);
		}
		return toret;
	}

	public boolean cartaProxima(Carta carta) {
		boolean toret = false;
		if (this.palo == carta.getPalo()) {
			if(this.numero == carta.numero - 1 || this.numero == carta.numero + 1) {
				toret = true;
			}
		}
		return toret;
	}

	@Override
	public String toString() {
		StringBuilder toret = new StringBuilder();

		if (null == getPalo()) {
			toret.append("oros");
		} else {
			switch (getPalo()) {
				case BASTOS:
					toret.append(ANSI_GREEN + numero + " de " + "bastos");
					break;
				case COPAS:
					toret.append(ANSI_RED + numero + " de " + "copas");
					break;
				case ESPADAS:
					toret.append(ANSI_CYAN + numero + " de " + "espadas");
					break;
				case OROS:
					toret.append(YELLOW_BOLD + numero + " de " + "oros");
					break;
			}
		}
		toret.append(ANSI_RESET);
		return toret.toString();
	}

	public String toStringShort() {
		StringBuilder toret = new StringBuilder();

		switch (getPalo()) {
			case BASTOS:
				toret.append(ANSI_GREEN + String.format("%02d", getNumero())).append("-");
				toret.append(getPalo().toString().toUpperCase().charAt(0) + ANSI_RESET);
				break;
			case COPAS:
				toret.append(ANSI_RED + String.format("%02d", getNumero())).append("-");
				toret.append(getPalo().toString().toUpperCase().charAt(0) + ANSI_RESET);
				break;
			case ESPADAS:
				toret.append(ANSI_CYAN + String.format("%02d", getNumero())).append("-");
				toret.append(getPalo().toString().toUpperCase().charAt(0) + ANSI_RESET);
				break;
			case OROS:
				toret.append(YELLOW_BOLD + String.format("%02d", getNumero())).append("-");
				toret.append(getPalo().toString().toUpperCase().charAt(0) + ANSI_RESET);
				break;
		}

		return toret.toString();
	}

}

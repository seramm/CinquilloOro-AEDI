/*
 * Representa una carta, formada por un número y su palo correspondiente
 */
package es.uvigo.esei.aed1.core;

import java.lang.NumberFormatException;
import es.uvigo.esei.aed1.iu.IU;

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

	/*public static Carta stringToCarta(String carta) {
		boolean encontrado = false;
		PALOS[] palos = PALOS.values();
                
                if(carta == null){
                    return null;
                }else{
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
	}
        */
        
        public static Carta stringToCarta(String carta) {
		boolean encontrado = false;
		PALOS[] palos = PALOS.values();
                
                try {
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
                catch(NumberFormatException e)
                {
                    IU iu = new IU();
                    iu.mostrarMensaje("Error: " + e.getMessage());
                }
                
                return null;
	}

	@Override
	public boolean equals(Object object) {
		boolean toret = false;
		if (object instanceof Carta) {
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

	public String toStringShort() {
		StringBuilder toret = new StringBuilder();
		toret.append(String.format("%02d", getNumero())).append("-");
		toret.append(getPalo().toString().toUpperCase().charAt(0));

		return toret.toString();
	}

}

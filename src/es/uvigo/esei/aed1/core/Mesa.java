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

	public Mesa() {
		cartas = new Deque[4];
		for (int i = 0; i < 4; i++) {
			cartas[i] = new ArrayDeque<>();
		}
	}

	public boolean ponerCarta(Carta carta) {
		Deque<Carta> dequeCarta = cartas[carta.getPalo().ordinal()];
		boolean toret = true;

		if (dequeCarta.isEmpty()) {
			if (carta.getNumero() == 5) {
				dequeCarta.add(carta);
				System.out.println("Has puesto la carta inicial " + carta.toString());
			} else {
				System.out.println("Carta no válida.");
				toret = false;
			}
		} else if (carta.getNumero() == (dequeCarta.getFirst().getNumero() - 1)) {
			dequeCarta.addFirst(carta);
			System.out.println("Has puesto la carta " + carta.toString());
		} else if (carta.getNumero() == (dequeCarta.getLast().getNumero() + 1)) {
			dequeCarta.addLast(carta);
			System.out.println("Has puesto la carta " + carta.toString());
		} else {
			System.out.println("La carta " + carta.toString() + " no es válida.");
			toret = false;
		}
		return toret;
	}

	public Deque<Carta>[] getCartas() {
		return cartas;
	}

	@Override
	public String toString() {
		StringBuilder toret = new StringBuilder("\n");

		toret.append("Estado de la mesa: ");
		for (Carta.PALOS i : Carta.PALOS.values()) {
			toret.append("\n\t").append(i.toString()).append(":");
			for (Carta j : getCartas()[i.ordinal()]) {
				toret.append(j.toString()).append(", ");
			}
		}

		return toret.toString();
	}

	public String toStringGraph() {
		StringBuilder toret = new StringBuilder();

		String[][] datos = new String[13][4];
		for (Carta.PALOS i : Carta.PALOS.values()) {
			datos[0][i.ordinal()] = i.toString();
			for (Carta j : getCartas()[i.ordinal()]) {
				datos[datos.length - j.getNumero()][i.ordinal()] = j.toStringShort();
			}
		}

		for (int i = 0; i < datos.length; i++) {
			for (int j = 0; j < datos[i].length; j++) {
				if (datos[i][j] == null) {
					toret.append("    ");
				} else {
					toret.append(datos[i][j]);
				}
				toret.append("\t");
			}
			toret.append("\n");
		}

		return toret.toString();
	}
        
        public boolean as(Mesa mesa){
            Carta as = new Carta(1,Carta.PALOS.OROS);
            boolean esta = false;
            for (int i = 0; i < 4; i++) {
                if(mesa.cartas[i].contains(as)){
                    esta = true;
                }
            }
            return esta;
        }

}

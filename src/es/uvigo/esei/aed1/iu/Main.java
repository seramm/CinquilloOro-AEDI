/**
 * Cinquillo Oro - AEDI
 * 6 de Copas
 * https://github.com/seramm/CinquilloOro-AEDI
 * 
 * ENTREGA FINAL
 * 
 */
package es.uvigo.esei.aed1.iu;

import es.uvigo.esei.aed1.core.Juego;

public class Main {

	public static void main(String[] args) {
		IU iu = new IU();
		Juego cinquillo = new Juego(iu);
		cinquillo.jugar();
	}
}

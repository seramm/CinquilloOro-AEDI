/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 */
package es.uvigo.esei.aed1.core;

import java.util.List;
import java.util.LinkedList;

/**
 * Clase que representa un jugador con su nombre y cartas.
 *
 * @author seram
 */
public class Jugador {

	private String nombre;
	private List<Carta> mano = new LinkedList<>();
        private int puntos = 0;
        
        
        

	/**
	 * Crea un jugador con su nombre.
	 *
	 * @param nombre nombre del jugador.
	 */
	public Jugador(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el nombre del jugador.
	 *
	 * @return nombre del jugador, como String.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Devuelve la mano del jugador.
	 *
	 * @return mano que tiene el jugador, como List.
	 */
	public List<Carta> getMano() {
		return mano;
	}

	/**
	 * Cambia el nombre del jugador.
	 *
	 * @param nombre nombre del jugador.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Cambia la mano del jugador.
	 *
	 * @param mano mano del jugador.
	 */
	public void setMano(List<Carta> mano) {
		this.mano = mano;
	}

        public int getPuntos() {
            return puntos;
        }

        public void setPuntos(int puntos) {
            this.puntos = puntos;
        }

        




        
	/**
	 * Añade una carta a la mano del jugador.
	 *
	 * @param carta la carta a añadir.
	 */
	public void anadirCarta(Carta carta) {
		mano.add(carta);
	}

	public void quitarCarta(Carta carta) {
		mano.remove(carta);
	}

	@Override
	public String toString() {
		StringBuilder toret = new StringBuilder();

		toret.append("Jugador: ").append(nombre).append("\n");
		toret.append("\tCartas:");

		for (Carta carta : mano) {
			toret.append(carta.toString()).append(", ");
		}
		return toret.toString();
	}

}

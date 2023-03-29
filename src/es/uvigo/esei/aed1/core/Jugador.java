/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 */

package es.uvigo.esei.aed1.core;
import java.util.List;
import java.util.LinkedList;


public class Jugador {
    private String nombre;
    private List<Carta> mano = new LinkedList<>();

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Carta> getMano() {
        return mano;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMano(List<Carta> mano) {
        this.mano = mano;
    }
    
    
}

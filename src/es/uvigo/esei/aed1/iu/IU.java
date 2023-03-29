/**
 * Representa la interfaz del juego del Cinquillo-Oro, en este proyecto va a ser una entrada/salida en modo texto 
 * Se recomienda una implementación modular.
 */

package es.uvigo.esei.aed1.iu;

import java.util.List;
import java.util.LinkedList;
import es.uvigo.esei.aed1.core.Jugador;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IU {
    private final Scanner teclado;

    public IU() {
            teclado = new Scanner(System.in).useDelimiter("\r?\n");
    }

    /**
     * Lee un num. de teclado
     * 
     * @param msg El mensaje a visualizar.
     * @return El num., como entero
     */
    public int leeNum(String msg) {
        do {
                System.out.print(msg);

                try {
                        return teclado.nextInt();
                } catch (InputMismatchException exc) {
                        teclado.next();
                        System.out.println("Entrada no válida. Debe ser un entero.");
                }
        } while (true);
    }

    public String leeString(String msg) {
        System.out.print(msg);
        return teclado.next();
    }

    public String leeString(String msg, Object... args) {
        System.out.printf(msg, args);
        return teclado.next();
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public void mostrarMensaje(String msg, Object... args) {
        System.out.printf(msg, args);
    }



    public List<Jugador> pedirDatosJugadores(){
        int n;
        String nombre;
        Jugador j;
        List<Jugador> jugadores = new LinkedList<>();
        n = leeNum("Introduce número de jugadores");
        for (int i = 0; i < n; i++) {
            nombre = leeString("Introduce nombre del jugador "+(i+1));
            j = new Jugador(nombre);
            jugadores.add(0, j);
        }
        return jugadores;
    }



    public void mostrarJugador(Jugador jugador){

    }

    public void mostrarJugadores(Collection<Jugador> jugadores){

    }
   
    
}

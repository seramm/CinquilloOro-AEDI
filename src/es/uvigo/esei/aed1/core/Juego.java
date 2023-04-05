/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;
import java.util.LinkedList;
import java.util.List;


public class Juego{
  private final IU iu;
    
    
public Juego(IU iu){
    this.iu = iu;

}

public List<Jugador> pedirDatosJugadores() {
        int n;
        List<Jugador> jugadores = new LinkedList<>();
        do {
            n = leeNum("Introduce número de jugadores: ");
        } while (n < 3 && n > 4);

        System.out.print("\n");

        for (int i = 0; i < n; i++) {
            String nombre;
            nombre = leeString("Introduce nombre del jugador " + (i + 1) + ": ");
            Jugador j = new Jugador(nombre);
            jugadores.add(0, j);
        }
        return jugadores;
    }

public void jugar(){
	//preguntar cuantos van a jugar
	//crear los jugadores
	//repartir las cartas entre los jugadores
	//mostrar el estado de los jugadores
	//indicar quien empieza la partida
    
}

        
    
}

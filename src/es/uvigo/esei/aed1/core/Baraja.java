/*
* Representa la baraja española pero con 8 y 9, en total 48 cartas, 4 palos, valores de las cartas de 1 a 12. 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */

package es.uvigo.esei.aed1.core;
import lista.*;


public class Baraja {

    Lista<Carta> baraja = new ListaEnlazada<>();
    Carta carta;
    
    public Baraja(){
        for(int j = 0; j < 4; j++){
            for (int i = 0; i < 12; i++) {
               carta = new Carta(i+1,j);
               baraja.insertarPrincipio(carta);
            }
        }
    }
   
    public static void barajar(Lista<Carta> baraja){
 
    }
   
}

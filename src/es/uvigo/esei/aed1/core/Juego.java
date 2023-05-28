/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Clase que contiene la lógica interna del juego.
 *
 * @author seram
 */
public class Juego {

	private final IU iu;
	private Baraja baraja = new Baraja();
	private Mesa mesa;
	private List<Jugador> jugadores = new LinkedList<>();
        private int multiplicador = 0;
        private Jugador ganador = new Jugador("ganador");
        private Jugador jugadorAs;
        
	/**
	 * Crea un juego con su interfaz de usuario.
	 *
	 * @param iu interfaz de usuario del juego.
	 */
	public Juego(IU iu) {
		this.iu = iu;
		mesa = new Mesa();
	}

	/**
	 * Inicia una partida. Pregunta número de jugadores, los crea, reparte las
	 * cartas y selecciona el jugador inicial.
	 */
	public void jugar() {
            
		StringBuilder text = new StringBuilder();
		text.append(iu.separador).append("\n\n\t\tJuego Cinquillo Oro\n").append("\t\t    6 de Copas\n\n").append(iu.separador);
		iu.mostrarMensaje(text.toString());

		Collection<String> nombres = iu.leeDatosJugadores();
		for (String i : nombres) {
			jugadores.add(new Jugador(i));
		}
                
                //Indice del jugador actual
                    Jugador jugadorActual = jugadores.get(0);
                
                while(mesa.as(mesa) == false){
                    iu.mostrarMensaje("\nBarajando");
                    baraja.barajarBaraja();		// Barajado
                    iu.mostrarMensaje("Baraja mezclada");

                    iu.mostrarMensaje("\nRepartiendo cartas");
                    // Reparto de la baraja
                    while (!baraja.getBaraja().isEmpty()) {
                            for (Jugador i : jugadores) {
                                    i.anadirCarta(baraja.getBaraja().remove(0)); // Añadido de la primera carta de la baraja a la mano del jugador.
                                    
                                    //Guardar el jugador con el As de Oros en caso de que haya que sumarle los puntos de As más adelante
                                    if(i.getMano().contains(new Carta(1, Carta.PALOS.OROS))){
                                        jugadorAs = i;
                                    }
                            }
                    }
                    //Mostrar jugadores
                    iu.mostrarJugadores(jugadores);
                    //Mostrar jugador que empieza
                    jugadorAleatorio();

                    text = new StringBuilder();
                    text.append("\n\n").append(iu.separador).append("\n\n");
                    text.append("\t\tInicio del juego\n\n").append(iu.separador).append("\n");
                    iu.mostrarMensaje(text.toString());

                    

                    //Rotacion de turnos de forma circular
                    int indice = 0;
                    while (!jugadorActual.getMano().isEmpty()){
                        for (int i = 0; i < jugadores.size(); i++) {
                           
                            jugadorActual = jugadores.get(i);
                            turno(jugadorActual);
                            
                            if(jugadorActual.getMano().isEmpty()){
                                break;
                            }
                            
                        }                
                    }
                    //Asignacion de puntos de partida
                    jugadorActual.setPuntosPartida(jugadorActual.getPuntosPartida()+4);
                    
                    //Cada ronda los puntos del as de oros valen más
                    multiplicador = multiplicador + 2; 
                    
                    iu.mostrarMensaje(mesa.toStringGraph());
                    iu.mostrarMensaje("El ganador es: " + jugadorActual.getNombre() + "\n");
                    if(mesa.as(mesa) == true){
                        break;
                    }
                    iu.mostrarMensaje(iu.separador);
                    iu.mostrarMensaje("Nuevo juego: \n");
                    
                    //Reseteo de la mesa y la baraja
                    mesa = new Mesa();
                    baraja = new Baraja();
                    baraja.barajarBaraja();
                    
                    //Vaciado de las manos de los jugadores
                    for(Jugador i: jugadores){
                        i.getMano().clear();
                    }
                    
                    //Nuevo reparto de manos
                    while (!baraja.getBaraja().isEmpty()) {
                            for (Jugador i : jugadores) {
                                    i.anadirCarta(baraja.getBaraja().remove(0)); // Añadido de la primera carta de la baraja a la mano del jugador.
                            }
                    }
                    
                }
                
                //Suma de los puntos de As al jugador que tenía el As, guardado previamente
                jugadorAs.setPuntosOros(multiplicador);
                iu.mostrarMensaje("Se ha colocado el as de oros \n");
                
                ganador.setPuntosTotales(0); //Incializamos el "Ganador" a comaparar con 0 puntos
                
                for (int i = 0; i < jugadores.size(); i++) {
                    jugadores.get(i).setPuntosTotales(jugadores.get(i).getPuntosOros()+jugadores.get(i).getPuntosPartida());
                    iu.mostrarMensaje("Puntos jugador " + jugadores.get(i).getNombre() + ": \n" + jugadores.get(i).getPuntosTotales() + "\n");
                }
                for (int i = 0; i < jugadores.size(); i++) {
                    if(jugadores.get(i).getPuntosTotales() > ganador.getPuntosTotales()){
                       ganador = jugadores.get(i);
                    }
                }
               
                iu.mostrarMensaje("Ganador: \n" + ganador.getNombre());
	}

	public void turno(Jugador jugador) {
		boolean puede = false;
		Carta carta;
		iu.mostrarTurno(jugador, mesa);
                
                if(saltarTurno(jugador) == true){
		                  while (puede == false) {
                        carta = iu.pedirCarta(jugador);

                        if (jugador.getMano().contains(carta)) {
                            puede = mesa.ponerCarta(carta);
                            jugador.quitarCarta(carta);

                        } else {
                            iu.mostrarMensaje("No tienes la carta " + carta.toString());
                        }
                    }
                }else{
                    iu.mostrarMensaje("Turno saltado");
                }
	}       

	/**
	 * Escoge un jugador aleatorio de los contenidos en List<Jugador> jugadores.
	 *
	 * @param msg texto a mostrar antes del jugador seleccionado.
	 */
	private void jugadorAleatorio() {
		iu.mostrarMensaje("\n\nEscogiendo jugador aleatorio");
		Random rand = new Random(System.currentTimeMillis());
		Jugador jugadorRand = jugadores.get(rand.nextInt(jugadores.size()));
		StringBuilder text = new StringBuilder();
		text.append("\tEl jugador inicial es: ");
		text.append(jugadorRand.getNombre());
		iu.mostrarMensaje(text.toString());
		Collections.rotate(jugadores, -jugadores.indexOf(jugadorRand));
	}
        
    private boolean saltarTurno(Jugador jugador) {
        boolean puede = false;
        
        for (int i = 0; i < jugador.getMano().size(); i++) {
            if (jugador.getMano().get(i).getNumero() == 5) {
                puede = true;
            } 
        }
        
        if(puede == false){
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < jugador.getMano().size(); j++) {
                    if(mesa.getCartas()[i].peekFirst() == null || mesa.getCartas()[i].peekLast() == null){
                        break;
                    }else if(mesa.getCartas()[i].peekFirst().getNumero() == jugador.getMano().get(j).getNumero()+1 && mesa.getCartas()[i].peekFirst().getPalo()== jugador.getMano().get(j).getPalo()){
                        puede = true;
                        break;
                    }else if(mesa.getCartas()[i].peekLast().getNumero() == jugador.getMano().get(j).getNumero()-1 && mesa.getCartas()[i].peekLast().getPalo()== jugador.getMano().get(j).getPalo()){
                        puede = true;
                        break;
                }
                    
                }
                
            }
        }
        
        return puede;
    }

}

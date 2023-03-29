/*
 * Representa una carta, formada por un número y su palo correspondiente
 */


package es.uvigo.esei.aed1.core;

public class Carta {
    private int numero;
    private int palo;

    private final String[] palos = new String[]{"Oros","Espadas","Copas","Bastos"};

    public Carta(int numero, int palo) {
        this.numero = numero;
        this.palo = palo;
    }

    public void setNumero(int numero) throws Exception {
        this.numero = numero;
		if(numero < 1 || numero > 12) {
			throw new Exception("Número de carta no válido. No se encuentra en 1 y 12.");
		}
    }

    public void setPalo(int palo) {
        this.palo = palo;
    }

    public int getNumero() {
        return numero;
    }

    public int getPalo() {
        return palo;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        
        toret.append(numero);
        toret.append(" de ");
        toret.append(palos[palo]);
        
        return toret.toString();
    }
    
    
    
    
}

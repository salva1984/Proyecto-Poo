package ec.edu.espol;

import java.util.ArrayList;
import java.util.Collections;

import ec.edu.espol.cartas.Carta;
import ec.edu.espol.cartas.CartaComodin;
import ec.edu.espol.cartas.CartaNormal;
import ec.edu.espol.cartas.Color;

public class Juego {

    //revisarCarta recibe la ultima carta jugada y la que quieres jugar, y revisa si es posible jugar la carta que le pasaste
    public static boolean revisarCarta(Carta ultimaCarta, Carta cartaJugar){

        // Tenemos que revisar si la carta que queremos jugar es normal o comodin.
        if(cartaJugar instanceof CartaNormal){
            
            // Hacemos downcasting para acceder a getNumero de CartaNormal
            CartaNormal cn = (CartaNormal) cartaJugar;

            //Tenemos que revisar si la ultima carta es Normal o Comodin
            if(ultimaCarta instanceof CartaNormal){

                // Hacemos downcasting por las mismas razones
                CartaNormal uc = (CartaNormal) ultimaCarta;

                //Comparamos si ambas comparten numero o color
                if(cn.getNumero() == uc.getNumero() || cn.getColor() == uc.getColor())
                    return true;
            }
            if(ultimaCarta instanceof CartaComodin){

                // NO hacemos dowcasting, las cartas comodin no tienen nada que nos importe acceder
                // Solo podemos comparar los colores
                if(cn.getColor() == ultimaCarta.getColor())
                    return true;
            }
        }

        if(cartaJugar instanceof CartaComodin){
            if(cartaJugar.getColor() == Color.N)
                return true; // Siempre es posible jugar una carta negra
            // la ultima carta puede ser comodin o normal, pero al jugar una carta comodin encima de esta solo nos importa el color porque los comodines no tiene numeros
            if(ultimaCarta.getColor() == cartaJugar.getColor())
                return true;
                
        }
        return false;
        
    }

    public static Carta robarCarta(ArrayList<Carta> mazo){
        Collections.shuffle(mazo);
        Carta c = mazo.get(0);
        mazo.remove(0);
        return c;
    }

}



package ec.edu.espol;

import java.util.ArrayList;
import java.util.Collections;

import ec.edu.espol.cartas.Carta;
import ec.edu.espol.cartas.CartaComodin;
import ec.edu.espol.cartas.CartaNormal;
import ec.edu.espol.cartas.Color;
import ec.edu.espol.cartas.TipoComodin;

public class Mazo {
    private ArrayList<Carta> cartas;

    public Mazo(){
        cartas = new ArrayList<>();
        for(int i = 1; i < 11; i++){
            cartas.add(new CartaNormal(Color.A, i));
            cartas.add(new CartaNormal(Color.R, i));
            cartas.add(new CartaNormal(Color.V, i));
            cartas.add(new CartaNormal(Color.Z, i));
        }
        for (Color c : Color.values()) {
            if(c != Color.N){
                cartas.add(new CartaComodin(c,TipoComodin.REVERSA));
                cartas.add(new CartaComodin(c,TipoComodin.REVERSA));
                cartas.add(new CartaComodin(c,TipoComodin.MAS2));
                cartas.add(new CartaComodin(c,TipoComodin.MAS2));
                cartas.add(new CartaComodin(c,TipoComodin.MAS4));
                cartas.add(new CartaComodin(c,TipoComodin.MAS4));
                cartas.add(new CartaComodin(c,TipoComodin.BLOQUEO));
                cartas.add(new CartaComodin(c,TipoComodin.BLOQUEO));

            }
            else{
            cartas.add(new CartaComodin(c,TipoComodin.MAS2));
            cartas.add(new CartaComodin(c,TipoComodin.MAS2));
            cartas.add(new CartaComodin(c,TipoComodin.MAS4));
            cartas.add(new CartaComodin(c,TipoComodin.MAS4));
            cartas.add(new CartaComodin(c,TipoComodin.CAMBIOCOLOR));
            cartas.add(new CartaComodin(c,TipoComodin.CAMBIOCOLOR));
            }
        }
        Collections.shuffle(cartas);
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }
    
}

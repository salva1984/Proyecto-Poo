package ec.edu.espol.jugadores;

import java.util.ArrayList;
import java.util.Random;

import ec.edu.espol.cartas.Carta;

public class Jugador {
    private ArrayList<Carta> cartas;
    // Este constructor toma el mazo y le quita 7 cartas y se las añade al del jugador
    public Jugador(ArrayList<Carta> mazo){ 
        cartas = new ArrayList<>();
        while(cartas.size()<3){
            Random random = new Random();
            int randint = random.nextInt(mazo.size());
            cartas.add(mazo.get(randint));
            mazo.remove(randint);
        }
    }
    public ArrayList<Carta> getCartas() {
        return cartas;
    }
    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Carta carta : cartas) {
            sb.append(carta.toString()+" - ");
        }
        return sb.toString();   
    }
}

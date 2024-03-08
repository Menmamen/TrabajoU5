package ejercicio;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    protected ArrayList<Carta> mazo;

    public Mazo(){

        mazo = new ArrayList<>();
        for (Carta.Palo palo: Carta.Palo.values()){
            for (int i = 1; i <= 13; i++) {
                Carta carta = new Carta(i, palo);
                mazo.add(carta);
            }
        }
    }
    //Barajar
    public void barajar(){
        Collections.shuffle(mazo);
    }


    //toString
    @Override
    public String toString() {
        //Voy a utilizar la clase StringBuilder para que me cree un string ordenado del array que tenemos
        StringBuilder mazoCompleto = new StringBuilder("[");
        for (Carta carta : mazo) {
            //usamos  el toString de cada carta
            mazoCompleto.append(carta.toString()).append(", ");
        }
        mazoCompleto.append("]");
        return mazoCompleto.toString();
    }

    //Solicitar carta
    public Carta solicitarCarta(){
        Carta primera = mazo.getFirst();
        mazo.removeFirst();
        System.out.println("La carta es: " + primera.toString());
        return primera;
    }
}

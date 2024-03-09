package ejercicio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;

@XmlRootElement(name = "Mano")
public class Mano extends Mazo{

    private ArrayList<Carta> mano;

    public Mano(){
        this.mano = new ArrayList<Carta>();
    }

    //Valor de la mano
    public int valorMano(){
        int valor = 0;
        for (Carta carta : mano) {
            valor += carta.getValor();
        }
        return valor;
    }
    //Acabar el juego
    public boolean finDeJuego(){
        if(valorMano() > 21){
            return true;
        }else {
            return false;
        }
    }
    //toString
    @Override
    public String toString() {
        return "Puntuacion: " + valorMano() +
                "\tSu mano es: " + mano.toString();
    }

    //Pedir una carta
    public void pedirCarta(Mazo m){
        mano.add(m.solicitarCarta());
    }

    @XmlElement(name = "manoCompleta")
    public String getToString(){
        return toString();
    }


    public ArrayList<Carta> getMano() {
        return mano;
    }
    @XmlElement(name = "carta")
    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }
}

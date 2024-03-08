package ejercicio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "carta")
public class Carta {

    public enum Palo{
        trebol, diamantes, corazones, picas
    };


    private int num;

    private Palo palo;

    //Constructor
    public Carta(int num, Palo palo){
        if (num < 1 || num > 13){
            throw new IllegalArgumentException("El número de la carta debe estar entre 1 y 13.");
        }
        this.num = num;
        this.palo = palo;
    }
    public Carta() {

    }
    //getters

    public int getNum() {
        return num;
    }

    public Palo getPalo() {
        return palo;
    }
    @XmlElement(name = "numero")
    public void setNum(int num) {
        this.num = num;
    }
    @XmlElement
    public void setPalo(Palo palo) {
        this.palo = palo;
    }
    //Valor de la carta

    public int getValor(){
        int valor = 0;
        switch (num){
            case 1:
                valor = 11;
                break;
            case 11, 12, 13:
                valor = 10;
                break;
            default:
                valor = num;
                break;
        }
        return valor;
    }
    //Mostrar el numero

    public String mostrarNumero(){
        String repr;
        switch (num){
            case 1:
                repr = "As";
                break;
            case 11:
                repr = "J";
                break;
            case 12:
                repr = "Q";
                break;
            case 13:
                repr = "K";
                break;
            default:
                repr = num + "";
                //se puede hacer así o con String.valueOf(num)
                break;
        }
        return repr;
    }
    //toSting
    @Override
    public String toString() {
        return mostrarNumero() + " - " + palo;
    }

    @XmlElement(name = "nombre")
    public String getToString(){
        return toString();
    }

}

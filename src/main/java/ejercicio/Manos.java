package ejercicio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "Manos")
public class Manos {

     private ArrayList<Mano> manos;
     public Manos(){
         manos = new ArrayList<Mano>();
     }
     public void agregarMano(Mano m){
         manos.add(m);
     }

    @Override
    public String toString() {
        StringBuilder listaManos = new StringBuilder("\nLista de manos jugadas: \n");
        for (Mano m : manos) {
            //usamos  el toString de cada mano
            listaManos.append(m.toString()).append("\n");
        }
        return listaManos.toString();
    }

    @XmlElement(name = "resultados")
    public String getToString() {
        return toString();
    }

    public ArrayList<Mano> getManos() {
        return manos;
    }
    @XmlElement(name = "mano")
    public void setManos(ArrayList<Mano> manos) {
        this.manos = manos;
    }
}

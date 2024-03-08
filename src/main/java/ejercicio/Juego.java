package ejercicio;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Juego {
    public static void main(String[] args) {
        //Creamos el mazo
        Mazo mazo = new Mazo();
        //Barajamos
        mazo.barajar();

        //Creamos la mano del jugador
        Mano mano = new Mano();

        //Añadimos un scanner para recoger las respuestas
        Scanner sc = new Scanner(System.in);
        //Añadimos un entero de control para ver si quiere pedir cartas o plantarse
        int seguir = 1;

        //Solicitar cartas y seguir la jugada
        while (seguir == 1) {
            System.out.println("¿Quieres una carta?\nIntroduce '1' en este caso.");
            seguir = sc.nextInt();
            if (seguir == 1) {
                mano.pedirCarta(mazo);
                System.out.println("Su mano actual es:");
                System.out.println(mano);
            } else {
                System.out.println("Te has plantado con una puntuación de: " + mano.valorMano());
            }
            if (mano.finDeJuego()) {
                System.out.println("Has superado 21, has perdido el juego.");
                seguir = 10;
            }
            if (mano.valorMano() == 21) {
                System.out.println("¡ENHORABUENA! Tu puntuacion es de 21.");
                seguir = 10;
            }
        }
        sc.close();
        Manos manos;
        String ruta = "FinalPartida.xml";


        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Manos.class);
            Marshaller escribir = jaxbContext.createMarshaller();
            Unmarshaller leer = jaxbContext.createUnmarshaller();


            if(new File(ruta).exists()){
                manos = (Manos) leer.unmarshal(new File(ruta));
            } else{
                manos = new Manos();
            }
            manos.agregarMano(mano);
            escribir.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            escribir.marshal(manos, new File(ruta));

        } catch (JAXBException e) {
            e.printStackTrace();

        }

    }
}
/*     try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Mano.class);
            Marshaller escribir = jaxbContext.createMarshaller();
            Unmarshaller leer = jaxbContext.createUnmarshaller();
            Manos manos = new Manos();

            if(new File("FinalPartida.xml").exists() && manos.getManos().size()==1){
                Mano resultados = (Mano) leer.unmarshal(new File("FinalPartida.xml"));
                manos.agregarMano(resultados);
                manos.agregarMano(mano);
                escribir.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                escribir.marshal(manos, new File("FinalPartida.xml"));

            } else if (new File("FinalPartida.xml").exists() && manos.getManos().size()>1) {
                Manos listaManos = (Manos) leer.unmarshal(new File("FinalPartida.xml"));
                listaManos.agregarMano(mano);
                escribir.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                escribir.marshal(listaManos, new File("FinalPartida.xml"));

            } else{
                manos.agregarMano(mano);
                escribir.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                escribir.marshal(mano, new File("FinalPartida.xml"));
            }

        } catch (JAXBException e) {
            e.printStackTrace();

        }

 */


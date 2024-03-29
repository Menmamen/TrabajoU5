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
        try {
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
        }catch (Exception e){
            System.out.println("El juego ha terminado de manera abrupta, el valor introducido no es un entero.");
        }

        sc.close();

        //Inicializamos la clase manos y creamos un String para la ruta del archivo XML
        Manos manos;
        String ruta = "FinalPartida.xml";

        try {
            //Inicializamos el marshall y unmarshall
            JAXBContext jaxbContext = JAXBContext.newInstance(Manos.class);
            Marshaller escribir = jaxbContext.createMarshaller();
            Unmarshaller leer = jaxbContext.createUnmarshaller();

            if(new File(ruta).exists()){
                manos = (Manos) leer.unmarshal(new File(ruta));
                //Si existe, lo leemos en manos
            } else{
                manos = new Manos();
                //Si no existe, creamos una lista de Manos vacía
            }
            manos.agregarMano(mano);//añadimos la última mano jugada
            escribir.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            escribir.marshal(manos, new File(ruta));

        } catch (JAXBException e) {
            e.printStackTrace();

        }

    }
}

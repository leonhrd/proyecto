package sample.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.views.Loteria;

public class EventoLoteria implements EventHandler {

    int opc;

    public EventoLoteria(int opc) {
        this.opc = opc;
    }

    @Override
    public void handle(Event event) {

        switch (opc) {
            case 1:
                //Este método es para plantilla ANTERIOR
                System.out.println("Mi primer evento Fovifest desde otra clase :)");

                break;
            case 2:
                System.out.println("chsm");


                break;



        }




    }
}

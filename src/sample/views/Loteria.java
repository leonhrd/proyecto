package sample.views;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.events.EventoLoteria;

import java.util.Timer;
import java.util.TimerTask;


public class Loteria extends Stage implements EventHandler {

    private VBox vBox;
    private HBox hBox1, hBox2;
    private Button btnAtras, btnSiguiente, btnJugar;
    private Label lblTiempo;
    private GridPane gdpPlantilla;
    public Image imgCarta;
    public ImageView imvCarta;
    private Scene escena;
    private String[] arImagenes = {"1 el gallo.jpg", "2 el diablito.jpg", "3 la dama.jpg", "4 el catrin.jpg", "5 el paraguas.jpg", "6 la sirena.jpg",
            "7 la escalera.jpg", "8 la botella.jpg", "9 barril.jpg", "10 arbol.jpg", "11 melon.jpg", "12 el valiente.jpg", "13 el gorrito.jpg", "14 la muerte.jpg",
            "15 la pera.jpg", "16 la bandera.jpg", "17 el bandolon.jpg", "18 el violoncello.jpg", "19 la garza.jpg", "20 el pajaro.jpg", "21 la mano.jpg",
            "22 la bota.jpg", "23 la luna.jpg", "24 el cotorro.jpg", "25 el borracho.jpg", "26 el negrito.jpg"};
    private Button[][] arBtnPlantilla = new Button[4][4];


    public Loteria() {
        CrearUI();
        this.setTitle("Loteria :)");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI() {


        btnAtras = new Button("Anterior");
        btnAtras.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventoLoteria(1));
        btnAtras.setPrefWidth(100);


        btnSiguiente = new Button("Siguiente");
        btnSiguiente.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventoLoteria(2));
        btnSiguiente.setPrefWidth(100);


        lblTiempo = new Label("00:00");

        hBox1 = new HBox();
        hBox1.setSpacing(10);
        hBox1.getChildren().addAll(btnAtras, btnSiguiente, lblTiempo);

        gdpPlantilla = new GridPane();
        CrearPlantillas();

        //Aquí puedo implementar un arreglo de imagenes, hacer lo mismo que hice en crear plantillas y
        //hacerlo con un hilo


        hBox2 = new HBox();
        hBox2.setSpacing(5);

        imgCarta = new Image("sample/imagesmex/1 el gallo.jpg");
        imvCarta = new ImageView(imgCarta);
        imvCarta.setFitHeight(350);
        imvCarta.setFitWidth(200);

        hBox2.getChildren().addAll(gdpPlantilla, imvCarta);

        btnJugar = new Button("Jugar");
        btnJugar.setPrefWidth(290);
        btnJugar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        vBox = new VBox();
        vBox.setPadding(new Insets(5));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(hBox1, hBox2, btnJugar);

        escena = new Scene(vBox, 550, 500);
    }

    public void CrearPlantillas() {


        int NumImg = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                Image imgCartP = new Image("sample/imagesmex/" + arImagenes[NumImg]);

                ImageView imv = new ImageView(imgCartP);
                imv.setFitHeight(80);
                imv.setFitWidth(60);

                arBtnPlantilla[j][i] = new Button();
                arBtnPlantilla[j][i].setGraphic(imv);
                gdpPlantilla.add(arBtnPlantilla[j][i], i, j);
                NumImg++;
            }
        }
    }

    @Override
    public void handle(Event event) {
        System.out.println("Mi primer evento Fovifest :)");


        Timer timer = new Timer();
        TimerTask task = new TimerTask() {



            @Override
            public void run() {
                int NumImg = 0;

                for (int i = 0; i < 24; i++) {
                    imgCarta = new Image("sample/imagesmex/" + arImagenes[NumImg]);
                    imvCarta = new ImageView(imgCarta);
                    System.out.println(NumImg + "algo está pasado");

                    NumImg++;

                }

            }
        };

    }


}
//Este método es para jugar



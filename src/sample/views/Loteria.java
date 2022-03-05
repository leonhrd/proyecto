
package sample.views;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.util.*;


public class Loteria extends Stage implements EventHandler {

    Timer timer = new Timer();
    Timer timer2 =  new Timer();

    private VBox vBox;
    private HBox hBox1, hBox2, hBox3;
    private Button btnAtras, btnSiguiente, btnJugar;
    private Label lblTiempo, lblPosPlantilla;
    private GridPane gdpPlantilla;
    public GridPane gdpPlantilla3;
    public Image imgCarta;
    public ImageView imvCarta;
    private Scene escena;
    public int NumImg;
    public int btn = 0;
    int i = 0, contAciertos = 0;
    int sec = 0, min = 0;
    String carta;
    Image imgCartP;
    int cont = 0;
    public String[] arImagenes = {"1 el gallo.jpg", "2 el diablito.jpg", "3 la dama.jpg", "4 el catrin.jpg", "5 el paraguas.jpg", "6 la sirena.jpg",
            "7 la escalera.jpg", "8 la botella.jpg", "9 barril.jpg", "10 arbol.jpg", "11 melon.jpg", "12 el valiente.jpg", "13 el gorrito.jpg", "14 la muerte.jpg",
            "15 la pera.jpg", "16 la bandera.jpg", "17 el bandolon.jpg", "18 el violoncello.jpg", "19 la garza.jpg", "20 el pajaro.jpg", "21 la mano.jpg",
            "22 la bota.jpg", "23 la luna.jpg", "24 el cotorro.jpg", "25 el borracho.jpg", "26 el negrito.jpg"};
    int[] plantilla = {};
    String[] auxImg = new String[16];
    public Button[][] arBtnPlantilla = new Button[4][4];

    public Loteria() {
        CrearUI();
        this.setTitle("Loteria :)");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI() {
        btnAtras = new Button("Anterior");
        btnAtras.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn--;
                if (btn <= 0)
                    btn = 4;
                disernirPlantilla(btn);
                lblPosPlantilla.setText("Plantilla: "+btn);
            }
        });
        btnAtras.setPrefWidth(100);


        btnSiguiente = new Button("Siguiente");
        btnSiguiente.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn++;
                if (btn > 4)
                    btn = 0;
                disernirPlantilla(btn);
                lblPosPlantilla.setText("Plantilla: "+btn);
            }
        });
        btnSiguiente.setPrefWidth(100);

        lblTiempo = new Label("00:00");
        lblPosPlantilla =  new Label("Plantilla: "+btn);

        hBox1 = new HBox();
        hBox1.setSpacing(10);
        hBox1.getChildren().addAll(btnAtras, btnSiguiente, lblTiempo, lblPosPlantilla);
        gdpPlantilla = new GridPane();

        CrearPlantillas();

        hBox2 = new HBox();
        hBox2.setSpacing(5);
        hBox3 = new HBox();
        hBox3.setSpacing(10);


        imgCarta = new Image("sample/imagesmex/carta volteada.jpg");
        imvCarta = new ImageView(imgCarta);
        imvCarta.setFitHeight(350);
        imvCarta.setFitWidth(200);
        hBox3.getChildren().add(imvCarta);

        hBox2.getChildren().addAll(gdpPlantilla);

        btnJugar = new Button("Jugar");
        btnJugar.setPrefWidth(290);
        btnJugar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        vBox = new VBox();
        vBox.setPadding(new Insets(5));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(hBox1, hBox2, btnJugar, hBox3);

        escena = new Scene(vBox, 1280, 720);
    }


    public void disernirPlantilla(int btn) {

        switch (btn) {
            case 1:

                plantilla = new int[]{14, 12, 9, 16, 22, 5, 4, 15, 24, 23, 21, 17, 6, 7, 8, 19};
                cambiarPlatilla(plantilla);
                break;

            case 2:
                plantilla = new int[]{15, 9, 12, 22, 16, 1, 4, 3, 5, 13, 21, 17, 6, 7, 8, 19};
                cambiarPlatilla(plantilla);
                break;

            case 3:
                plantilla = new int[]{13, 1, 4, 24, 23, 22, 2, 3, 5, 14, 21, 17, 6, 9, 8, 20};
                cambiarPlatilla(plantilla);
                break;

            case 4:
                plantilla = new int[]{23, 3, 4, 5, 7, 9, 3, 1, 19, 6, 25, 22, 24, 8, 10, 11};
                cambiarPlatilla(plantilla);
                break;
        }

    }

    public void CrearPlantillas() {
        int p = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                imgCartP = new Image("sample/imagesmex/" + arImagenes[NumImg]);
                ImageView imv = new ImageView(imgCartP);
                imv.setFitHeight(80);
                imv.setFitWidth(60);
                arBtnPlantilla[j][i] = new Button();
                arBtnPlantilla[j][i].setGraphic(imv);
                arBtnPlantilla[j][i].setId(arImagenes[p]);
                NumImg++;
                p++;

                gdpPlantilla.add(arBtnPlantilla[j][i], i, j);
            }
        }

        NumImg = 0;
    }


    public void getCartaClick() {
        for (int j = 0; j < arBtnPlantilla.length; j++) {
            for (int k = 0; k < arBtnPlantilla[j].length; k++) {
                int finalK = k;
                int finalJ = j;
                arBtnPlantilla[j][k].setOnMouseClicked(event -> {
                   if (arBtnPlantilla[finalJ][finalK].getId().equals(carta)) {
                       arBtnPlantilla[finalJ][finalK].setDisable(true);
                   }
                });
            }
        }
    }

    void tempo(){
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    sec++;
                    lblTiempo.setText("00:"+sec);
                    if(sec==60){
                        timer2.cancel();
                    }
                });

            }
        };
        timer2.schedule(task2, 0,1000);
    }

    @Override
    public void handle(Event event) {
        tempo();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    getCartaClick();
                    if (i != 16) {
                        if (imvCarta.getImage() != null) {
                            hBox3.getChildren().clear();
                            //Mandar arreglo aleatorio
                            List<Integer> maso = barajearMaso();
                            if (btn == 0){
                                imgCarta = new Image("sample/imagesmex/" + arImagenes[i]);
                                carta = arImagenes[i];
                            }else{
                                imgCarta = new Image("sample/imagesmex/" + arImagenes[maso.get(i)]);
                                carta = arImagenes[maso.get(i)];
                            }
                            imvCarta = new ImageView(imgCarta);
                            imvCarta.setFitHeight(350);
                            imvCarta.setFitWidth(200);
                            hBox3.getChildren().add(imvCarta);
                            i++;

                        }
                    }else{
                        //Terminado el juego
                        timer.cancel();
                        esGanado();
                        btnJugar.setDisable(true);
                        if (contAciertos == 16){
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle("Juego Termiando");
                            a.setContentText("Felicidades ganaste!");
                            a.show();
                        }else if(contAciertos < 16){
                            Alert b = new Alert(Alert.AlertType.WARNING);
                            b.setTitle("Se termino");
                            b.setContentText("no no ganaste!");
                            b.show();
                        }
                    }
                });

            }

        };
        timer.schedule(task, 1000, 3000);
    }

    public void cambiarPlatilla(int[] plantilla) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String arImg = arImagenes[plantilla[NumImg]];
                auxImg[cont] = arImg;
                NumImg++;
                cont++;
            }
        }
        NumImg = 0;
        cont = 0;
        getPlantilla(plantilla);
    }


    public void getPlantilla(int[] plantilla) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                imgCartP = new Image("sample/imagesmex/" + auxImg[NumImg]);
                ImageView imv = new ImageView(imgCartP);
                imv.setFitHeight(80);
                imv.setFitWidth(60);
                arBtnPlantilla[i][j].setGraphic(imv);
                String arImg = arImagenes[plantilla[NumImg]];
                arBtnPlantilla[i][j].setId(arImg);
                NumImg++;
            }
        }
        NumImg = 0;
    }

    public void esGanado(){
        for (int j = 0; j < arBtnPlantilla.length; j++) {
            for (int k = 0; k < arBtnPlantilla[j].length; k++) {
                if (arBtnPlantilla[j][k].isDisable()){
                    contAciertos++;
                }
            }
        }
    }

    public List<Integer> barajearMaso(){
        List<Integer> cartasRandom = new ArrayList<Integer>(16);
        for (int i = 0; i < 25; i++){
                if(!cartasRandom.contains(i)){
                    cartasRandom.add(i);
                    if(i == 16){
                        break;
                    }
                }
        }
        Collections.shuffle(cartasRandom);
        return cartasRandom;
    }


}


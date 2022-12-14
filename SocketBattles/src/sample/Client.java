package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Client implements Runnable {
    @FXML
    private Pane pane;
    private Rectangle rect;//DEL CLIENT
    private Rectangle avversario;//DEL SERVER
    private char direzione;
    private String direzioneAvversario = "";
    private int dimensione = 40;
    private GestisciSocket client;
    private Thread t;
    private BetterArrayList<Double> angoliAvversari;
    private ArrayList<Circle> proiettiliAvversari;
    private ArrayList<Circle> proiettili;
    private ArrayList<Double> angoli;
    private ArrayList<Integer> lunghezze;
    private double angoloAvversario;
    private double angolo;
    private int secondi=0;
    public float shootRate = 0.5f;
    float prossimoSparo = 0.0f;
    private Timer timer;
    private TimerTask timerTask;
    private double mouseX = 0;
    private double mouseY = 0;
    private int i=0;
    private double vita=1.0;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label primaLabel,secondaLabel,terzaLabel,quartaLabel;
    private boolean morto=false,vinto=false,ricomincia=false;
    private boolean ricominciaAvversario=false;
    private double vitaAvversaria=1.0;


    public void initialize() throws Exception {
        client = new GestisciSocket("localhost", 6789);
        client.connetti();
        t = new Thread(this);
        t.start();
        lunghezze=new ArrayList<>();
        angoli=new ArrayList<>();
        proiettili=new ArrayList<>();
        progressBar.setStyle("-fx-accent: #00FF00;");
        rect = new Rectangle(450, 480, dimensione, dimensione);
        avversario = new Rectangle(450, 130, dimensione, dimensione);
        angoliAvversari=new BetterArrayList<>();
        proiettiliAvversari=new ArrayList<>();
        primaLabel.setVisible(false);
        secondaLabel.setVisible(false);
        terzaLabel.setVisible(false);
        pane.getChildren().add(rect);
        pane.getChildren().add(avversario);
        Timeline timeline = new Timeline((new KeyFrame(Duration.seconds(0.10), e -> {
            aggiorna();
        }))); //si aggiorna 33 volte al secondo
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Timeline timeline2 = new Timeline((new KeyFrame(Duration.millis(30), e -> {
            aggiorna2();
        }))); //si aggiorna 33 volte al secondo
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();

        timer =new Timer();
        timerTask =new TimerTask() {
            @Override
            public void run() {
                secondi++;
            }
        };

        start();

    }

    private void start(){
        timer.scheduleAtFixedRate(timerTask,1000,1000);
    }


    public void aggiorna2(){
        if(!morto||!vinto) {
            if (angoloAvversario != 0.0 && !angoliAvversari.contains(angoloAvversario)) {
                angoliAvversari.add(angoloAvversario);
                proiettiliAvversari.add(new Circle(avversario.getX(), avversario.getY(), 10));
                proiettiliAvversari.get(proiettiliAvversari.size() - 1).setFill(Color.RED);

                pane.getChildren().add(proiettiliAvversari.get(proiettiliAvversari.size() - 1));
            }


            try {
                proiettiliAvversari.get(proiettiliAvversari.size() - 1).setCenterX(proiettiliAvversari.get(proiettiliAvversari.size() - 1).getCenterX() + (20.0 * Math.cos(angoloAvversario)));
                proiettiliAvversari.get(proiettiliAvversari.size() - 1).setCenterY(proiettiliAvversari.get(proiettiliAvversari.size() - 1).getCenterY() + (20.0 * Math.sin(angoloAvversario)));

                Circle prova = new Circle(avversario.getX(), avversario.getX(), 30);
                if (prova.getBoundsInParent().intersects(proiettiliAvversari.get(proiettiliAvversari.size() - 2).getBoundsInParent())) {
                    pane.getChildren().remove(proiettiliAvversari.get(proiettiliAvversari.size() - 2));
                }

                if (proiettiliAvversari.get(proiettiliAvversari.size() - 1).getCenterX() > 900 || proiettiliAvversari.get(proiettiliAvversari.size() - 1).getCenterY() > 600) {
                    pane.getChildren().remove(proiettiliAvversari.get(proiettiliAvversari.size() - 1));
                }

                for (int i = 0; i < proiettiliAvversari.size(); i++) {
                    if (proiettiliAvversari.get(i).getCenterX() < 900 || proiettiliAvversari.get(i).getCenterY() < 600) {
                        proiettiliAvversari.get(i).setCenterX(proiettiliAvversari.get(i).getCenterX() + (20.0 * Math.cos(angoliAvversari.get(i))));
                        proiettiliAvversari.get(i).setCenterY(proiettiliAvversari.get(i).getCenterY() + (20.0 * Math.sin(angoliAvversari.get(i))));
                    }
                }

            } catch (IndexOutOfBoundsException ex) {

            }


            try {
                proiettili.get(proiettili.size() - 1).setCenterX(proiettili.get(proiettili.size() - 1).getCenterX() + (20.0 * Math.cos(angolo)));
                proiettili.get(proiettili.size() - 1).setCenterY(proiettili.get(proiettili.size() - 1).getCenterY() + (20.0 * Math.sin(angolo)));
            } catch (IndexOutOfBoundsException ex) {

            }

            try {
                if (proiettiliAvversari.get(proiettiliAvversari.size() - 1).getBoundsInParent().intersects(rect.getBoundsInParent())) {
                    vita -= 0.1;
                    System.out.println(vita);
                    progressBar.setProgress(vita);
                }

                if(vita<0.1){
                    primaLabel.setVisible(true);
                    secondaLabel.setVisible(true);
                    morto=true;
                }



            } catch (IndexOutOfBoundsException ex) {

            }

            //se uno o pi?? dei proiettili sparati non ?? fuori dalla grandezza della finestra allora portali fuori
            for (int i = 0; i < proiettili.size(); i++) {
                if (proiettili.get(i).getCenterX() < 900 || proiettili.get(i).getCenterY() < 600) {
                    proiettili.get(i).setCenterX(proiettili.get(i).getCenterX() + (20.0 * Math.cos(angoli.get(i))));
                    proiettili.get(i).setCenterY(proiettili.get(i).getCenterY() + (20.0 * Math.sin(angoli.get(i))));
                }
            }



        }

    }

    public void aggiorna() {


        if(!morto||!vinto) {

            if(vitaAvversaria<0.1){
                vinto=true;
                //terzaLabel.setVisible(true);

            }

            if (vita < 0.1) {
                primaLabel.setVisible(true);
                secondaLabel.setVisible(true);
                morto = true;
            }

            if (direzione == 'r') {
                rect.setX(rect.getX() + dimensione);
            } else if (direzione == 'l') {
                rect.setX(rect.getX() - dimensione);
            } else if (direzione == 'd') {
                rect.setY(rect.getY() + dimensione);
            } else if (direzione == 'u') {
                rect.setY(rect.getY() - dimensione);
            }

            if (direzioneAvversario.length() > 0) {
                if (direzioneAvversario.charAt(0) == 'r') {
                    avversario.setX(avversario.getX() + dimensione);
                } else if (direzioneAvversario.charAt(0) == 'l') {
                    avversario.setX(avversario.getX() - dimensione);
                } else if (direzioneAvversario.charAt(0) == 'd') {
                    avversario.setY(avversario.getY() + dimensione);
                } else if (direzioneAvversario.charAt(0) == 'u') {
                    avversario.setY(avversario.getY() - dimensione);
                }
            }

            System.out.println(avversario.getY());


            if (rect.getX() > 862) {
                rect.setX(862);
            } else if (rect.getY() < 300) {
                rect.setY(300);
            } else if (rect.getX() < 0) {
                rect.setX(0);
            } else if (rect.getY() > 560) {
                rect.setY(560);
            }

            if (avversario.getX() > 862) {
                avversario.setX(862);
            } else if (avversario.getY() > 262) {
                avversario.setY(262);
            } else if (avversario.getX() < 0) {
                avversario.setX(0);
            } else if (avversario.getY() < 0) {
                avversario.setY(0);
            }



        }
    }


    public void cambia2(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.D) {
            direzione = 'r';
        } else if (event.getCode() == KeyCode.A) {
            direzione = 'l';
        } else if (event.getCode() == KeyCode.S) {
            direzione = 'd';
        } else if (event.getCode() == KeyCode.W) {
            direzione = 'u';
        }else if(event.getCode()==KeyCode.R&&morto||vinto&&ricominciaAvversario){
            ricomincia=true;
            terzaLabel.setVisible(false);
            this.reset();
        }
    }

    public void spara(MouseEvent mouseEvent) {
        if(!morto) {
            if (secondi > prossimoSparo) {
                prossimoSparo = secondi + shootRate;
                proiettili.add(new Circle(rect.getX(), rect.getY(), 10));
                pane.getChildren().add(proiettili.get(proiettili.size() - 1));
                mouseX = mouseEvent.getX();
                mouseY = mouseEvent.getY();
                angolo = Math.atan2((mouseY - proiettili.get(proiettili.size() - 1).getCenterY()), (mouseX - proiettili.get(proiettili.size() - 1).getCenterX()));
                angoli.add(angolo);
                i++;

            }
        }
    }


        //se l'avversario non ha schiacciato r tu puoi continuare senza fare nulla
        private void reset() {
            if(vinto){
                terzaLabel.setVisible(false);
                rect.setX(450);
                rect.setY(30);
                avversario.setX(450);
                avversario.setY(302);
                vinto=false;
            }else if(morto){
                rect.setX(450);
                rect.setY(0);
                avversario.setX(450);
                avversario.setY(302);
                morto=false;
            }
            if (ricominciaAvversario) {
                vinto = false;
                morto = false;
                ricominciaAvversario = false;
                vita = 1.0;
                progressBar.setProgress(vita);
                primaLabel.setVisible(false);
                secondaLabel.setVisible(false);
                terzaLabel.setVisible(false);

            }
            if (ricomincia) {
                vita = 1.0;
                progressBar.setProgress(vita);
                primaLabel.setVisible(false);
                secondaLabel.setVisible(false);
                terzaLabel.setVisible(false);
                ricomincia = false;
                vinto = false;
                morto = false;
            }

        }


    @Override
    public void run() {
        while (true) {
            try {
                client.scrivi(String.valueOf(direzione));
                direzioneAvversario = client.leggi();
                client.scrivi(String.valueOf(angolo));
                angoloAvversario = Double.parseDouble(client.leggi());
                client.scrivi(String.valueOf(vita));
                vitaAvversaria=Double.parseDouble(client.leggi());



                        client.scrivi("RICOMINCIA");
                        if(client.leggi().equals("RICOMINCIA")){
                            if(morto||vinto){
                                ricominciaAvversario=true;
                            }

                        }





            } catch (IOException | NumberFormatException e) {

            }
        }
    }

}


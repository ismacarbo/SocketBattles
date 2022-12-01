package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;

public class Proiettile {
    @FXML
    private Pane pane;
    private ArrayList<Circle> cerchio;
    private ArrayList<Double> angoli;
    private ArrayList<Double> X;
    private ArrayList<Double> Y;
    private double mouseX = 0, mouseY = 0;
    double costante = 360 / (Math.PI * 2);
    private double angolo;
    private Rectangle center;
    private char direzione;
    int i = 0;


    public void initialize() {
        X = new ArrayList<>();
        Y = new ArrayList<>();
        angoli = new ArrayList<>();
        center = new Rectangle(500, 300, 30, 30);
        cerchio = new ArrayList<>();
        pane.getChildren().add(center);
        Timeline timeline = new Timeline((new KeyFrame(Duration.millis(20), e -> aggiorna())));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Timeline timeline2 = new Timeline((new KeyFrame(Duration.seconds(0.05), e -> aggiorna2())));
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();
    }

    public void aggiorna() {
        cerchio.get(cerchio.size() - 1).setCenterX(cerchio.get(cerchio.size() - 1).getCenterX() + (20.0 * Math.cos(angolo)));
        cerchio.get(cerchio.size() - 1).setCenterY(cerchio.get(cerchio.size() - 1).getCenterY() + (20.0 * Math.sin(angolo)));
        System.out.println(angolo);

        //se uno o più dei proiettili sparati non è fuori dalla grandezza della finestra allora portali fuori
        for (int i = 0; i < cerchio.size(); i++) {
            if (cerchio.get(i).getCenterX() < 900 || cerchio.get(i).getCenterY() < 600) {
                cerchio.get(i).setCenterX(cerchio.get(i).getCenterX() + (20.0 * Math.cos(angoli.get(i))));
                cerchio.get(i).setCenterY(cerchio.get(i).getCenterY() + (20.0 * Math.sin(angoli.get(i))));
            }
        }
    }

    public void aggiorna2() {
        if (direzione == 'r') {
            center.setX(center.getX() + 30);
        } else if (direzione == 'l') {
            center.setX(center.getX() - 30);
        } else if (direzione == 'd') {
            center.setY(center.getY() + 30);
        } else if (direzione == 'u') {
            center.setY(center.getY() - 30);
        }
    }

    public void spara(MouseEvent mouseEvent) {
        cerchio.add(new Circle(center.getX(), center.getY(), 10));
        System.out.println(cerchio.toString());
        pane.getChildren().add(cerchio.get(cerchio.size() - 1));
        mouseX = mouseEvent.getX();
        mouseY = mouseEvent.getY();
        angolo = Math.atan2((mouseY - cerchio.get(cerchio.size() - 1).getCenterY()), (mouseX - cerchio.get(cerchio.size() - 1).getCenterX()));
        X.add(mouseX);
        Y.add(mouseY);
        angoli.add(angolo);
        i++;
    }

    public void cambia(KeyEvent event) throws IOException {

        if (event.getCode() == KeyCode.D) {
            direzione = 'r';
        } else if (event.getCode() == KeyCode.A) {
            direzione = 'l';
        } else if (event.getCode() == KeyCode.S) {
            direzione = 'd';
        } else if (event.getCode() == KeyCode.W) {
            direzione = 'u';
        }
    }
}
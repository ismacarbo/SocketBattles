package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;


public class Main3 extends Application {

    //SERVER
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("proiettile.fxml")));
        primaryStage.setTitle("snake");
        primaryStage.setScene(new Scene(root, 900, 600));//X,Y
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}

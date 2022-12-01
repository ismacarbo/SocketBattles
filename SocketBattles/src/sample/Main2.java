package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;


public class Main2 extends Application {

    //SERVER
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample2.fxml")));
        primaryStage.setTitle("Socket Battles");
        primaryStage.setScene(new Scene(root, 900, 600));//X,Y
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


public class Main extends Application {

    //CLIENT
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("Socket Battles");
        primaryStage.setScene(new Scene(root, 900, 600));//X,Y

        primaryStage.show();
        System.out.println("ok");
        System.out.println("ok");
    }




    public static void main(String[] args) {
        launch(args);
    }
}

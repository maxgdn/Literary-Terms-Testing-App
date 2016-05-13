package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    static final int PREF_HEIGHT = 1024;
    static final int PREF_WIDTH = 720;
    public Parent root;
    private static Main instance;
    private static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("englishclient1.fxml"));
        primaryStage.setTitle("English Literary Terms");
        Scene scene = new Scene(root, PREF_HEIGHT, PREF_WIDTH);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/sample/css/MainCS.css").toExternalForm());
        Main.instance = this;
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static Main getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

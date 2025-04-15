package com.example.flashcard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static Stage primaryStage;
    static FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
    public static Scene MainMenu;

    static {
        try {
            MainMenu = new Scene(fxmlLoader1.load());
            MainMenu.getStylesheets().add(HelloApplication.class.getResource("/com/example/flashcard/style.css").toExternalForm());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("ForMyBambik");
        primaryStage.setScene(MainMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
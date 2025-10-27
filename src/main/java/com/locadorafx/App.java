package com.locadorafx;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static com.locadorafx.controllers.SceneController.AlertMensage.mensagemTelaNaoExistente;

public class App extends Application {
    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login-View"), 1000, 620);
        stage.setTitle("Locadora");

            stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/com/locadorafx/images/icon.png"))));

        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    public static void setRoot(String fxml){
        try {
            scene.setRoot(loadFXML(fxml));
        } catch (IOException | IllegalStateException e) {
            mensagemTelaNaoExistente(e.getMessage());
        }
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/locadorafx/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }
}
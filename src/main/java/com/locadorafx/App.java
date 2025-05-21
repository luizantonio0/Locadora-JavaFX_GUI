package com.locadorafx;

import java.io.IOException;
import java.util.Objects;

import static com.locadorafx.Controllers.SceneController.AlertMensage.mensagemTelaNaoExistente;

import com.locadorafx.Entities.Locacao.Locacao;
import com.locadorafx.Entities.Veiculos.FactoryVeiculos;
import com.locadorafx.Models.LocacaoDAO;
import com.locadorafx.Models.VeiculoDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
            e.printStackTrace();
            System.out.println();
            //mensagemTelaNaoExistente(e.getMessage());
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
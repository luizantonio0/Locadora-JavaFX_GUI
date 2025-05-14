package com.locadorafx;

import java.io.IOException;
import java.time.Year;
import java.util.List;

import com.locadorafx.Entities.Locadora.Locadora;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloAutomovel;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloMotocicleta;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloVan;
import com.locadorafx.Entities.Veiculos.Veiculo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.locadorafx.Entities.Veiculos.FactoryVeiculos.factory;

public class App extends Application {
    private static Scene scene;
    private static final List<Veiculo> veiculos = Locadora.getEstoque();
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML(/*"login-View"*/  /* */"cadastrarVeiculo-View"), 1000, 620);
        stage.setTitle("Locadora");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("@/logo.png")));
    }
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/locadorafx/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {

            Veiculo gol = factory("AAA0A00", 50000, Year.of(2010), Estado.DISPONIVEL, 1, ModeloAutomovel.GOL, null, null);
            Veiculo virtus = factory("BBB0B00", 100000, Year.of(2010), Estado.DISPONIVEL, 2, ModeloAutomovel.VIRTUS, null, null);
            Veiculo jetta = factory("CCC0C00", 200000, Year.of(2024), Estado.DISPONIVEL, 3, ModeloAutomovel.JETTA, null, null);

            Veiculo van1 = factory("AAA0A00", 50000, Year.of(2010), Estado.DISPONIVEL, 4, null,  ModeloVan.DUCATO, null);
            Veiculo van2 = factory("BBB0B00", 100000, Year.of(2010), Estado.DISPONIVEL, 5, null, ModeloVan.KOMBI, null);
            Veiculo van3 = factory("CCC0C00", 200000, Year.of(2024), Estado.DISPONIVEL, 6, null, ModeloVan.SPRINTER, null);

            Veiculo moto1 = factory("AAA0A00", 5000, Year.of(2010), Estado.DISPONIVEL, 7, null, null, ModeloMotocicleta.CG150);
            Veiculo moto2 = factory("BBB0B00", 10000, Year.of(2010), Estado.DISPONIVEL, 8, null, null, ModeloMotocicleta.CB300F);
            Veiculo moto3 = factory("CCC0C00", 20000, Year.of(2024), Estado.DISPONIVEL, 9, null, null, ModeloMotocicleta.CBR1000);

            veiculos.add(gol); veiculos.add(virtus); veiculos.add(jetta);
            veiculos.add(van1); veiculos.add(van2); veiculos.add(van3);
            veiculos.add(moto1); veiculos.add(moto2); veiculos.add(moto3);

        launch();
    }
}
package com.locadorafx;

import com.locadorafx.Entities.Locadora.Locadora;
import com.locadorafx.Entities.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloAutomovel;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloMotocicleta;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloVan;
import com.locadorafx.Entities.Veiculos.Automovel;
import com.locadorafx.Entities.Veiculos.Motocicleta;
import com.locadorafx.Entities.Veiculos.Van;
import com.locadorafx.Entities.Veiculos.Veiculo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Year;
import java.util.List;

public class App extends Application {
    private static Scene scene;
    private static final List<Veiculo> veiculos = Locadora.getEstoque();
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login-View"), 1000, 620);
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

            Veiculo gol = new Automovel("AAA0A00", 50000, Year.of(2010), Marca.VW, Estado.DISPONIVEL, Categoria.POPULAR, 1, ModeloAutomovel.GOL);
            Veiculo virtus = new Automovel("BBB0B00", 100000, Year.of(2010), Marca.VW, Estado.DISPONIVEL, Categoria.INTEMERIARIO, 2, ModeloAutomovel.VIRTUS);
            Veiculo jetta = new Automovel("CCC0C00", 200000, Year.of(2024), Marca.VW, Estado.DISPONIVEL, Categoria.LUXO, 3, ModeloAutomovel.JETTA);

            Veiculo van1 = new Van("AAA0A00", 50000, Year.of(2010), Marca.Fiat, Estado.DISPONIVEL, Categoria.POPULAR,  ModeloVan.DUCATO);
            Veiculo van2 = new Van("BBB0B00", 100000, Year.of(2010), Marca.GM, Estado.DISPONIVEL, Categoria.INTEMERIARIO, ModeloVan.MASTER);
            Veiculo van3 = new Van("CCC0C00", 200000, Year.of(2024), Marca.Mercedes, Estado.DISPONIVEL, Categoria.LUXO,  ModeloVan.SPRINTER);

            Veiculo moto1 = new Motocicleta("AAA0A00", 5000, Year.of(2010), Marca.Honda, Estado.DISPONIVEL, Categoria.POPULAR,  ModeloMotocicleta.CG150);
            Veiculo moto2 = new Motocicleta("BBB0B00", 10000, Year.of(2010), Marca.Honda, Estado.DISPONIVEL, Categoria.INTEMERIARIO, ModeloMotocicleta.CB300F);
            Veiculo moto3 = new Motocicleta("CCC0C00", 20000, Year.of(2024), Marca.Honda, Estado.DISPONIVEL, Categoria.LUXO,  ModeloMotocicleta.CBR1000);

            veiculos.add(gol); veiculos.add(virtus); veiculos.add(jetta);
            veiculos.add(van1); veiculos.add(van2); veiculos.add(van3);
            veiculos.add(moto1); veiculos.add(moto2); veiculos.add(moto3);

        launch();
    }
}
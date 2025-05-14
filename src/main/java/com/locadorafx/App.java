package com.locadorafx;

import java.io.IOException;
import java.time.Year;

import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloAutomovel;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloMotocicleta;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloVan;
import static com.locadorafx.Entities.Veiculos.FactoryVeiculos.factory;
import com.locadorafx.Entities.Veiculos.Veiculo;
import com.locadorafx.Models.ClienteDAO;
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
        scene = new Scene(loadFXML(/*"login-View"*/  /* */"cadastrarVeiculo-View"), 1000, 620);
        stage.setTitle("Locadora");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/com/locadorafx/images/icon.png")));

        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/locadorafx/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {

            Cliente luiz = new Cliente("Luiz", "Gonçalves", "01161535632", "luiz@gmail.com", "201683799", "Curitiba");
            Cliente antonio = new Cliente("Antonio", "Santos", "12609432089", "antonio@gmail.com", "367463386", "Montes Claros");
            ClienteDAO.save(luiz); ClienteDAO.save(antonio);

            Veiculo gol = factory("AAA0A00", 50000, Year.of(2010), Estado.DISPONIVEL, ModeloAutomovel.GOL, null, null);
            Veiculo virtus = factory("BBB0B00", 100000, Year.of(2010), Estado.DISPONIVEL, ModeloAutomovel.VIRTUS, null, null);
            Veiculo jetta = factory("CCC0C00", 200000, Year.of(2024), Estado.DISPONIVEL, ModeloAutomovel.JETTA, null, null);

            Veiculo van1 = factory("AAA0A00", 50000, Year.of(2010), Estado.DISPONIVEL, null,  ModeloVan.DUCATO, null);
            Veiculo van2 = factory("BBB0B00", 100000, Year.of(2010), Estado.DISPONIVEL, null, ModeloVan.KOMBI, null);
            Veiculo van3 = factory("CCC0C00", 200000, Year.of(2024), Estado.DISPONIVEL, null, ModeloVan.SPRINTER, null);

            Veiculo moto1 = factory("AAA0A00", 5000, Year.of(2010), Estado.DISPONIVEL, null, null, ModeloMotocicleta.CG150);
            Veiculo moto2 = factory("BBB0B00", 10000, Year.of(2010), Estado.DISPONIVEL, null, null, ModeloMotocicleta.CB300F);
            Veiculo moto3 = factory("CCC0C00", 20000, Year.of(2024), Estado.DISPONIVEL, null, null, ModeloMotocicleta.CBR1000);

            VeiculoDAO.save(gol); VeiculoDAO.save(virtus); VeiculoDAO.save(jetta);
            VeiculoDAO.save(van1); VeiculoDAO.save(van2); VeiculoDAO.save(van3);
            VeiculoDAO.save(moto1); VeiculoDAO.save(moto2); VeiculoDAO.save(moto3);

        launch();
    }
}
package com.locadorafx.Controllers;

import com.locadorafx.App;
import com.locadorafx.Entities.Locadora.Locadora;
import com.locadorafx.Entities.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloAutomovel;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloMotocicleta;
import com.locadorafx.Entities.Veiculos.Automovel;
import com.locadorafx.Entities.Veiculos.Motocicleta;
import com.locadorafx.Entities.Veiculos.Van;
import com.locadorafx.Entities.Veiculos.Veiculo;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.Year;
import java.util.List;

public class cadastrarVeiculoController {

    @FXML
    private ComboBox<Categoria> comboBoxCategoria;

    @FXML
    private ComboBox<Estado> comboBoxEstado;

    @FXML
    private ComboBox<Marca> comboBoxMarca;

    @FXML
    private ComboBox<ModeloAutomovel> comboBoxModelo;

    protected static Veiculo tipoModelo;

    @FXML
    private TextField textFieldAno;

    @FXML
    private TextField textFieldPlaca;

    @FXML
    private TextField textFieldValor;

    public void initialize() {

        /*Mudar o desing da view e remover o comboBox Categoria, ela é definida quando o usuario escolhe o modelo. */


        comboBoxEstado.getItems().addAll(Estado.values());
        comboBoxMarca.getItems().addAll(Marca.values());
        //Ao definir um elemento javaFX como visible(false) ele é inacessivel. Usar para os 3 tipos diferentes de modelo.
        comboBoxMarca.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    List<ModeloAutomovel> modelos = ModeloAutomovel.getModeloAutomovel(comboBoxMarca.getValue());
                    comboBoxModelo.getItems().clear();
                    comboBoxModelo.getItems().addAll(modelos.toArray(new ModeloAutomovel[0]));

                    comboBoxModelo.setDisable(false);

                }
        );
        comboBoxModelo.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {

                    comboBoxCategoria.getItems().clear();
                    comboBoxCategoria.getItems().add(comboBoxModelo.getValue().categoria);

                }
        );
    }

    @FXML
    void cadastrarVeiculo() {
        Veiculo automovel = new Automovel(textFieldPlaca.getText(), Double.parseDouble(textFieldValor.getText()), Year.parse(textFieldAno.getText()),
                comboBoxMarca.getValue(), comboBoxEstado.getValue(), comboBoxCategoria.getValue(), 0, null);

        Locadora.adicionarVeiculo(automovel);
    }

    @FXML
    void voltarTela() throws IOException {
        App.setRoot("login-View");
    }

}

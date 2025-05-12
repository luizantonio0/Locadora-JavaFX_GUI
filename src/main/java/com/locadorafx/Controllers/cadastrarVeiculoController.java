package com.locadorafx.Controllers;

import com.locadorafx.App;
import com.locadorafx.Entities.Locadora.Locadora;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloAutomovel;
import com.locadorafx.Entities.Veiculos.Automovel;
import com.locadorafx.Entities.Veiculos.Veiculo;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.io.IOException;
import java.time.Year;
import java.util.List;

import static com.locadorafx.Controllers.MascaraFormatador.MascaraFormatador.*;
import static com.locadorafx.Controllers.SceneController.AlertMensage.*;

public class cadastrarVeiculoController {

    @FXML
    private ComboBox<Estado> comboBoxEstado;

    @FXML
    private ComboBox<Marca> comboBoxMarca;

    @FXML
    private ComboBox<ModeloAutomovel> comboBoxModelo;

    @FXML
    private TextField textFieldAno;

    @FXML
    private TextField textFieldPlaca;

    @FXML
    private TextField textFieldValor;

    protected static Veiculo tipoModelo;

    public void initialize() {

        /*Mudar o desing da view e remover o comboBox Categoria, ela é definida quando o usuario escolhe o modelo. */

        comboBoxEstado.getItems().addAll(Estado.values());
        comboBoxMarca.getItems().addAll(Marca.values());

        //Ao definir um elemento javaFX como visible(false) ele é inacessivel. Usar para os 3 tipos diferentes de modelo.

        comboBoxMarca.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    List<ModeloAutomovel> modelos = ModeloAutomovel.getModeloAutomovel(newValue);

                    comboBoxModelo.getItems().clear();
                    comboBoxModelo.getItems().addAll(modelos.toArray(new ModeloAutomovel[0]));

                    comboBoxModelo.setDisable(false);
                });
        textFieldAno.setTextFormatter(new TextFormatter<>(rolagemTextoAno()));
        textFieldValor.setTextFormatter(new TextFormatter<>(rolagemTextoValor()));
        textFieldPlaca.setTextFormatter(new TextFormatter<>(rolagemTextoPlaca()));
    }

    @FXML
    void cadastrarVeiculo() {
        StringBuilder valorString = new StringBuilder(textFieldValor.getText());

        double valor = getDouble(valorString);

        try {
            Veiculo veiculo = new Automovel(textFieldPlaca.getText(), valor, Year.parse(textFieldAno.getText()), comboBoxEstado.getValue(), 0, comboBoxModelo.getValue());
            Locadora.adicionarVeiculo(veiculo);
            mensagemSucessoCarro();
        } catch (IllegalArgumentException e){
            mensagemErro(e.getMessage());
        }
    }

    @FXML
    void voltarTela() throws IOException {
        App.setRoot("login-View");
    }
}
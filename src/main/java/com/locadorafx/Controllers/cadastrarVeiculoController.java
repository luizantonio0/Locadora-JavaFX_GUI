package com.locadorafx.Controllers;

import com.locadorafx.App;
import com.locadorafx.Entities.Locadora.Locadora;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloAutomovel;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloMotocicleta;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloVan;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.io.IOException;
import java.time.Year;

import static com.locadorafx.Controllers.MascaraFormatador.MascaraFormatador.*;
import static com.locadorafx.Controllers.SceneController.AlertMensage.*;
import static com.locadorafx.Controllers.SceneController.ComboBoxInitialize.ComboBoxInitializeModelo;
import static com.locadorafx.Entities.Veiculos.FactoryVeiculos.factory;

public class cadastrarVeiculoController {

    @FXML
    private ComboBox<Estado> comboBoxEstado;

    @FXML
    private ComboBox<Marca> comboBoxMarca;

    @FXML
    private ComboBox<ModeloAutomovel> comboBoxModelo;

    @FXML
    private ComboBox<ModeloMotocicleta> comboBoxModeloMotocicleta;

    @FXML
    private ComboBox<ModeloVan> comboBoxModeloVan;

    @FXML
    private TextField textFieldAno;

    @FXML
    private TextField textFieldPlaca;

    @FXML
    private TextField textFieldValor;

    protected static short tipoVeiculo = 0;

    public void initialize() {

        switch (tipoVeiculo) {
            case 0:
                comboBoxModelo.setVisible(true); break;
            case 1:
                comboBoxModeloMotocicleta.setVisible(true); break;
            case 2:
                comboBoxModeloVan.setVisible(true); break;
        }
        comboBoxEstado.getItems().addAll(Estado.values());
        comboBoxMarca.getItems().addAll(Marca.values());

        comboBoxMarca.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {

                    switch (tipoVeiculo) {
                        case 0:
                            ComboBoxInitializeModelo(tipoVeiculo, comboBoxModelo, newValue); break;
                        case 1:
                            ComboBoxInitializeModelo(tipoVeiculo, comboBoxModeloMotocicleta, newValue); break;
                        case 2:
                            ComboBoxInitializeModelo(tipoVeiculo, comboBoxModeloVan, newValue); break;
                    }
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
            var veiculo = factory(textFieldPlaca.getText(), valor, Year.parse(textFieldAno.getText()), comboBoxEstado.getValue(), 0, comboBoxModelo.getValue(),  comboBoxModeloVan.getValue(), comboBoxModeloMotocicleta.getValue());
            Locadora.adicionarVeiculo(veiculo);
            mensagemCadastroVeiculoSucesso();
        } catch (IllegalArgumentException e){
            mensagemCadastroVeiculoErro(e.getMessage());
        }
    }

    @FXML
    void voltarTela() throws IOException {
        App.setRoot("login-View");
    }
}
package com.locadorafx.Controllers;

import java.time.Year;

import com.locadorafx.App;
import static com.locadorafx.Controllers.MascaraFormatador.MascaraFormatador.getDouble;
import static com.locadorafx.Controllers.MascaraFormatador.MascaraFormatador.rolagemTextoAno;
import static com.locadorafx.Controllers.MascaraFormatador.MascaraFormatador.rolagemTextoPlaca;
import static com.locadorafx.Controllers.SceneController.AlertMensage.*;
import static com.locadorafx.Controllers.SceneController.ComboBoxInitialize.ComboBoxInitializeModelo;

import com.locadorafx.Controllers.MascaraFormatador.MascaraFormatador;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloAutomovel;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloMotocicleta;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloVan;
import static com.locadorafx.Entities.Veiculos.FactoryVeiculos.factory;

import com.locadorafx.Models.VeiculoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class CadastrarVeiculoController {

    @FXML
    private ComboBox<String> comboBoxTipo;

    @FXML
    private ComboBox<Estado> comboBoxEstado;

    @FXML
    private ComboBox<Marca> comboBoxMarca;

    @FXML
    private ComboBox<String> comboBoxModelo;

    @FXML
    private TextField textFieldAno;

    @FXML
    private TextField textFieldPlaca;

    @FXML
    private TextField textFieldValor;

    public void initialize() {

        comboBoxTipo.getItems().addAll("Automovel", "Motocicleta", "Van");

        comboBoxEstado.getItems().addAll(Estado.values());
        comboBoxMarca.getItems().addAll(Marca.values());

        comboBoxTipo.getSelectionModel().selectedItemProperty().addListener(
                (_, _, _) -> {
                    comboBoxModelo.getSelectionModel().clearSelection();
                    comboBoxMarca.getSelectionModel().clearSelection();
                    textFieldPlaca.setDisable(false); textFieldAno.setDisable(false); textFieldValor.setDisable(false); comboBoxMarca.setDisable(false); comboBoxEstado.setDisable(false); comboBoxModelo.setDisable(false);
                });

        comboBoxMarca.getSelectionModel().selectedItemProperty().addListener(
                (_, _, newValue) -> {

                    switch (comboBoxTipo.getValue()) {
                        case "Automovel" -> ComboBoxInitializeModelo("Automovel", comboBoxModelo, newValue);
                        case "Motocicleta" -> ComboBoxInitializeModelo("Motocicleta", comboBoxModelo, newValue);
                        case "Van" -> ComboBoxInitializeModelo("Van", comboBoxModelo, newValue);
                    }
                });
        textFieldAno.setTextFormatter(new TextFormatter<>(rolagemTextoAno()));
        textFieldValor.setTextFormatter(new TextFormatter<>(MascaraFormatador.formatarValorMonetario()));
        textFieldPlaca.setTextFormatter(new TextFormatter<>(rolagemTextoPlaca()));
    }

    @FXML
    void cadastrarVeiculo() {

        ModeloMotocicleta modeloMotocicleta = null;
        ModeloVan modeloVan = null;
        ModeloAutomovel modeloAutomovel = null;

         switch (comboBoxTipo.getValue()) {
            case "Automovel" -> modeloAutomovel = ModeloAutomovel.valueOf(comboBoxModelo.getValue());
            case "Motocicleta" -> modeloMotocicleta = ModeloMotocicleta.valueOf(comboBoxModelo.getValue());
            case "Van" -> modeloVan = ModeloVan.valueOf(comboBoxModelo.getValue());
         }

        StringBuilder valorString = new StringBuilder(textFieldValor.getText());

        double valor = getDouble(valorString);

        try {
            var veiculo = factory(textFieldPlaca.getText(), valor, Year.parse(textFieldAno.getText()), comboBoxEstado.getValue(), modeloAutomovel, modeloVan, modeloMotocicleta);
            VeiculoDAO.save(veiculo);
            textFieldPlaca.clear(); textFieldAno.clear(); textFieldValor.clear(); comboBoxMarca.getSelectionModel().clearSelection(); comboBoxEstado.getSelectionModel().clearSelection(); comboBoxModelo.getSelectionModel().clearSelection();
            mensagemSucesso("O veículo foi cadastrado com sucesso!");
        } catch (IllegalArgumentException e){
            mensagemErro("Erro ao cadastrar veiculo: " + e.getMessage());
        }
    }

    @FXML
    void voltarTela(){
        App.setRoot("AdminMenu-View");
    }
}
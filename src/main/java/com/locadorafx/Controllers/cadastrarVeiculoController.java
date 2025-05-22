package com.locadorafx.Controllers;

import java.time.Year;

import com.locadorafx.App;
import static com.locadorafx.Controllers.MascaraFormatador.MascaraFormatador.getDouble;
import static com.locadorafx.Controllers.MascaraFormatador.MascaraFormatador.rolagemTextoAno;
import static com.locadorafx.Controllers.MascaraFormatador.MascaraFormatador.rolagemTextoPlaca;
import static com.locadorafx.Controllers.MascaraFormatador.MascaraFormatador.formatarValorMonetario;
import static com.locadorafx.Controllers.SceneController.AlertMensage.*;
import static com.locadorafx.Controllers.SceneController.ComboBoxInitialize.ComboBoxInitializeModelo;
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

    protected static final short tipoVeiculo = 0;

    public void initialize() {

        switch (tipoVeiculo) {
            case 0 -> comboBoxModelo.setVisible(true);
            case 1 -> comboBoxModeloMotocicleta.setVisible(true);
            case 2 -> comboBoxModeloVan.setVisible(true);
        }
        comboBoxEstado.getItems().addAll(Estado.values());
        comboBoxMarca.getItems().addAll(Marca.values());

        comboBoxMarca.getSelectionModel().selectedItemProperty().addListener(
                (_, _, newValue) -> {

                    switch (tipoVeiculo) {
                        case 0 -> ComboBoxInitializeModelo(tipoVeiculo, comboBoxModelo, newValue);
                        case 1 -> ComboBoxInitializeModelo(tipoVeiculo, comboBoxModeloMotocicleta, newValue);
                        case 2 -> ComboBoxInitializeModelo(tipoVeiculo, comboBoxModeloVan, newValue);
                    }
                });
        textFieldAno.setTextFormatter(new TextFormatter<>(rolagemTextoAno()));
        textFieldValor.setTextFormatter(new TextFormatter<>(formatarValorMonetario()));
        textFieldPlaca.setTextFormatter(new TextFormatter<>(rolagemTextoPlaca()));
    }

    @FXML
    void cadastrarVeiculo() {
        StringBuilder valorString = new StringBuilder(textFieldValor.getText());

        double valor = getDouble(valorString);

        try {
            var veiculo = factory(textFieldPlaca.getText(), valor, Year.parse(textFieldAno.getText()), comboBoxEstado.getValue(),  comboBoxModelo.getValue(),  comboBoxModeloVan.getValue(), comboBoxModeloMotocicleta.getValue());
            VeiculoDAO.save(veiculo);
            
            textFieldPlaca.clear(); textFieldAno.clear(); textFieldValor.clear(); comboBoxMarca.getSelectionModel().clearSelection(); comboBoxEstado.getSelectionModel().clearSelection(); comboBoxModelo.getSelectionModel().clearSelection(); comboBoxModeloVan.getSelectionModel().clearSelection(); comboBoxModeloMotocicleta.getSelectionModel().clearSelection();
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
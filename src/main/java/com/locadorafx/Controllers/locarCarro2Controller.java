package com.locadorafx.Controllers;

import com.locadorafx.App;
import com.locadorafx.Entities.Veiculos.Automovel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.locadorafx.Controllers.locarCarro1Controller.veiculoSelecionado;

public class locarCarro2Controller {

    @FXML
    private static Label TexFieldPrice;

    @FXML
    private Label TextFieldCategoria;

    @FXML
    private Label TextFieldMarca;

    @FXML
    private Label TextFieldModelo;
    @FXML
    private TextField textFieldDias;


    public void initialize() {

        CarregarDadosVeiculo.carregarCategoria(TextFieldModelo, veiculoSelecionado);
        CarregarDadosVeiculo.carregarMarca(TextFieldMarca, veiculoSelecionado);
        CarregarDadosVeiculo.carregarModelo(TextFieldCategoria, veiculoSelecionado);

        //criar listener para mudar o preço conforme o input
        textFieldDias.textProperty().addListener((observable, oldValue, newValue) -> {
            TexFieldPrice.setText("R$ %.2f".formatted(veiculoSelecionado.getValorDiariaLocacao(Integer.parseInt(textFieldDias.getText()))));
        });

    }



    @FXML
    void locar() {


    }

    @FXML
    void voltarTela() throws IOException {
        App.setRoot("locarCarro1-View");
    }

}

package com.locadorafx.Controllers;

import com.locadorafx.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class locarCarro1Controller {

    @FXML
    private Label TexFieldPrice1;

    @FXML
    private Label TexFieldPrice2;

    @FXML
    private Label TexFieldPrice3;

    @FXML
    private Label TextFieldCategoria1;

    @FXML
    private Label TextFieldCategoria2;

    @FXML
    private Label TextFieldCategoria3;

    @FXML
    private Label TextFieldMarca1;

    @FXML
    private Label TextFieldMarca2;

    @FXML
    private Label TextFieldMarca3;

    @FXML
    private Label TextFieldModelo1;

    @FXML
    private Label TextFieldModelo2;

    @FXML
    private Label TextFieldModelo3;

    private static int ordemCarros = 0;

    public void initialize() {
    }

    @FXML
    void VoltarParaLogin() throws IOException {
        App.setRoot("login-View");
    }

    @FXML
    void abrirLocarVeiculo(){

    }

    @FXML
    void abrirMeusDados() throws IOException {
        App.setRoot("");
    }

    @FXML
    void avancar(ActionEvent event) {
        ordemCarros += 3;
    }

    @FXML
    void locarVeiculo(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {
        ordemCarros -= 3;
    }

}


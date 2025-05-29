package com.locadorafx.Controllers;

import com.locadorafx.App;
import javafx.fxml.FXML;

public class AdminMenuViewController {

    @FXML
    void abrirTelaCadastrarClientes() {
        App.setRoot("cadastrarCliente-View");
    }

    @FXML
    void abrirTelaCrudClientes() {
        App.setRoot("CRUD-Cliente-View");
    }

    @FXML
    void abrirTelaDevolverVeiculo() {
        App.setRoot("AdminLocacaoDevolver-View");
    }

    @FXML
    void abrirTelaLocarVeiculos() {
        App.setRoot("AdminLocarCarro-View");
    }

    @FXML
    void abrirTelaLocacoes() {
        App.setRoot("AdminLocacaoDevolver-View");
    }

    @FXML
    void abrirTelaMenu() {
        App.setRoot("AdminMenu-View");
    }

    @FXML
    void abrirTelaVeiculos() {
        App.setRoot("cadastrarVeiculo-View");
    }

    @FXML
    void abrirTelaVenderVeiculos() {
        App.setRoot("AdminVenderVeiculo-View");
    }

    @FXML
    void fecharPrograma() {
        App.setRoot("login-View");
    }
}
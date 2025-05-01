package com.locadorafx.Controllers;

import com.locadorafx.App;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class loginController {

    @FXML
    private TextField textFieldCPF;

    @FXML
    private PasswordField textFieldSenha;

    public void initialize() {
        textFieldCPF.setPromptText("Digite seu CPF");
        textFieldSenha.setPromptText("Digite sua senha");
    }

    @FXML
    void abrirTelaCadastro() throws IOException {
        App.setRoot("cadastrarCliente-View");
    }

    @FXML
    void cancelForm() {
        textFieldCPF.setPromptText("Digite um CPF");

    }

    @FXML
    void sendFormLogin() {
        textFieldCPF.setPromptText("Digite um CPF");
        textFieldSenha.setPromptText("Digite uma senha");

    }

}
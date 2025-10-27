package com.locadorafx.controllers;

import com.locadorafx.App;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.jasypt.util.password.BasicPasswordEncryptor;

import static com.locadorafx.controllers.SceneController.AlertMensage.mensagemErro;

public class LoginController {

    @FXML
    private TextField textFieldCPF;

    @FXML
    private PasswordField textFieldSenha;

    public void initialize() {
        textFieldCPF.setPromptText("Digite seu CPF");
        textFieldSenha.setPromptText("Digite sua senha");
    }

    @FXML
    void cancelForm() {
        textFieldCPF.clear();
        textFieldSenha.clear();
    }

    @FXML
    void sendFormLogin(){

        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if (textFieldCPF.getText().equals("12345678900")){
            if(passwordEncryptor.checkPassword(textFieldSenha.getText(), "6/voAQOdRUIafh1eA0H2bF4O3/exh7r2")){
                App.setRoot("AdminMenu-View");

            } else mensagemErro("CPF ou senha incorretos!");
        } else mensagemErro("CPF ou senha incorretos!");
    }
}
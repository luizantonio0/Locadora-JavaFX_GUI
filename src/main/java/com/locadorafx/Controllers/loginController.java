package com.locadorafx.Controllers;

import com.locadorafx.App;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//import org.jasypt.util.password.BasicPasswordEncryptor;

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
        textFieldCPF.setText("");
        textFieldSenha.setText("");

    }

    @FXML
    void sendFormLogin() throws IOException {
        //Não está compilando pois não consegue identificar a biblioteca jasypt, erro de modulo.
        //BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        //Buscar dados do banco de Dados banco de dados
        //passwordEncryptor.checkPassword(textFieldSenha.getText(), "Senha obtida do banco de dados");
        App.setRoot("locarCarro1-View");

    }

}
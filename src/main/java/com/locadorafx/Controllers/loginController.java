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
        //BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        //String cpfEncriptado = passwordEncryptor.encryptPassword(textFieldCPF.getText());
        //Buscar dados do banco de Dados banco de dados
        //passwordEncryptor.checkPassword(textFieldSenha.getText(), cpfEncriptado);
        App.setRoot("locarCarro1-View");

    }

}
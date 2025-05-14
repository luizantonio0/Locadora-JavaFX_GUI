package com.locadorafx.Controllers;

import com.locadorafx.App;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//import org.jasypt.util.password.BasicPasswordEncryptor;

import java.io.IOException;

import static com.locadorafx.Controllers.SceneController.AlertMensage.mensagemTelaNaoExistente;

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
    void abrirTelaCadastro(){
        try {
            App.setRoot("cadastrarCliente-View");
        } catch (IOException | IllegalStateException e) {
            mensagemTelaNaoExistente(e.getMessage());
        }
    }

    @FXML
    void cancelForm() {
        textFieldCPF.setText("");
        textFieldSenha.setText("");

    }

    @FXML
    void sendFormLogin(){
        //BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        //String cpfEncriptado = passwordEncryptor.encryptPassword(textFieldCPF.getText());
        //Buscar dados do banco de Dados banco de dados
        //passwordEncryptor.checkPassword(textFieldSenha.getText(), cpfEncriptado);
        try {
            App.setRoot("locarCarro1-View");
        } catch (IOException | IllegalStateException e) {
            mensagemTelaNaoExistente(e.getMessage());
        }
    }

}
package com.locadorafx.Controllers;

import com.locadorafx.App;
import com.locadorafx.Entities.Clientes.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.locadorafx.Controllers.SceneController.AlertMensage.mensagemTelaNaoExistente;

public class cadastrarClienteController {

    @FXML
    private TextField textFieldCPF;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldEndereco;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldRG;

    @FXML
    private TextField textFieldSobrenome;

    @FXML
    void cadastrarCliente(ActionEvent event) {
        String cpf = textFieldCPF.getText();
        String email = textFieldEmail.getText();
        String endereco = textFieldEndereco.getText();
        String nome = textFieldNome.getText();
        String rg = textFieldRG.getText();
        String sobrenome = textFieldSobrenome.getText();
        //201683799 RG Valido
        Cliente cliente = new Cliente(nome, sobrenome, cpf, email, rg, endereco);
        System.out.println(cliente);


    }

    @FXML
    void voltarTela(){
        try {
            App.setRoot("login-View");
        } catch (IOException | IllegalStateException e) {
            mensagemTelaNaoExistente(e.getMessage());
        }
    }

}
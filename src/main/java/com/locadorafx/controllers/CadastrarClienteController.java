package com.locadorafx.controllers;

import com.locadorafx.App;
import com.locadorafx.controllers.SceneController.AlertMensage;
import com.locadorafx.models.Clientes.Cliente;
import com.locadorafx.DAO.ClienteDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.sql.SQLException;

import static com.locadorafx.controllers.MascaraFormatador.MascaraFormatador.*;

public class CadastrarClienteController {

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

    public void initialize() {
        textFieldCPF.setTextFormatter(new TextFormatter<>(formatarCpf()));
        textFieldRG.setTextFormatter(new TextFormatter<>(formatarRg()));
    }

    @FXML
    void cadastrarCliente() {
        String cpf = textFieldCPF.getText();
        String email = textFieldEmail.getText();
        String endereco = textFieldEndereco.getText();
        String nome = textFieldNome.getText();
        String rg = textFieldRG.getText();
        String sobrenome = textFieldSobrenome.getText();
        //201683799 RG Valido
        try {
            Cliente cliente = new Cliente(nome, sobrenome, cpf, email, rg, endereco);
            ClienteDAO.getInstance().save(cliente);

            textFieldCPF.clear(); textFieldEmail.clear(); textFieldEndereco.clear(); textFieldNome.clear(); textFieldRG.clear(); textFieldSobrenome.clear();
            AlertMensage.mensagemSucesso("Cliente cadastrado com sucesso!");
        } catch (IllegalArgumentException | SQLException e){
            AlertMensage.mensagemErro("Erro: " + e.getMessage());
        }
    }

    @FXML
    void voltarTela(){
        App.setRoot("AdminMenu-View");
    }
}
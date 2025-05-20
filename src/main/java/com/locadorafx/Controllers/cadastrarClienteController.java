package com.locadorafx.Controllers;

import com.locadorafx.App;
import com.locadorafx.Controllers.SceneController.AlertMensage;
import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Models.ClienteDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
            ClienteDAO.save(cliente);

            textFieldCPF.clear(); textFieldEmail.clear(); textFieldEndereco.clear(); textFieldNome.clear(); textFieldRG.clear(); textFieldSobrenome.clear();
            AlertMensage.mensagemSucesso("Cliente cadastrado com sucesso!");
        } catch (IllegalArgumentException e){
            AlertMensage.mensagemErro("Erro: " + e.getMessage());
        }





    }

    @FXML
    void voltarTela(){
        App.setRoot("AdminMenu-View");
    }

}
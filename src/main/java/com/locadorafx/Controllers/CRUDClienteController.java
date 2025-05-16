package com.locadorafx.Controllers;

import com.locadorafx.App;

import static com.locadorafx.Controllers.CarregarDadosVeiculo.carregarDadosCliente;
import static com.locadorafx.Controllers.CarregarDadosVeiculo.carregarTabelaClientes;
import static com.locadorafx.Controllers.SceneController.AlertMensage.*;

import com.locadorafx.Entities.Clientes.Atributos.CPF;
import com.locadorafx.Entities.Clientes.Atributos.Email;
import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Locadora.Locadora;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class CRUDClienteController {

    @FXML
    private TableColumn<Cliente, CPF> tableColumnCPF;

    @FXML
    private TableColumn<Cliente, Email> tableColumnEmail;

    @FXML
    private TableColumn<Cliente, String> tableColumnEndereco;

    @FXML
    private TableColumn<Cliente, Integer> tableColumnId;

    @FXML
    private TableColumn<Cliente, String> tableColumnNome;

    @FXML
    private TableView<Cliente> tableViewClientes;


    @FXML
    private TextField textFieldCPF;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextArea textFieldEndereco;

    @FXML
    private TextField textFieldId;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldRG;

    @FXML
    private TextField textFieldSorbenome;

    private ObservableList<Cliente> clientes = FXCollections.observableArrayList(Locadora.getClientes());

    private Cliente clienteSelecionado;

    public void initialize() {

        carregarTabelaClientes(tableColumnNome, tableColumnEmail, tableColumnEndereco, tableColumnCPF, tableColumnId);

        //clientes = FXCollections.observableArrayList(Locadora.getClientes());
        tableViewClientes.setItems(clientes);

        tableViewClientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            textFieldEmail.setEditable(false);
            textFieldNome.setEditable(false);
            textFieldSorbenome.setEditable(false);
            textFieldEndereco.setEditable(false);
            
            clienteSelecionado = newValue;
            if (newValue != null) {
                carregarDadosCliente(textFieldCPF, textFieldRG, textFieldNome, textFieldSorbenome, textFieldEmail, textFieldEndereco, textFieldId, newValue);
            }

        });

    }

    @FXML
    void apagarDadosEscolhidos() {
        carregarDadosCliente(textFieldCPF, textFieldRG, textFieldNome, textFieldSorbenome, textFieldEmail, textFieldEndereco, textFieldId, clienteSelecionado);
    }

    @FXML
    void getTelaAnterior(){
            App.setRoot("menuAdmin");
    }

    @FXML
    void save() {
        clienteSelecionado.setEmail(textFieldEmail.getText());
        clienteSelecionado.setNome(textFieldNome.getText());
        clienteSelecionado.setEndereco(textFieldEndereco.getText());
        clienteSelecionado.setSobrenome(textFieldSorbenome.getText());
    }

    @FXML
    void setEditable() {
        textFieldEmail.setEditable(true);
        textFieldNome.setEditable(true);
        textFieldSorbenome.setEditable(true);
        textFieldEndereco.setEditable(true);

    }

    @FXML
    void excluirCliente() {
        if (!clienteSelecionado.isAtivo()) {
            clientes.remove(clienteSelecionado);
            tableViewClientes.setItems(clientes);
        } else mensagemErro("O cliente selecionado não pode ser excluido, pois tem veiculo alugado!!");
    }


}
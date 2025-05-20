package com.locadorafx.Controllers;

import com.locadorafx.App;
import static com.locadorafx.Controllers.CarregarDadosVeiculo.carregarDadosCliente;
import static com.locadorafx.Controllers.CarregarDadosVeiculo.carregarTabelaClientes;
import static com.locadorafx.Controllers.SceneController.AlertMensage.mensagemErro;
import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Models.ClienteDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class CRUDClienteController {

    @FXML
    private TableColumn<Cliente, String> tableColumnCPF;

    @FXML
    private TableColumn<Cliente, String> tableColumnEmail;

    @FXML
    private TableColumn<Cliente, String> tableColumnEndereco;

    @FXML
    private TableColumn<Cliente, String> tableColumnId;

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

    private ObservableList<Cliente> clientes = FXCollections.observableArrayList(ClienteDAO.find(30));

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
        App.setRoot("AdminMenu-View");
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
            ClienteDAO.delete(clienteSelecionado.getId());
            clientes.remove(clienteSelecionado);
            tableViewClientes.setItems(clientes);
        } else mensagemErro("O cliente selecionado não pode ser excluido, pois tem veiculo alugado!!");
    }

    @FXML
    void abrirTelaMenu(){
        App.setRoot("AdminMenu-View");
    }

}
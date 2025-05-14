package com.locadorafx.Controllers;

import java.io.IOException;

import com.locadorafx.App;
import static com.locadorafx.Controllers.SceneController.AlertMensage.mensagemTelaNaoExistente;
import com.locadorafx.Entities.Clientes.Atributos.CPF;
import com.locadorafx.Entities.Clientes.Atributos.Email;
import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Locadora.Locadora;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private ObservableList<Cliente> clientes;

    private Cliente clienteSelecionado;

    public void initialize() {
        //não funciona corrigir
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tableColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));


        clientes = FXCollections.observableArrayList(Locadora.getClientes());
        tableViewClientes.setItems(clientes);

        tableViewClientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            textFieldEmail.setEditable(false);
            textFieldEndereco.setEditable(false);
            textFieldNome.setEditable(false);
            textFieldSorbenome.setEditable(false);
            
            clienteSelecionado = newValue;
            if (newValue != null) {
                //textFieldId.setText(newValue.getId());
                textFieldCPF.setText(newValue.getCpf());
                textFieldRG.setText(newValue.getRg().toString());
                textFieldNome.setText(newValue.getNome());
                textFieldSorbenome.setText(newValue.getSobrenome());
                textFieldEmail.setText(newValue.getEmail());
                textFieldEndereco.setText(newValue.getEndereco());
                textFieldId.setText(String.valueOf(newValue.getId()));
            }

        });

    }

    @FXML
    void apagarDadosEscolhidos() {
        textFieldSorbenome.setText(clienteSelecionado.getSobrenome());
        textFieldNome.setText(clienteSelecionado.getNome());
        textFieldEmail.setText(clienteSelecionado.getEmail());
        textFieldEndereco.setText(clienteSelecionado.getEndereco());
    }

    @FXML
    void getTelaAnterior(){
        try {
            App.setRoot("menuAdmin");
        } catch (IOException | IllegalStateException e) {
            mensagemTelaNaoExistente(e.getMessage());
        }

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
        textFieldEndereco.setEditable(true);
        textFieldNome.setEditable(true);
        textFieldSorbenome.setEditable(true);
    }

}
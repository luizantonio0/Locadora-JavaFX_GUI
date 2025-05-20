package com.locadorafx.Controllers;

import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Veiculos.Veiculo;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class CarregarDadosVeiculo {


    protected static void carregarPreco(Label preco, Veiculo veiculo) {
        preco.setText("R$ %.2f".formatted((veiculo.getValorDiariaLocacao())));
    }
    protected static void carregarCategoria(Label categoria, Veiculo veiculo){
        categoria.setText(veiculo.getCategoria().toString());
    }
    protected static void carregarMarca(Label marca, Veiculo veiculo){
        marca.setText(veiculo.getMarca().toString());
    }
    protected static void carregarModelo(Label modelo, Veiculo veiculo){
        modelo.setText(veiculo.getModeloToString());
    }

    public static void carregarTabelaClientes(
            TableColumn<Cliente, String> tableColumnNome,
            TableColumn<Cliente, String> tableColumnEmail,
            TableColumn<Cliente, String> tableColumnEndereco,
            TableColumn<Cliente, String> tableColumnCPF,
            TableColumn<Cliente, String> tableColumnId) {
        tableColumnNome.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getNome())));
        tableColumnEmail.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getEmail())));
        tableColumnEndereco.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getEndereco())));
        tableColumnCPF.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getCpf())));
        tableColumnId.setCellValueFactory(cellData -> (new SimpleStringProperty(String.valueOf(cellData.getValue().getId()))));
    }

    public static void carregarDadosCliente(TextField textFieldCPFCliente, TextField textFieldNomeCliente, TextField textFieldIdCliente, Cliente newValue){
        textFieldCPFCliente.setText(newValue.getCpf());
        textFieldNomeCliente.setText(newValue.getNome() + " " + newValue.getSobrenome());
        textFieldIdCliente.setText(String.valueOf(newValue.getId()));
    }
    public static void carregarDadosCliente(TextField textFieldCPF, TextField textFieldRG, TextField textFieldNome, TextField textFieldSobrenome, TextField textFieldEmail, TextArea textFieldEndereco, TextField textFieldId, Cliente newValue){
        textFieldCPF.setText(newValue.getCpf());
        textFieldRG.setText(newValue.getRg());
        textFieldNome.setText(newValue.getNome());
        textFieldSobrenome.setText(newValue.getSobrenome());
        textFieldEmail.setText(newValue.getEmail());
        textFieldEndereco.setText(newValue.getEndereco());
        textFieldId.setText(String.valueOf(newValue.getId()));
    }
    public static void carregarDadosVeiculo(TextField id, TextField placa, TextField marca, TextField modelo, TextField ano, Veiculo newValue){
        id.setText(String.valueOf(newValue.getId()));
        placa.setText(newValue.getPlaca());
        marca.setText(newValue.getMarca().toString());
        modelo.setText(newValue.getModeloToString());
        ano.setText(String.valueOf(newValue.getAno()));

    }
}
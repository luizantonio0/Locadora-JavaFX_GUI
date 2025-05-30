package com.locadorafx.Controllers;

import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Locacao.Locacao;
import com.locadorafx.Entities.Veiculos.Veiculo;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;

public class CarregarDadosTabela {

    protected static void carregarTabelaClientes(
            TableColumn<Cliente, String> nome,
            TableColumn<Cliente, String> email,
            TableColumn<Cliente, String> endereco,
            TableColumn<Cliente, String> CPF,
            TableColumn<Cliente, String> id) {
        nome.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getNome() + " " + cellData.getValue().getSobrenome())));
        email.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getEmail())));
        endereco.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getEndereco())));
        CPF.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getCpf())));
        id.setCellValueFactory(cellData -> (new SimpleStringProperty(String.valueOf(cellData.getValue().getId()))));
    }

    protected static void carregarTabelaVeiculos(
            TableColumn<Veiculo, String> preco,
            TableColumn<Veiculo, String> id,
            TableColumn<Veiculo, String> marca,
            TableColumn<Veiculo, String> modelo,
            TableColumn<Veiculo, String> ano,
            TableColumn<Veiculo, String> placa) {
        preco.setCellValueFactory(cellData -> (new SimpleStringProperty(String.valueOf(cellData.getValue().getValorDiariaLocacao()))));
        id.setCellValueFactory(cellData -> (new SimpleStringProperty(String.valueOf(cellData.getValue().getId()))));
        marca.setCellValueFactory(cellData -> (new SimpleStringProperty(String.valueOf(cellData.getValue().getMarca()))));
        modelo.setCellValueFactory(cellData -> (new SimpleStringProperty(String.valueOf(cellData.getValue().getModeloToString()))));
        ano.setCellValueFactory(cellData -> (new SimpleStringProperty(String.valueOf(cellData.getValue().getAno()))));
        placa.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getPlaca())));
    }

    protected static void carregarTabelaVeiculos(
            TableColumn<Veiculo, String> preco,
            TableColumn<Veiculo, String> id,
            TableColumn<Veiculo, String> marca,
            TableColumn<Veiculo, String> modelo,
            TableColumn<Veiculo, String> ano,
            TableColumn<Veiculo, String> placa,
            TableColumn<Veiculo, String> estado) {
        carregarTabelaVeiculos(preco, id, marca, modelo, ano, placa);
        estado.setCellValueFactory(cellData -> (new SimpleStringProperty(String.valueOf(cellData.getValue().getEstado()))));
    }

    protected static void carregarTabelaLocacoes(
            TableColumn<Locacao, String> id,
            TableColumn<Locacao, String> nomeCliente,
            TableColumn<Locacao, String> dias,
            TableColumn<Locacao, String> placaVeiculo,
            TableColumn<Locacao, String> valor
    ) {
        id.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        nomeCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getNome() + " " + cellData.getValue().getCliente().getSobrenome()));
        dias.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDias())));
        placaVeiculo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVeiculo().getPlaca()));
        valor.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getValor())));
    }
}

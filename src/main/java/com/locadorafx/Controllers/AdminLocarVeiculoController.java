package com.locadorafx.Controllers;

import com.locadorafx.Entities.Clientes.Atributos.CPF;
import com.locadorafx.Entities.Clientes.Atributos.Email;
import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Locadora.Locadora;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Placa;
import com.locadorafx.Entities.Veiculos.Veiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.temporal.ChronoUnit;

import static com.locadorafx.Controllers.CarregarDadosVeiculo.*;
import static com.locadorafx.Controllers.locarCarro1Controller.veiculoSelecionado;

public class AdminLocarVeiculoController {

    @FXML
    private DatePicker datePickerInicio;

    @FXML
    private DatePicker datePickerTermino;

    @FXML
    private TableColumn<Veiculo, Year> tableColumnAnoVeiculo;

    @FXML
    private TableColumn<Cliente, CPF> tableColumnCPF;

    @FXML
    private TableColumn<Cliente, Email> tableColumnEmail;

    @FXML
    private TableColumn<Cliente, String> tableColumnEndereco;

    @FXML
    private TableColumn<Cliente, Integer> tableColumnId;

    @FXML
    private TableColumn<Veiculo, Integer> tableColumnIdVeiculo;

    @FXML
    private TableColumn<Veiculo, Marca> tableColumnMarcaVeiculo;

    @FXML
    private TableColumn<Veiculo, String> tableColumnModeloVeiculo;

    @FXML
    private TableColumn<Cliente, String> tableColumnNome;

    @FXML
    private TableColumn<Veiculo, Placa> tableColumnPlacaVeiculo;

    @FXML
    private TableColumn<Veiculo, Double> tableColumnPrecoDiariaVeiculo;

    @FXML
    private TableView<Cliente> tableViewClientes;

    @FXML
    private TableView<Veiculo> tableViewVeiculo;

    @FXML
    private TextField textFieldAnoVeiculo;

    @FXML
    private TextField textFieldCPFCliente;

    @FXML
    private TextField textFieldIdCliente;

    @FXML
    private TextField textFieldIdVeiculo;

    @FXML
    private TextField textFieldMarcaVeiculo;

    @FXML
    private TextField textFieldModeloVeiculo;

    @FXML
    private TextField textFieldNomeCliente;

    @FXML
    private TextField textFieldPlacaVeiculo;

    private ObservableList<Cliente> clientes = FXCollections.observableArrayList(Locadora.getClientes());

    private ObservableList<Veiculo> estoque = FXCollections.observableArrayList(Locadora.getEstoque());

    private Veiculo veiculoSelecionado;

    private Cliente clienteSelecionado;

    private int diasLocacao;
    private double valorDiariaLocacao;

    @FXML
    private Label textLabelValor;

    public void initialize(){
        carregarTabelaClientes(tableColumnNome, tableColumnEmail, tableColumnEndereco, tableColumnCPF, tableColumnId);

        tableViewClientes.setItems(clientes);

        tableViewClientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            clienteSelecionado = newValue;
            if (newValue != null) {
                carregarDadosCliente(textFieldCPFCliente, textFieldNomeCliente, textFieldIdCliente, newValue);
            }

        });

        //Usar Lambda na refatoração para evitar repetiçao do dado preço Diaria
        tableColumnPrecoDiariaVeiculo.setCellValueFactory(new PropertyValueFactory<>("precoDiaria"));
        tableColumnIdVeiculo.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnMarcaVeiculo.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableColumnModeloVeiculo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tableColumnAnoVeiculo.setCellValueFactory(new PropertyValueFactory<>("ano"));
        tableColumnPlacaVeiculo.setCellValueFactory(new PropertyValueFactory<>("placa"));

        tableViewVeiculo.setItems(estoque);

        tableViewVeiculo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            veiculoSelecionado = newValue;
            if (newValue != null) {
                carregarDadosVeiculo(textFieldIdVeiculo, textFieldPlacaVeiculo, textFieldMarcaVeiculo, textFieldModeloVeiculo, textFieldAnoVeiculo, newValue);
            }

        });
    }

    @FXML
    void LocarVeiculo(ActionEvent event) {
        if(veiculoSelecionado != null || clienteSelecionado != null){
        veiculoSelecionado.locar(diasLocacao, datePickerInicio.getValue().atStartOfDay(), clienteSelecionado);
            System.out.println(Locadora.getLocacoes().getLast());
        }
    }

    @FXML
    void apagarDadosEscolhidos(ActionEvent event) {

    }

    @FXML
    void getTelaAnterior(ActionEvent event) {

    }

    @FXML
    void calcularDiasLocacao() {
        if (datePickerInicio.getValue() != null && datePickerTermino.getValue() != null || veiculoSelecionado != null) {

            diasLocacao = ((int) ChronoUnit.DAYS.between(datePickerInicio.getValue(), datePickerTermino.getValue()) +1);
            valorDiariaLocacao = veiculoSelecionado.getValorDiariaLocacao(diasLocacao);

            textLabelValor.setText("R$ %.2f".formatted(valorDiariaLocacao));
        }
    }

}
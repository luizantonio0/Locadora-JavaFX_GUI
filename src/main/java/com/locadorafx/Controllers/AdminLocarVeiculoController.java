package com.locadorafx.Controllers;

import com.locadorafx.App;
import com.locadorafx.Controllers.SceneController.AlertMensage;
import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Locadora.Locadora;
import com.locadorafx.Entities.Veiculos.Veiculo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.temporal.ChronoUnit;

import static com.locadorafx.Controllers.CarregarDadosVeiculo.*;

public class AdminLocarVeiculoController {

    @FXML
    private DatePicker datePickerInicio;

    @FXML
    private DatePicker datePickerTermino;

    @FXML
    private TableColumn<Veiculo, String> tableColumnAnoVeiculo;

    @FXML
    private TableColumn<Cliente, String> tableColumnCPF;

    @FXML
    private TableColumn<Cliente, String> tableColumnEmail;

    @FXML
    private TableColumn<Cliente, String> tableColumnEndereco;

    @FXML
    private TableColumn<Cliente, String> tableColumnId;

    @FXML
    private TableColumn<Veiculo, String> tableColumnIdVeiculo;

    @FXML
    private TableColumn<Veiculo, String> tableColumnMarcaVeiculo;

    @FXML
    private TableColumn<Veiculo, String> tableColumnModeloVeiculo;

    @FXML
    private TableColumn<Cliente, String> tableColumnNome;

    @FXML
    private TableColumn<Veiculo, String> tableColumnPlacaVeiculo;

    @FXML
    private TableColumn<Veiculo, String> tableColumnPrecoDiariaVeiculo;

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

    private final ObservableList<Cliente> clientes = FXCollections.observableArrayList(Locadora.getClientes());

    private final ObservableList<Veiculo> estoque = FXCollections.observableArrayList(Locadora.getEstoque());

    private Veiculo veiculoSelecionado;

    private Cliente clienteSelecionado;

    private int diasLocacao;

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
        tableColumnPrecoDiariaVeiculo.setCellValueFactory(cellData -> (new SimpleStringProperty(String.valueOf(cellData.getValue().getValorDiariaLocacao()))));
        tableColumnIdVeiculo.setCellValueFactory(cellData -> (new SimpleStringProperty(String.valueOf(cellData.getValue().getId()))));
        tableColumnMarcaVeiculo.setCellValueFactory(cellData -> (new SimpleStringProperty(String.valueOf(cellData.getValue().getMarca()))));
        tableColumnModeloVeiculo.setCellValueFactory(cellData -> (new SimpleStringProperty(String.valueOf(cellData.getValue().getModeloToString()))));
        tableColumnAnoVeiculo.setCellValueFactory(cellData -> (new SimpleStringProperty(String.valueOf(cellData.getValue().getAno()))));
        tableColumnPlacaVeiculo.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getPlaca())));

        tableViewVeiculo.setItems(estoque);

        tableViewVeiculo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            veiculoSelecionado = newValue;
            if (newValue != null) {
                carregarDadosVeiculo(textFieldIdVeiculo, textFieldPlacaVeiculo, textFieldMarcaVeiculo, textFieldModeloVeiculo, textFieldAnoVeiculo, newValue);
            }

        });
    }

    @FXML
    void LocarVeiculo() {
            try {
            veiculoSelecionado.locar(diasLocacao, datePickerInicio.getValue(), clienteSelecionado);
            AlertMensage.mensagemSucesso("Locado com sucesso!");

            } catch (IllegalStateException ex){
                AlertMensage.mensagemErro("Erro: " + ex.getMessage());
            } catch (NullPointerException ex){
                AlertMensage.mensagemErro("Não foi possivel locar o veiculo: Defina as datas da locação");
            }
    }

    @FXML
    void apagarDadosEscolhidos() {
        tableViewVeiculo.getSelectionModel().clearSelection();
        tableViewClientes.getSelectionModel().clearSelection();
    }

    @FXML
    void getTelaAnterior() {
        App.setRoot("AdminMenu-View");
    }

    @FXML
    void calcularDiasLocacao() {
        if (datePickerInicio.getValue() != null && datePickerTermino.getValue() != null && veiculoSelecionado != null) {
            double valorDiariaLocacao;

            diasLocacao = ((int) ChronoUnit.DAYS.between(datePickerInicio.getValue(), datePickerTermino.getValue().plusDays(1)));
            valorDiariaLocacao = veiculoSelecionado.getValorDiariaLocacao(diasLocacao);

            textLabelValor.setText("R$ %.2f".formatted(valorDiariaLocacao));
        }
    }
    @FXML
    void abrirTelaMenu(){
        App.setRoot("AdminMenu-View");
    }

}
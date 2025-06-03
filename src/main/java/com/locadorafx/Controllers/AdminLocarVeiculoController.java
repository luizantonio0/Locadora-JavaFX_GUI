package com.locadorafx.Controllers;

import java.time.temporal.ChronoUnit;

import com.locadorafx.App;
import static com.locadorafx.Controllers.CarregarDadosVeiculo.carregarDadosCliente;
import static com.locadorafx.Controllers.CarregarDadosVeiculo.carregarDadosVeiculo;
import com.locadorafx.Controllers.SceneController.AlertMensage;
import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Veiculos.Veiculo;

import com.locadorafx.Models.ClienteDAO;
import com.locadorafx.Models.VeiculoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AdminLocarVeiculoController {

    @FXML
    private MenuItem menuItemMotocicleta;

    @FXML
    private MenuItem menuItemVan;

    @FXML
    private MenuItem menuItemVeiculo;

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
    private TableColumn<Veiculo, String> tableColumnEstado;

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

    private final ObservableList<Cliente> clientes = FXCollections.observableArrayList(ClienteDAO.find(100));

    private final ObservableList<Veiculo> estoque = FXCollections.observableArrayList(VeiculoDAO.find(100));

    private Veiculo veiculoSelecionado;

    private Cliente clienteSelecionado;

    private int diasLocacao;

    @FXML
    private Label textLabelValor;

    public void initialize(){
        CarregarDadosTabela.carregarTabelaClientes(tableColumnNome, tableColumnEmail, tableColumnEndereco, tableColumnCPF, tableColumnId);

        tableViewClientes.setItems(clientes);

        tableViewClientes.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            clienteSelecionado = newValue;
            if (newValue != null) {
                carregarDadosCliente(textFieldCPFCliente, textFieldNomeCliente, textFieldIdCliente, newValue);
            }
        });

        CarregarDadosTabela.carregarTabelaVeiculos(tableColumnPrecoDiariaVeiculo, tableColumnIdVeiculo, tableColumnMarcaVeiculo, tableColumnModeloVeiculo, tableColumnAnoVeiculo, tableColumnPlacaVeiculo, tableColumnEstado);

        tableViewVeiculo.setItems(estoque);

        tableViewVeiculo.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
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

    @FXML
    void alterarTipoTabela(ActionEvent event) {
        if (event.getSource() == menuItemVeiculo) {
            estoque.setAll(VeiculoDAO.find(100, "Automovel"));
        } else if (event.getSource() == menuItemMotocicleta) {
            estoque.setAll(VeiculoDAO.find(100, "Motocicleta"));
        } else if (event.getSource() == menuItemVan) {
            estoque.setAll(VeiculoDAO.find(100, "Van"));
        } else {
            estoque.setAll(VeiculoDAO.find(100));
        }
    }

}
package com.locadorafx.Controllers;

import com.locadorafx.App;
import com.locadorafx.Entities.Locacao.Locacao;
import com.locadorafx.Entities.Locadora.Locadora;
import com.locadorafx.Models.LocacaoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import static com.locadorafx.Controllers.CarregarDadosVeiculo.*;

public class AdminLocacaoController {

    @FXML
    private TableColumn<Locacao, String> tableColumnDias;

    @FXML
    private TableColumn<Locacao, String> tableColumnId;

    @FXML
    private TableColumn<Locacao, String> tableColumnNomeCliente;

    @FXML
    private TableColumn<Locacao, String> tableColumnPlacaVeiculo;

    @FXML
    private TableColumn<Locacao, String> tableColumnValor;

    @FXML
    private TableView<Locacao> tableViewLocacao;

    @FXML
    private TextField textFieldAnoVeiculo;

    @FXML
    private TextField textFieldCPFCliente;

    @FXML
    private TextField textFieldDiasLocacao;

    @FXML
    private TextField textFieldIdCliente;

    @FXML
    private TextField textFieldIdLocacao;

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

    @FXML
    private TextField textFieldValorLocacao;

    private ObservableList<Locacao> locacoes = FXCollections.observableArrayList(LocacaoDAO.find(100));

    private Locacao locacaoSelecionada;

    public void initialize() {
        tableColumnId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        tableColumnNomeCliente.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCliente().getNome())));
        tableColumnDias.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDias())));
        tableColumnPlacaVeiculo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVeiculo().getPlaca()));
        tableColumnValor.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getValor())));

        tableViewLocacao.setItems(locacoes);

        tableViewLocacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                locacaoSelecionada = newValue;
                carregarDadosCliente(textFieldCPFCliente, textFieldNomeCliente, textFieldIdCliente, newValue.getCliente());
                carregarDadosVeiculo(textFieldIdVeiculo, textFieldPlacaVeiculo, textFieldMarcaVeiculo, textFieldModeloVeiculo, textFieldAnoVeiculo, newValue.getVeiculo());
                textFieldDiasLocacao.setText(String.valueOf(newValue.getDias()));
                textFieldIdLocacao.setText(String.valueOf(newValue.getId()));
                textFieldValorLocacao.setText(String.valueOf(newValue.getValor()));

            }
        });

    }

    @FXML
    void apagarDadosEscolhidos() {
    }

    @FXML
    void devolverVeiculo() {

        locacaoSelecionada.getVeiculo().devolver();

        locacoes.remove(locacaoSelecionada);
        tableViewLocacao.setItems(locacoes);
        tableViewLocacao.getSelectionModel().clearSelection();
    }

    @FXML
    void getTelaAnterior() {
        App.setRoot("AdminMenu-View");
    }
    @FXML
    void abrirTelaMenu(){
        App.setRoot("AdminMenu-View");
    }

}

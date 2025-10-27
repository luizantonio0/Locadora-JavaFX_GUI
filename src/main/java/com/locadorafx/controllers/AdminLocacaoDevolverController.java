package com.locadorafx.controllers;

import com.locadorafx.App;
import com.locadorafx.models.Locacao.Locacao;
import com.locadorafx.DAO.LocacaoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import static com.locadorafx.controllers.CarregarDadosVeiculo.*;
import static com.locadorafx.controllers.SceneController.AlertMensage.mensagemErro;
import static com.locadorafx.controllers.SceneController.AlertMensage.mensagemSucesso;

public class AdminLocacaoDevolverController {

    @FXML
    private MenuItem menuItemAtiva;

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

    private final ObservableList<Locacao> locacoes = FXCollections.observableArrayList(LocacaoDAO.find(100, true));

    private Locacao locacaoSelecionada;

    public void initialize() {
        CarregarDadosTabela.carregarTabelaLocacoes(tableColumnId, tableColumnNomeCliente, tableColumnDias, tableColumnPlacaVeiculo, tableColumnValor);

        tableViewLocacao.setItems(locacoes);

        tableViewLocacao.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
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
    void filtrarLocacoes(ActionEvent event) {
        if (event.getSource() == menuItemAtiva) {
            locacoes.setAll(LocacaoDAO.find(100, true));
        } else {
            locacoes.setAll(LocacaoDAO.find(100, false));
        }
    }

    @FXML
    void devolverVeiculo() {
        try {
            locacaoSelecionada.getVeiculo().devolver();
            locacoes.remove(locacaoSelecionada);
            tableViewLocacao.setItems(locacoes);
            mensagemSucesso("Devolvido com sucesso!");
        } catch (IllegalStateException ex) {
            mensagemErro(ex.getMessage());
        }
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

package com.locadorafx.Controllers;

import com.locadorafx.Entities.Clientes.Atributos.CPF;
import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Locacao.Locacao;
import com.locadorafx.Entities.Locadora.Locadora;
import com.locadorafx.Entities.Veiculos.Atributos.Placa;
import com.locadorafx.Entities.Veiculos.Veiculo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private ObservableList<Locacao> locacoes = FXCollections.observableArrayList(Locadora.getLocacoes());

    private Locadora locadoraSelecionada;

    public void initialize() {
        tableColumnId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        tableColumnNomeCliente.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCliente().getNome())));
        tableColumnDias.setCellValueFactory(new PropertyValueFactory<>("dias"));
        //tableColumnPlacaVeiculo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValor()));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        tableViewLocacao.setItems(locacoes);
    }

    @FXML
    void apagarDadosEscolhidos(ActionEvent event) {

    }

    @FXML
    void devolverVeiculo(ActionEvent event) {

    }

    @FXML
    void getTelaAnterior(ActionEvent event) {

    }

}

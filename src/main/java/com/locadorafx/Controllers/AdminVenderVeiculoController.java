package com.locadorafx.Controllers;

import com.locadorafx.App;
import com.locadorafx.Entities.Locadora.Locadora;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Veiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.Year;

import static com.locadorafx.Controllers.CarregarDadosVeiculo.*;

public class AdminVenderVeiculoController {

    @FXML
    private TableColumn<Veiculo, Year> tableColumnAnoVeiculo;

    @FXML
    private TableColumn<Veiculo, Integer> tableColumnIdVeiculo;

    @FXML
    private TableColumn<Veiculo, Marca> tableColumnMarcaVeiculo;

    @FXML
    private TableColumn<Veiculo, String> tableColumnModeloVeiculo;

    @FXML
    private TableColumn<Veiculo, String> tableColumnPlacaVeiculo;

    @FXML
    private TableColumn<Veiculo, String> tableColumnPrecoDiariaVeiculo;

    @FXML
    private TableView<Veiculo> tableViewVeiculo;

    @FXML
    private TextField textFieldAno;

    @FXML
    private TextField textFieldCategoria;

    @FXML
    private TextField textFieldEstado;

    @FXML
    private TextField textFieldId;

    @FXML
    private TextField textFieldMarca;

    @FXML
    private TextField textFieldModelo;

    @FXML
    private TextField textFieldPlaca;

    @FXML
    private TextField textFieldValorVenda;

    private ObservableList<Veiculo> estoque = FXCollections.observableArrayList(Locadora.getEstoque());

    private Veiculo veiculoSelecionado;


    public void initialize(){

        //Usar Lambda na refatoração para evitar repetiçao do dado preço Diaria
        //tableColumnPrecoDiariaVeiculo.setCellValueFactory(new PropertyValueFactory<>("precoDiaria"));
        tableColumnIdVeiculo.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnMarcaVeiculo.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableColumnModeloVeiculo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tableColumnAnoVeiculo.setCellValueFactory(new PropertyValueFactory<>("ano"));
        tableColumnPlacaVeiculo.setCellValueFactory(new PropertyValueFactory<>("placa"));

        tableViewVeiculo.setItems(estoque);

        tableViewVeiculo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            veiculoSelecionado = newValue;
            if (newValue != null) {
                carregarDadosVeiculo(textFieldId, textFieldPlaca, textFieldMarca, textFieldModelo, textFieldAno, newValue);
                textFieldEstado.setText(newValue.getEstado().toString());
                textFieldCategoria.setText(newValue.getCategoria().toString());
                textFieldValorVenda.setText(String.valueOf(newValue.getValorParaVenda()));
            }

        });
    }

    @FXML
    void apagarDadosEscolhidos() {
        //Talvez Tirar
    }

    @FXML
    void getTelaAnterior() {
        App.setRoot("AdminMenu");
    }

    @FXML
    void venderVeiculo() {
        try {
            veiculoSelecionado.vender();
            textFieldEstado.setText(veiculoSelecionado.getEstado().toString());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }


    }

}
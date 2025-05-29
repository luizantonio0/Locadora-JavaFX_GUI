package com.locadorafx.Controllers;

import com.locadorafx.App;
import com.locadorafx.Controllers.SceneController.AlertMensage;
import com.locadorafx.Entities.Veiculos.Veiculo;
import com.locadorafx.Models.VeiculoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import static com.locadorafx.Controllers.CarregarDadosVeiculo.*;

public class AdminVenderVeiculoController {

    @FXML
    private TableColumn<Veiculo, String> tableColumnAnoVeiculo;

    @FXML
    private TableColumn<Veiculo, String> tableColumnIdVeiculo;

    @FXML
    private TableColumn<Veiculo, String> tableColumnMarcaVeiculo;

    @FXML
    private TableColumn<Veiculo, String> tableColumnModeloVeiculo;
    @FXML
    private TableColumn<Veiculo, String> tableColumnPrecoDiariaVeiculo;

    @FXML
    private TableColumn<Veiculo, String> tableColumnPlacaVeiculo;


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

    private final ObservableList<Veiculo> estoque = FXCollections.observableArrayList(VeiculoDAO.find(100));

    private Veiculo veiculoSelecionado;


    public void initialize(){

        CarregarDadosTabela.carregarTabelaVeiculos(tableColumnPrecoDiariaVeiculo, tableColumnIdVeiculo, tableColumnMarcaVeiculo, tableColumnModeloVeiculo, tableColumnAnoVeiculo, tableColumnPlacaVeiculo);

        tableViewVeiculo.setItems(estoque);

        tableViewVeiculo.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
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
        App.setRoot("AdminMenu-View");
    }

    @FXML
    void venderVeiculo() {
        try {
            veiculoSelecionado.vender();
            VeiculoDAO.updateEstado(veiculoSelecionado);
            textFieldEstado.setText(veiculoSelecionado.getEstado().toString());
            AlertMensage.mensagemSucesso("O veiculo foi vendido com sucesso!!");
        } catch (IllegalStateException e) {
            AlertMensage.mensagemErro("Erro: " + e.getMessage());
        }


    }
    @FXML
    void abrirTelaMenu(){
        App.setRoot("AdminMenu-View");
    }
}
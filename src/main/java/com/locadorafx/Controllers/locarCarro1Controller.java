package com.locadorafx.Controllers;

import com.locadorafx.App;
import com.locadorafx.Entities.Locadora.Locadora;
import com.locadorafx.Entities.Veiculos.Veiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

import static com.locadorafx.Controllers.SceneController.AlertMensage.mensagemTelaNaoExistente;

public class locarCarro1Controller {

    @FXML
    private Label TexFieldPrice1;

    @FXML
    private Label TexFieldPrice2;

    @FXML
    private Label TexFieldPrice3;

    //private Label[] TextFieldPrices = {TexFieldPrice1, TexFieldPrice2, TexFieldPrice3};

    @FXML
    private Label TextFieldCategoria1;

    @FXML
    private Label TextFieldCategoria2;

    @FXML
    private Label TextFieldCategoria3;

    @FXML
    private Label TextFieldMarca1;

    @FXML
    private Label TextFieldMarca2;

    @FXML
    private Label TextFieldMarca3;

    @FXML
    private Label TextFieldModelo1;

    @FXML
    private Label TextFieldModelo2;

    @FXML
    private Label TextFieldModelo3;


    private static int ordemCarros = 0;
    protected static Veiculo veiculoSelecionado;

    public void initialize(){
        carregarCarros();
    }

    @FXML
    void VoltarParaLogin(){
        try {
            App.setRoot("login-View");
        } catch (IOException e) {
            mensagemTelaNaoExistente(e.getMessage());
        }
    }

    @FXML
    void abrirLocarVeiculo(){

    }

    @FXML
    void abrirMeusDados(){
        try {
            App.setRoot("");
        } catch (IOException | IllegalStateException e) {
            mensagemTelaNaoExistente(e.getMessage());
        }
    }

    @FXML
    void avancar() {
        if (ordemCarros == Locadora.getEstoque().size()-3){
            ordemCarros = 0;
        } else {
            ordemCarros +=3;
        }
        carregarCarros();
    }

    @FXML
    void locarVeiculo(ActionEvent event) throws IOException {
        var btn = event.getSource();

        veiculoSelecionado = switch (btn.toString()){
            case "Button[id=btnLocar01, styleClass=button]''" -> Locadora.getEstoque().get(ordemCarros);
            case "Button[id=btnLocar02, styleClass=button]''" -> Locadora.getEstoque().get(ordemCarros+1);
            case "Button[id=btnLocar03, styleClass=button]''" -> Locadora.getEstoque().get(ordemCarros+2);
            default -> null;
        };

        try {
            App.setRoot("locarCarro2-View");
        } catch (IOException | IllegalStateException e) {
            mensagemTelaNaoExistente(e.getMessage());
        }
    }

    @FXML
    void voltar() {
        if (ordemCarros == 0){
            ordemCarros = Locadora.getEstoque().size()-3;
        } else{
            ordemCarros -= 3;
        }
        carregarCarros();
    }

    private void carregarCarros() {

        CarregarDadosVeiculo.carregarPreco(TexFieldPrice1, Locadora.getEstoque().get(ordemCarros));
        CarregarDadosVeiculo.carregarPreco(TexFieldPrice2, Locadora.getEstoque().get(ordemCarros+1));
        CarregarDadosVeiculo.carregarPreco(TexFieldPrice3, Locadora.getEstoque().get(ordemCarros+2));

        CarregarDadosVeiculo.carregarCategoria(TextFieldCategoria1, Locadora.getEstoque().get(ordemCarros));
        CarregarDadosVeiculo.carregarCategoria(TextFieldCategoria2, Locadora.getEstoque().get(ordemCarros+1));
        CarregarDadosVeiculo.carregarCategoria(TextFieldCategoria3, Locadora.getEstoque().get(ordemCarros+2));

        CarregarDadosVeiculo.carregarMarca(TextFieldMarca1, Locadora.getEstoque().get(ordemCarros));
        CarregarDadosVeiculo.carregarMarca(TextFieldMarca2, Locadora.getEstoque().get(ordemCarros+1));
        CarregarDadosVeiculo.carregarMarca(TextFieldMarca3, Locadora.getEstoque().get(ordemCarros+2));

        CarregarDadosVeiculo.carregarModelo(TextFieldModelo1, Locadora.getEstoque().get(ordemCarros));
        CarregarDadosVeiculo.carregarModelo(TextFieldModelo2, Locadora.getEstoque().get(ordemCarros+1));
        CarregarDadosVeiculo.carregarModelo(TextFieldModelo3, Locadora.getEstoque().get(ordemCarros+2));

    }
}
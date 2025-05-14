package com.locadorafx.Controllers;

import com.locadorafx.App;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.temporal.ChronoUnit;

import static com.locadorafx.Controllers.SceneController.AlertMensage.mensagemTelaNaoExistente;
import static com.locadorafx.Controllers.locarCarro1Controller.veiculoSelecionado;

public class locarCarro2Controller {

    @FXML
    private Label TexFieldPrice;

    @FXML
    private Label TextFieldCategoria;

    @FXML
    private Label TextFieldMarca;

    @FXML
    private Label TextFieldModelo;
    @FXML
    private DatePicker datePickerInicio;

    @FXML
    private DatePicker datePickerTermino;

    private int diasLocacao;
    private double valorDiariaLocacao;

    public void initialize() {
        CarregarDadosVeiculo.carregarCategoria(TextFieldCategoria, veiculoSelecionado);
        CarregarDadosVeiculo.carregarMarca(TextFieldMarca, veiculoSelecionado);
        CarregarDadosVeiculo.carregarModelo(TextFieldModelo, veiculoSelecionado);
    }

    @FXML
    void calcularDiasLocacao() {
        if (datePickerInicio.getValue() != null && datePickerTermino.getValue() != null) {

            diasLocacao = ((int) ChronoUnit.DAYS.between(datePickerInicio.getValue(), datePickerTermino.getValue()) +1);
            valorDiariaLocacao = veiculoSelecionado.getValorDiariaLocacao(diasLocacao);

            TexFieldPrice.setText("R$ %.2f".formatted(valorDiariaLocacao));
        }
    }

    @FXML
    void locar() {
        //Instanciar nova locação

    }

    @FXML
    void voltarTela(){
        try {
            App.setRoot("locarCarro1-View");
        } catch (IOException | IllegalStateException e) {
            mensagemTelaNaoExistente(e.getMessage());
        }
    }
}

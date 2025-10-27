package com.locadorafx.controllers;

import com.locadorafx.App;
import com.locadorafx.DAO.LocacaoDAO;
import com.locadorafx.Reports.RelatorioFinanceiroLocacao;
import com.locadorafx.Reports.RelatorioFinanceiroLocacaoCSV;
import javafx.fxml.FXML;

import java.io.IOException;

import static com.locadorafx.controllers.SceneController.AlertMensage.mensagemErro;
import static com.locadorafx.controllers.SceneController.AlertMensage.mensagemSucesso;

public class AdminMenuViewController {

    @FXML
    void abrirTelaCadastrarClientes() {
        App.setRoot("cadastrarCliente-View");
    }

    @FXML
    void abrirTelaCrudClientes() {
        App.setRoot("CRUD-Cliente-View");
    }

    @FXML
    void abrirTelaDevolverVeiculo() {
        App.setRoot("AdminLocacaoDevolver-View");
    }

    @FXML
    void abrirTelaLocarVeiculos() {
        App.setRoot("AdminLocarCarro-View");
    }

    @FXML
    void abrirTelaLocacoes() {
        App.setRoot("AdminLocacaoDevolver-View");
    }

    @FXML
    void abrirTelaMenu() {
        App.setRoot("AdminMenu-View");
    }

    @FXML
    void abrirTelaVeiculos() {
        App.setRoot("cadastrarVeiculo-View");
    }

    @FXML
    void abrirTelaVenderVeiculos() {
        App.setRoot("AdminVenderVeiculo-View");
    }

    @FXML
    void fecharPrograma() {
        App.setRoot("login-View");
    }
    @FXML
    void gerarRelatorioCSV(){
        String[] cabecalho = {"Id", "Dias", "Data", "ClienteId", "VeiculoId", "Ativo", "Valor"};

        RelatorioFinanceiroLocacaoCSV relatorio = new RelatorioFinanceiroLocacaoCSV();
        try {
            relatorio.gerarRelatorio(cabecalho, LocacaoDAO.findAll(100), "RelatorioFinanceiroLocacao");
            mensagemSucesso("Relatório gerado com sucesso!");
        } catch (IOException e) {
            mensagemErro(e.getMessage());
        }
    }

    @FXML
    void gerarRelatorioExcel() {
        String[] cabecalho = {"Id", "Dias", "Valor", "Data", "ClienteId", "VeiculoId", "Ativo"};

        RelatorioFinanceiroLocacao relatorio = new RelatorioFinanceiroLocacao();
        try {
        relatorio.gerarRelatorio(cabecalho, LocacaoDAO.findAll(100), "RelatorioFinanceiroLocacao");
        mensagemSucesso("Relatório gerado com sucesso!");
        } catch (IOException e) {
            mensagemErro(e.getMessage());
        }
    }
}
package com.locadorafx.Controllers.SceneController;

import javafx.scene.control.Alert;


public class AlertMensage {
    public static void mensagemCadastroVeiculoSucesso() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Veículo cadastrado com sucesso!");
        alert.setHeaderText(null);
        alert.setContentText("O veículo foi cadastrado com sucesso!");
        alert.showAndWait();
    }

    public static void mensagemCadastroVeiculoErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro ao cadastrar veiculo!!");
        alert.setContentText("Erro: " + mensagem);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void mensagemTelaNaoExistente(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Tela não encontrada!");
        alert.setContentText("Erro: " + mensagem);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}

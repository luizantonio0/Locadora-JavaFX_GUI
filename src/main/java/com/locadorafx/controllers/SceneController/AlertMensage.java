package com.locadorafx.controllers.SceneController;

import javafx.scene.control.Alert;

public class AlertMensage {

    public static void mensagemSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("SUCESSO!!");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void mensagemErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRO!!");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
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
package com.locadorafx.Controllers.SceneController;

import javafx.scene.control.Alert;


public class AlertMensage {
    public static void mensagemSucessoCarro() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Carro cadastrado com sucesso!");
        alert.setHeaderText(null);
        alert.setContentText("O carro foi cadastrado com sucesso!");
        alert.showAndWait();
        //textLabelMensagemErro.setTextFill(Color.web("#77ff89"));
    }

    public static void mensagemErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro ao cadastrar veiculo!!");
        alert.setContentText("Erro: " + mensagem);
        alert.setHeaderText(null);
        alert.showAndWait();

    }
}

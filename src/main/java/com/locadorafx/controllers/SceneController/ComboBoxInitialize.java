package com.locadorafx.controllers.SceneController;

import java.util.List;

import com.locadorafx.models.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.models.Veiculos.Atributos.Modelos.ModeloAutomovel;
import com.locadorafx.models.Veiculos.Atributos.Modelos.ModeloMotocicleta;
import com.locadorafx.models.Veiculos.Atributos.Modelos.ModeloVan;

import javafx.scene.control.ComboBox;

public class ComboBoxInitialize {
    public static void ComboBoxInitializeModelo(String tipoVeiculo, ComboBox<String> comboBoxModelo, Marca marca) {

        List<String> modelos;



        comboBoxModelo.setDisable(false);

        switch (tipoVeiculo){
            case "Automovel" -> {
                modelos = ModeloAutomovel.getModeloAutomovel(marca); comboBoxModelo.getItems().clear();
                comboBoxModelo.getItems().addAll(modelos);
            }
            case "Motocicleta" -> {
                modelos = ModeloMotocicleta.getModeloMotocicleta(marca); comboBoxModelo.getItems().clear();
                comboBoxModelo.getItems().addAll(modelos);
            }
            case "Van" -> {
                modelos = ModeloVan.getModeloVan(marca); comboBoxModelo.getItems().clear();
                comboBoxModelo.getItems().addAll(modelos);
            }
        }
    }


}

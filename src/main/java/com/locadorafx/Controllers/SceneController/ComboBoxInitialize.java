package com.locadorafx.Controllers.SceneController;

import java.util.List;

import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloAutomovel;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloMotocicleta;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloVan;

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

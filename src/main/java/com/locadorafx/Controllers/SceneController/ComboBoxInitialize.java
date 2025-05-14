package com.locadorafx.Controllers.SceneController;

import java.util.List;

import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloAutomovel;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloMotocicleta;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloVan;

import javafx.scene.control.ComboBox;

public class ComboBoxInitialize {
    public static void ComboBoxInitializeModelo(short tipoVeiculo, ComboBox<?> comboBoxModelo, Marca marca) {
        switch (tipoVeiculo){
            case 0 -> ComboBoxInitializeModeloAutomovel((ComboBox<ModeloAutomovel>) comboBoxModelo, marca);
            case 1 -> ComboBoxInitializeModeloMotocicleta((ComboBox<ModeloMotocicleta>) comboBoxModelo, marca);
            case 2 -> ComboBoxInitializeModeloVan((ComboBox<ModeloVan>) comboBoxModelo, marca);
        }
    }

    private static void ComboBoxInitializeModeloAutomovel(ComboBox<ModeloAutomovel> comboBoxModelo, Marca newValue) {
        List<ModeloAutomovel> modelos = ModeloAutomovel.getModeloAutomovel(newValue);

        comboBoxModelo.getItems().clear();
        comboBoxModelo.getItems().addAll(modelos.toArray(ModeloAutomovel[]::new));

        comboBoxModelo.setDisable(false);
    }
    private static void ComboBoxInitializeModeloVan(ComboBox<ModeloVan> comboBoxModelo, Marca newValue) {
        List<ModeloVan> modelos = ModeloVan.getModeloVan(newValue);

        comboBoxModelo.getItems().clear();
        comboBoxModelo.getItems().addAll(modelos.toArray(ModeloVan[]::new));

        comboBoxModelo.setDisable(false);
    }
    private static void ComboBoxInitializeModeloMotocicleta(ComboBox<ModeloMotocicleta> comboBoxModelo, Marca newValue) {
        List<ModeloMotocicleta> modelos = ModeloMotocicleta.getModeloMotocicleta(newValue);

        comboBoxModelo.getItems().clear();
        comboBoxModelo.getItems().addAll(modelos.toArray(ModeloMotocicleta[]::new));

        comboBoxModelo.setDisable(false);
    }
}

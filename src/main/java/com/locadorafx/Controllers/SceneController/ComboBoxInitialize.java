package com.locadorafx.Controllers.SceneController;

import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloAutomovel;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloMotocicleta;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloVan;
import javafx.scene.control.ComboBox;

import java.util.List;

public class ComboBoxInitialize {
    public static void ComboBoxInitializeModelo(short tipoVeiculo, ComboBox comboBoxModelo, Marca marca) {
        switch (tipoVeiculo){
            case 0: ComboBoxInitializeModeloAutomovel(comboBoxModelo, marca); break;
            case 1: ComboBoxInitializeModeloMotocicleta(comboBoxModelo, marca); break;
            case 2: ComboBoxInitializeModeloVan(comboBoxModelo, marca); break;
        }
    }

    private static void ComboBoxInitializeModeloAutomovel(ComboBox<ModeloAutomovel> comboBoxModelo, Marca newValue) {
        List<ModeloAutomovel> modelos = ModeloAutomovel.getModeloAutomovel(newValue);

        comboBoxModelo.getItems().clear();
        comboBoxModelo.getItems().addAll(modelos.toArray(new ModeloAutomovel[0]));

        comboBoxModelo.setDisable(false);
    }
    private static void ComboBoxInitializeModeloVan(ComboBox<ModeloVan> comboBoxModelo, Marca newValue) {
        List<ModeloVan> modelos = ModeloVan.getModeloVan(newValue);

        comboBoxModelo.getItems().clear();
        comboBoxModelo.getItems().addAll(modelos.toArray(new ModeloVan[0]));

        comboBoxModelo.setDisable(false);
    }
    private static void ComboBoxInitializeModeloMotocicleta(ComboBox<ModeloMotocicleta> comboBoxModelo, Marca newValue) {
        List<ModeloMotocicleta> modelos = ModeloMotocicleta.getModeloMotocicleta(newValue);

        comboBoxModelo.getItems().clear();
        comboBoxModelo.getItems().addAll(modelos.toArray(new ModeloMotocicleta[0]));

        comboBoxModelo.setDisable(false);
    }
}

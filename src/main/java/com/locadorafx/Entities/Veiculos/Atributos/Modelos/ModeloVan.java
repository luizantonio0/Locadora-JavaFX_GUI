package com.locadorafx.Entities.Veiculos.Atributos.Modelos;

import com.locadorafx.Entities.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;

import java.util.ArrayList;
import java.util.List;

public enum ModeloVan {
    //POPULAR
    DUCATO(Marca.Fiat, Categoria.POPULAR), KOMBI(Marca.VW, Categoria.POPULAR),
    //INTERMEDIARIO
    TRANSIT(Marca.Ford, Categoria.INTEMERDIARIO),
    //LUXO
    SPRINTER(Marca.Mercedes, Categoria.LUXO);

    private final Marca marca;
    private final Categoria categoria;

    ModeloVan(Marca marca, Categoria categoria) {
        this.marca = marca;
        this.categoria = categoria;
}
    public Marca getMarca() {
        return marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public static List<ModeloVan> getModeloVan(Marca marca) {
        List<ModeloVan> modelos = new ArrayList<>();
        for (ModeloVan automovel : ModeloVan.values()) {
            if (automovel.marca.equals(marca)) {
                modelos.add(automovel);
            }
        }
        return modelos;
    }
}

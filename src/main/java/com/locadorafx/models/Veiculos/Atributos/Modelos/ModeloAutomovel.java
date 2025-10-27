package com.locadorafx.models.Veiculos.Atributos.Modelos;

import com.locadorafx.models.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.models.Veiculos.Atributos.Marca.Marca;

import java.util.ArrayList;
import java.util.List;

public enum ModeloAutomovel {
    //VW
    GOL(Marca.VW, Categoria.POPULAR), VIRTUS(Marca.VW, Categoria.INTEMERDIARIO), JETTA(Marca.VW, Categoria.LUXO),
    //GM
    ONIX(Marca.GM, Categoria.POPULAR), CRUZE(Marca.GM, Categoria.INTEMERDIARIO), CAMARO(Marca.GM, Categoria.LUXO),
    //FIAR
    UNO(Marca.Fiat, Categoria.POPULAR), DOBLO(Marca.Fiat, Categoria.INTEMERDIARIO), SPYDER(Marca.Fiat, Categoria.LUXO),
    //HONDA
    FIT(Marca.Honda, Categoria.POPULAR), CITY(Marca.Honda, Categoria.INTEMERDIARIO), CIVIC(Marca.Honda, Categoria.LUXO),
    //MERCEDES
    A190(Marca.Mercedes, Categoria.POPULAR), C180(Marca.Mercedes, Categoria.INTEMERDIARIO), C63(Marca.Mercedes, Categoria.LUXO),
    //FORD
    KA(Marca.Ford, Categoria.POPULAR), FIESTA(Marca.Ford, Categoria.INTEMERDIARIO), FUSION(Marca.Ford, Categoria.LUXO),
    //AUDI
    A3(Marca.Audi, Categoria.POPULAR), Q3(Marca.Audi, Categoria.INTEMERDIARIO), RS8(Marca.Audi, Categoria.LUXO);

    private final Marca marca;
    private final Categoria categoria;

    ModeloAutomovel(Marca marca, Categoria categoria) {
        this.marca = marca;
        this.categoria = categoria;
    }

    public static List<String> getModeloAutomovel(Marca marca) {
        List<String> modeloAutomoveis = new ArrayList<>();
        for (ModeloAutomovel automovel : ModeloAutomovel.values()) {
            if (automovel.marca.equals(marca)) {
                modeloAutomoveis.add(automovel.toString());
            }
        }
        return modeloAutomoveis;
    }

    public Marca getMarca() {
        return marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}

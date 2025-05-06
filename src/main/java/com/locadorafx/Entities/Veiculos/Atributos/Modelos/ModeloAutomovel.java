package com.locadorafx.Entities.Veiculos.Atributos.Modelos;

import com.locadorafx.Entities.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;

import java.util.ArrayList;
import java.util.List;

public enum ModeloAutomovel {
    //VW
    GOL(Marca.VW, Categoria.POPULAR), VIRTUS(Marca.VW, Categoria.INTEMERIARIO), JETTA(Marca.VW, Categoria.LUXO),
    //GM
    ONIX(Marca.GM, Categoria.POPULAR), CRUZE(Marca.GM, Categoria.INTEMERIARIO), CAMARO(Marca.GM, Categoria.LUXO),
    //FIAR
    UNO(Marca.Fiat, Categoria.POPULAR), DOBLO(Marca.Fiat, Categoria.INTEMERIARIO), SPYDER(Marca.Fiat, Categoria.LUXO),
    //HONDA
    FIT(Marca.Honda, Categoria.POPULAR), CITY(Marca.Honda, Categoria.INTEMERIARIO), CIVIC(Marca.Honda, Categoria.LUXO),
    //MERCEDES
    A190(Marca.Mercedes, Categoria.POPULAR), C180(Marca.Mercedes, Categoria.INTEMERIARIO), C63(Marca.Mercedes, Categoria.LUXO),
    //FORD
    KA(Marca.Ford, Categoria.POPULAR), FIESTA(Marca.Ford, Categoria.INTEMERIARIO), FUSION(Marca.Ford, Categoria.LUXO),
    //AUDI
    A3(Marca.Audi, Categoria.POPULAR), Q3(Marca.Audi, Categoria.INTEMERIARIO), RS8(Marca.Audi, Categoria.LUXO);

    public final Marca marca;
    public final Categoria categoria;

    ModeloAutomovel(Marca marca, Categoria categoria) {
        this.marca = marca;
        this.categoria = categoria;
    }

    public static List<ModeloAutomovel> getModeloAutomovel(Marca marca) {
        List<ModeloAutomovel> modeloAutomoveis = new ArrayList<>();
        for (ModeloAutomovel automovel : ModeloAutomovel.values()) {
            if (automovel.marca.equals(marca)) {
                modeloAutomoveis.add(automovel);
            }
        }
        return modeloAutomoveis;
    }


}

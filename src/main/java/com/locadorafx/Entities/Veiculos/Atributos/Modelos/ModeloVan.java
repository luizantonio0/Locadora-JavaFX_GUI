package com.locadorafx.Entities.Veiculos.Atributos.Modelos;

import com.locadorafx.Entities.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;

public enum ModeloVan {
    //POPULAR
    DUCATO(Marca.Fiat, Categoria.POPULAR), KOMBI(Marca.VW, Categoria.POPULAR),
    //INTERMEDIARIO
    TRANSIT(Marca.Ford, Categoria.INTEMERIARIO),
    //LUXO
    SPRINTER(Marca.Mercedes, Categoria.LUXO);

    private final Marca marca;
    private final Categoria categoria;

    private ModeloVan(Marca marca, Categoria categoria) {
        this.marca = marca;
        this.categoria = categoria;
}
}

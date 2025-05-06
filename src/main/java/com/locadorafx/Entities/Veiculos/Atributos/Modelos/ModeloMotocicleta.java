package com.locadorafx.Entities.Veiculos.Atributos.Modelos;

import com.locadorafx.Entities.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;

public enum ModeloMotocicleta {
    //HONDA
    CG150(Marca.Honda, Categoria.POPULAR), CB300F(Marca.Honda, Categoria.INTEMERIARIO), CBR1000(Marca.Honda, Categoria.LUXO);
    
    private final Marca marca;
    private final Categoria categoria;

    private ModeloMotocicleta(Marca marca, Categoria categoria) {
        this.marca = marca;
        this.categoria = categoria;
    }
}

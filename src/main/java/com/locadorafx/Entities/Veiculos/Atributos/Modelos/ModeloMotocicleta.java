package com.locadorafx.Entities.Veiculos.Atributos.Modelos;

import com.locadorafx.Entities.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;

import java.util.ArrayList;
import java.util.List;

public enum ModeloMotocicleta {
    //HONDA
    CG150(Marca.Honda, Categoria.POPULAR), CB300F(Marca.Honda, Categoria.INTEMERDIARIO), CBR1000(Marca.Honda, Categoria.LUXO);
    
    private final Marca marca;
    private final Categoria categoria;

    ModeloMotocicleta(Marca marca, Categoria categoria) {
        this.marca = marca;
        this.categoria = categoria;
    }
    public Marca getMarca() {
        return marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public static List<String> getModeloMotocicleta(Marca marca) {
        List<String> modelos = new ArrayList<>();
        for (ModeloMotocicleta automovel : ModeloMotocicleta.values()) {
            if (automovel.marca.equals(marca)) {
                modelos.add(automovel.toString());
            }
        }
        return modelos;
    }
}

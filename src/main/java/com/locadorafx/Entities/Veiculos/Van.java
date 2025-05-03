package com.locadorafx.Entities.Veiculos;

import com.locadorafx.Entities.Veiculos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Modelos.ModeloVan;

import java.time.Year;

public final class Van extends Veiculo {

    public Van(String placa, double valorCompra, Year ano, Marca marca, Estado estado, Categoria categoria, /*int id,*/ ModeloVan modelo) {
        super(placa, valorCompra, ano, marca, estado, categoria/*, id*/);
        this.modelo = modelo;
    }

    private final ModeloVan modelo;

    public ModeloVan getModelo() {
        return modelo;
    }

    //------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Automovel{\nid=-erro-, \nmodelo=%s}\n%s".formatted(/*id,*/ modelo, super.toString());
    }

    @Override
    public double getValorDiariaLocacao(){

        return switch (super.getCategoria()){
            case Categoria.POPULAR -> 200.00;
            case Categoria.INTEMERIARIO -> 400.00;
            case Categoria.LUXO -> 600.00;
        };
    }
}

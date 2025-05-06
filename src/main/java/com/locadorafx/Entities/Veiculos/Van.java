package com.locadorafx.Entities.Veiculos;

import com.locadorafx.Entities.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloVan;

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
    public double getValorDiariaLocacao(int dias){
        if (dias <= 0){return 0;}
        return switch (super.getCategoria()){
            case Categoria.POPULAR -> 200.00 * dias;
            case Categoria.INTEMERIARIO -> 400.00 * dias;
            case Categoria.LUXO -> 600.00 * dias;
        };
    }
}

package com.locadorafx.Models.Veiculos;

import com.locadorafx.Models.Veiculos.Categoria.Categoria;
import com.locadorafx.Models.Veiculos.Estado.Estado;
import com.locadorafx.Models.Veiculos.Marca.Marca;
import com.locadorafx.Models.Veiculos.Modelos.ModeloAutomovel;

import java.time.Year;

public final class Automovel extends Veiculo{


    public Automovel(String placa, double valorCompra, Year ano, Marca marca, Estado estado, Categoria categoria, int id, ModeloAutomovel modelo) {
        super(placa, valorCompra, ano, marca, estado, categoria);
        this.id = id;
        this.modelo = modelo;
    }

    public Automovel(String placa, double valorCompra, Year ano, Marca marca, Estado estado, Categoria categoria, int id) {
        super(placa, valorCompra, ano, marca, estado, categoria);
        this.id = id;
    }

    private final int id;
    private ModeloAutomovel modelo;
    //------------------------------------------------------------------------------------
    public ModeloAutomovel getModelo() {
        return this.modelo;
    }
    //------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Automovel{\nid=%d, \nmodelo=%s}\n%s".formatted(id, modelo, super.toString());
    }

    public double getValorDiariaLocacao(){

         return switch (super.getCategoria()){
            case Categoria.POPULAR -> 100.00;
            case Categoria.INTEMERIARIO -> 300.00;
            case Categoria.LUXO -> 450.00;
         };
    }
}

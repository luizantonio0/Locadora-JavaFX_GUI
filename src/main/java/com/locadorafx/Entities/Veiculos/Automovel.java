package com.locadorafx.Entities.Veiculos;

import com.locadorafx.Entities.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloAutomovel;

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
    @Override
    public Categoria getCategoria() {
        return modelo.categoria;
    }
    @Override
    public Marca getMarca() {
        return modelo.marca;
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

    public double getValorDiariaLocacao(int dias){
        if (dias <= 0){return 0;}
        return switch (super.getCategoria()){
            case Categoria.POPULAR -> 100.00 * dias;
            case Categoria.INTEMERIARIO -> 300.00 * dias;
            case Categoria.LUXO -> 450.00 * dias;
        };
    }
}

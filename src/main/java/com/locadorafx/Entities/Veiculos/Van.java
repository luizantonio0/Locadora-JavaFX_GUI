package com.locadorafx.Entities.Veiculos;

import java.time.Year;

import com.locadorafx.Entities.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloVan;

public final class Van extends Veiculo {

    Van(int id, String placa, double valorCompra, Year ano, Estado estado, ModeloVan modelo) {
        super(id, placa, valorCompra, ano, estado);
        this.modelo = modelo;
    }

    Van(String placa, double valorCompra, Year ano, Estado estado, ModeloVan modelo) {
        super(placa, valorCompra, ano, estado);
        this.modelo = modelo;
        setPrecoDiaria(getValorDiariaLocacao());
    }

    private final ModeloVan modelo;

    //------------------------------------------------------------------------------------
    public ModeloVan getModelo() {
        return modelo;
    }

    @Override
    public Categoria getCategoria() {
        return modelo.getCategoria();
    }
    @Override
    public Marca getMarca() {
        return modelo.getMarca();
    }

    @Override
    public String getModeloToString(){
        return this.modelo.toString();
    }

    //------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Automovel{\nid=%d, \nmodelo=%s}\n%s".formatted(getId(), modelo, super.toString());
    }

    @Override
    public double getValorDiariaLocacao(){

        return switch (getCategoria()){
            case Categoria.POPULAR -> 200.00;
            case Categoria.INTEMERDIARIO -> 400.00;
            case Categoria.LUXO -> 600.00;
        };
    }
    @Override
    public double getValorDiariaLocacao(int dias){
        if (dias <= 0){return 0;}
        return switch (getCategoria()){
            case Categoria.POPULAR -> 200.00 * dias;
            case Categoria.INTEMERDIARIO -> 400.00 * dias;
            case Categoria.LUXO -> 600.00 * dias;
        };
    }
 
}

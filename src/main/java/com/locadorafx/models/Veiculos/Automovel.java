package com.locadorafx.models.Veiculos;

import java.time.Year;

import com.locadorafx.models.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.models.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.models.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.models.Veiculos.Atributos.Modelos.ModeloAutomovel;

public final class Automovel extends Veiculo{

    Automovel(int id, String placa, double valorCompra, Year ano, Estado estado, ModeloAutomovel modelo) {
        super(id, placa, valorCompra, ano, estado);
        this.modelo = modelo;
    }

    Automovel(String placa, double valorCompra, Year ano, Estado estado, ModeloAutomovel modelo) {
        super(placa, valorCompra, ano, estado);
        this.modelo = modelo;
        setPrecoDiaria(getValorDiariaLocacao());
    }
    private final ModeloAutomovel modelo;
    //------------------------------------------------------------------------------------
    public ModeloAutomovel getModelo() {
        return this.modelo;
    }

    @Override
    public Categoria getCategoria() {
        return modelo.getCategoria();
    }
    @Override
    public Marca getMarca() {
        return modelo.getMarca();
    }
    //------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Automovel{\nid=%d, \nmodelo=%s}\n%s".formatted(getId(), modelo, super.toString());
    }

    @Override
    public double getValorDiariaLocacao(){

         return switch (getCategoria()){
            case Categoria.POPULAR -> 100.00;
            case Categoria.INTEMERDIARIO -> 300.00;
            case Categoria.LUXO -> 450.00;
         };
    }

    @Override
    public double getValorDiariaLocacao(int dias){
        if (dias <= 0){return 0;}
        return switch (getCategoria()){
            case Categoria.POPULAR -> 100.00 * dias;
            case Categoria.INTEMERDIARIO -> 300.00 * dias;
            case Categoria.LUXO -> 450.00 * dias;
        };
    }
    @Override
    public String getModeloToString(){
        return this.modelo.toString();
    }
}

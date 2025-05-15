package com.locadorafx.Entities.Veiculos;

import java.time.Year;

import com.locadorafx.Entities.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloMotocicleta;

public final class Motocicleta extends Veiculo{

    Motocicleta(String placa, double valorCompra, Year ano, Estado estado, ModeloMotocicleta modelo) {
        super(placa, valorCompra, ano, estado);
        this.modelo = modelo;
        setPrecoDiaria(getValorDiariaLocacao());
    }

    private final ModeloMotocicleta modelo;
    //------------------------------------------------------------------------------------

    public ModeloMotocicleta getModelo() {
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

    //------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Automovel{\nid=%d, \nmodelo=%s}\n%s".formatted(getId(), modelo, super.toString());
    }
    @Override
    public double getValorDiariaLocacao(){

        return switch (getCategoria()){
            case Categoria.POPULAR -> 70.00;
            case Categoria.INTEMERDIARIO -> 200.00;
            case Categoria.LUXO -> 350.00;
        };
    }
    @Override
    public double getValorDiariaLocacao(int dias){
        if (dias <= 0){return 0;}
        return switch (getCategoria()){
            case Categoria.POPULAR -> 70.00 * dias;
            case Categoria.INTEMERDIARIO -> 200.00 * dias;
            case Categoria.LUXO -> 350.00 * dias;
        };
    }
    @Override
    public String getModeloToString(){
        return this.modelo.toString();
    }
}

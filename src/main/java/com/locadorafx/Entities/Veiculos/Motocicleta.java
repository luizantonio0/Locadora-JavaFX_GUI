package com.locadorafx.Entities.Veiculos;

import com.locadorafx.Entities.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloMotocicleta;

import java.time.Year;

public final class Motocicleta extends Veiculo{

    public Motocicleta(String placa, double valorCompra, Year ano, Estado estado, /*int id,*/ ModeloMotocicleta modelo) {
        super(placa, valorCompra, ano, estado/*, id*/);
        this.modelo = modelo;
    }

    private final ModeloMotocicleta modelo;

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
        return "Automovel{\nid=-erro-, \nmodelo=%s}\n%s".formatted(/*id,*/ modelo, super.toString());
    }
    @Override
    public double getValorDiariaLocacao(){

        return switch (getCategoria()){
            case Categoria.POPULAR -> 70.00;
            case Categoria.INTEMERIARIO -> 200.00;
            case Categoria.LUXO -> 350.00;
        };
    }
    public double getValorDiariaLocacao(int dias){
        if (dias <= 0){return 0;}
        return switch (getCategoria()){
            case Categoria.POPULAR -> 70.00 * dias;
            case Categoria.INTEMERIARIO -> 200.00 * dias;
            case Categoria.LUXO -> 350.00 * dias;
        };
    }
}

package com.locadorafx.Veiculos;

import com.locadorafx.Veiculos.Categoria.Categoria;
import com.locadorafx.Veiculos.Estado.Estado;
import com.locadorafx.Veiculos.Marca.Marca;
import com.locadorafx.Veiculos.Modelos.ModeloAutomovel;

import java.time.LocalDateTime;
import java.time.Year;

public sealed class Automovel extends Veiculo permits Van,Motocicleta{


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



    public double getValorParaVenda(){

        int idadeVeiculo = LocalDateTime.now().getYear() - getAno();
        double tempValorCompra = getValorCompra();

        double valorVenda = tempValorCompra - idadeVeiculo * 0.15 * tempValorCompra;

        if (valorVenda < tempValorCompra * 0.1) {valorVenda = tempValorCompra*0.1;}

        return valorVenda;
    }
    public double getValorDiariaLocacao(){

         return switch (super.getCategoria()){
            case Categoria.POPULAR -> 100.00;
            case Categoria.INTEMERIARIO -> 300.00;
            case Categoria.LUXO -> 450.00;
         };
    }
}

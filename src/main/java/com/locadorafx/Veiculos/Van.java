package Veiculos;

import Veiculos.Categoria.Categoria;
import Veiculos.Estado.Estado;
import Veiculos.Marca.Marca;
import Veiculos.Modelos.ModeloVan;

import java.time.Year;

public final class Van extends Automovel {

    public Van(String placa, double valorCompra, Year ano, Marca marca, Estado estado, Categoria categoria, int id, ModeloVan modelo) {
        super(placa, valorCompra, ano, marca, estado, categoria, id);
        this.modelo = modelo;
    }

    private final ModeloVan modelo;
    //------------------------------------------------------------------------------------

    @Override
    public double getValorDiariaLocacao(){

        return switch (super.getCategoria()){
            case Categoria.POPULAR -> 200.00;
            case Categoria.INTEMERIARIO -> 400.00;
            case Categoria.LUXO -> 600.00;
        };
    }
}

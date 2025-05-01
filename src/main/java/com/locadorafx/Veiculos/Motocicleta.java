package Veiculos;

import Veiculos.Categoria.Categoria;
import Veiculos.Estado.Estado;
import Veiculos.Marca.Marca;
import Veiculos.Modelos.ModeloMotocicleta;

import java.time.Year;

public final class Motocicleta extends Automovel{

    public Motocicleta(String placa, double valorCompra, Year ano, Marca marca, Estado estado, Categoria categoria, int id, ModeloMotocicleta modelo) {
        super(placa, valorCompra,ano, marca, estado, categoria, id);
        this.modelo = modelo;
    }

    private final ModeloMotocicleta modelo;
    //------------------------------------------------------------------------------------

    @Override
    public double getValorDiariaLocacao(){

        return switch (super.getCategoria()){
            case Categoria.POPULAR -> 70.00;
            case Categoria.INTEMERIARIO -> 200.00;
            case Categoria.LUXO -> 350.00;
        };
    }
}

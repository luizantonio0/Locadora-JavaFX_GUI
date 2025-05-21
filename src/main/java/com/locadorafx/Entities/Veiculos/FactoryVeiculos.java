package com.locadorafx.Entities.Veiculos;

import java.time.Year;

import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloAutomovel;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloMotocicleta;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloVan;

public class FactoryVeiculos {

    private FactoryVeiculos() {
    }

    public static Veiculo factoryVeiculo(int id, String placa, double valorCompra, int anoInt, String estadoString, String modeloString, String tipo){
        var ano = Year.of(anoInt);
        var estado = Estado.valueOf(estadoString);
        
        return switch (tipo) {
            case "Automovel"->  new Automovel(id, placa, valorCompra, ano, estado, ModeloAutomovel.valueOf(modeloString));
            case "Van"->  new Van(id, placa, valorCompra, ano, estado, ModeloVan.valueOf(modeloString));
            case "Motocicleta"-> new Motocicleta(id, placa, valorCompra, ano, estado, ModeloMotocicleta.valueOf(modeloString));
            default -> null; 
        };
    }

    public static Veiculo factory(String placa, double valorCompra, Year ano, Estado estado, ModeloAutomovel modelo, ModeloVan modeloVan, ModeloMotocicleta modeloMotocicleta) {
        if (modeloMotocicleta == null && modelo == null) {
            return new Van(placa, valorCompra, ano, estado, modeloVan);
        }
        if (modelo == null && modeloVan == null) {
            return new Motocicleta(placa, valorCompra, ano, estado, modeloMotocicleta);
        }
        return new Automovel(placa, valorCompra, ano, estado, modelo);
    }

}

package com.locadorafx.Entities.Veiculos;

import java.time.Year;

import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloAutomovel;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloMotocicleta;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloVan;

public class FactoryVeiculos {

    private FactoryVeiculos() {
    }

    public static Veiculo factoryVeiculo (String placa, double valorCompra, int anoInt, String estadoString, String modeloString, String tipo){
        var ano = Year.of(anoInt);
        var estado = Estado.valueOf(estadoString);
        
        return switch (tipo) {
            case "Automovel"->  factoryAutomovel(placa, valorCompra, ano, estado, ModeloAutomovel.valueOf(modeloString));
            case "Van"->  factoryVan(placa, valorCompra, ano, estado, ModeloVan.valueOf(modeloString)); 
            case "Motocicleta"-> factoryMotocicleta(placa, valorCompra, ano, estado, ModeloMotocicleta.valueOf(modeloString)); 
            default -> null; 
        };
    }

    public static Veiculo factory(String placa, double valorCompra, Year ano, Estado estado, ModeloAutomovel modelo, ModeloVan modeloVan, ModeloMotocicleta modeloMotocicleta) {
        if (modeloMotocicleta == null && modelo == null) {
            return factoryVan(placa, valorCompra, ano, estado, modeloVan);
        }
        if (modelo == null && modeloVan == null) {
            return factoryMotocicleta(placa, valorCompra, ano, estado, modeloMotocicleta);
        }
        return factoryAutomovel(placa, valorCompra, ano, estado, modelo);
    }

    private static Veiculo factoryAutomovel(String placa, double valorCompra, Year ano, Estado estado,  ModeloAutomovel modelo) {
        return new Automovel(placa, valorCompra, ano, estado, modelo);
    }

    private static Veiculo factoryVan(String placa, double valorCompra, Year ano, Estado estado, ModeloVan modelo) {
        return new Van(placa, valorCompra, ano, estado, modelo);
    }

    private static Veiculo factoryMotocicleta(String placa, double valorCompra, Year ano, Estado estado, ModeloMotocicleta modelo) {
        return new Motocicleta(placa, valorCompra, ano, estado, modelo);
    }
}

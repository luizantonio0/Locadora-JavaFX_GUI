package com.locadorafx.Entities.Veiculos;

import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloAutomovel;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloMotocicleta;
import com.locadorafx.Entities.Veiculos.Atributos.Modelos.ModeloVan;

import java.time.Year;

public class FactoryVeiculos {

    private FactoryVeiculos() {
    }


    public static Veiculo factory(String placa, double valorCompra, Year ano, Estado estado, int id, ModeloAutomovel modelo, ModeloVan modeloVan, ModeloMotocicleta modeloMotocicleta) {
        if (modeloMotocicleta == null && modelo == null) {
            return factoryVan(placa, valorCompra, ano, estado, modeloVan);
        }
        if (modelo == null && modeloVan == null) {
            return factoryMotocicleta(placa, valorCompra, ano, estado, modeloMotocicleta);
        }
        return factoryAutomovel(placa, valorCompra, ano, estado, id, modelo);
    }

    private static Veiculo factoryAutomovel(String placa, double valorCompra, Year ano, Estado estado, int id, ModeloAutomovel modelo) {
        return new Automovel(placa, valorCompra, ano, estado, id, modelo);
    }

    private static Veiculo factoryVan(String placa, double valorCompra, Year ano, Estado estado, /*int id,*/ ModeloVan modelo) {
        return new Van(placa, valorCompra, ano, estado, modelo);
    }

    private static Veiculo factoryMotocicleta(String placa, double valorCompra, Year ano, Estado estado, /*int id,*/ ModeloMotocicleta modelo) {
        return new Motocicleta(placa, valorCompra, ano, estado, modelo);
    }
}

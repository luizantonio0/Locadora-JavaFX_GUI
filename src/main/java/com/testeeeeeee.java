package com;


import com.locadorafx.Entities.Veiculos.FactoryVeiculos;

import com.locadorafx.Entities.Veiculos.Motocicleta;

public class testeeeeeee {
        public static void main(String[] args) {
            
        var veiculo = FactoryVeiculos.factoryVeiculo(0,"AAA0A00",20000.00, 2024, "NOVO", "CG150", "Motocicleta");
        System.out.println(veiculo instanceof Motocicleta);


    }
}
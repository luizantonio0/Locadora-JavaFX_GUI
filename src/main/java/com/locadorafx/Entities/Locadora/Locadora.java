package com.locadorafx.Entities.Locadora;

import com.locadorafx.Entities.Veiculos.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class Locadora {
    private static List<Veiculo> estoque = new ArrayList<>();

    public void adicionarVeiculo(Veiculo veiculo) {
        this.estoque.add(veiculo);
    }

    public static List<Veiculo> getEstoque() {
        return estoque;
    }
}

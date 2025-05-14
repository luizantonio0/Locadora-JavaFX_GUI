package com.locadorafx.Models;

import com.locadorafx.Entities.Locadora.Locadora;
import com.locadorafx.Entities.Veiculos.Veiculo;

public class VeiculoDAO {
    public static void save(Veiculo veiculo) {
        int id = Locadora.getEstoque().size() + 1;
        veiculo.setId(id);
        
        Locadora.adicionarVeiculo(veiculo);
    }
}

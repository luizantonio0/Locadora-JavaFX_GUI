package com.locadorafx.Controllers;

import com.locadorafx.Entities.Veiculos.Automovel;
import com.locadorafx.Entities.Veiculos.Motocicleta;
import com.locadorafx.Entities.Veiculos.Van;
import com.locadorafx.Entities.Veiculos.Veiculo;
import javafx.scene.control.Label;


public class CarregarDadosVeiculo {


    protected static void carregarPreco(Label preco, Veiculo veiculo) {
        preco.setText("R$ %.2f".formatted((veiculo.getValorDiariaLocacao())));
    }
    protected static void carregarCategoria(Label categoria, Veiculo veiculo){
        categoria.setText(veiculo.getCategoria().toString());
    }
    protected static void carregarMarca(Label marca, Veiculo veiculo){
        marca.setText(veiculo.getMarca().toString());
    }
    protected static void carregarModelo(Label modelo, Veiculo veiculo){
        modelo.setText(switch (veiculo) {
            case Automovel a -> a.getModelo().toString();
            case Motocicleta m -> m.getModelo().toString();
            case Van v -> v.getModelo().toString();
        });
    }
}
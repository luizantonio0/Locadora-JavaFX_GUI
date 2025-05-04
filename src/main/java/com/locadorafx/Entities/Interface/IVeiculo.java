package com.locadorafx.Entities.Interface;

import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Veiculos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Locacao.Locacao;

import java.time.LocalDateTime;

public interface IVeiculo {
    void locar(int dias, LocalDateTime data, Cliente cliente);
    void vender();
    void devolver();
    Estado getEstado();
    Marca getMarca();
    Categoria getCategoria();
    Locacao getLocacao();
    String getPlaca();
    int getAno();
    double getValorParaVenda();
    double getValorDiariaLocacao();
    double getValorDiariaLocacao(int dias);


}

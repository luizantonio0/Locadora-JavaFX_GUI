package com.locadorafx.Interface;

import com.locadorafx.Clientes.Cliente;
import com.locadorafx.Veiculos.Marca.Marca;
import com.locadorafx.Veiculos.Estado.Estado;
import com.locadorafx.Veiculos.Categoria.Categoria;
import com.locadorafx.Veiculos.Locacao.Locacao;

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


}

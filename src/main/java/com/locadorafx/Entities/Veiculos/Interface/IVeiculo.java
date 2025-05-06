package com.locadorafx.Entities.Veiculos.Interface;

import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.Entities.Locacao.Locacao;

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

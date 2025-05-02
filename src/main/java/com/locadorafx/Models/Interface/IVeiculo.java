package com.locadorafx.Models.Interface;

import com.locadorafx.Models.Clientes.Cliente;
import com.locadorafx.Models.Veiculos.Marca.Marca;
import com.locadorafx.Models.Veiculos.Estado.Estado;
import com.locadorafx.Models.Veiculos.Categoria.Categoria;
import com.locadorafx.Models.Veiculos.Locacao.Locacao;

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

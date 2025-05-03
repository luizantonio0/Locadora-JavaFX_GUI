package com.locadorafx.Entities.Veiculos.Locacao;

import com.locadorafx.Entities.Clientes.Cliente;

import java.time.LocalDateTime;

public class Locacao {

    public Locacao(int id, int dias, double valor, LocalDateTime data, Cliente cliente) {
        this.id = id;
        this.dias = dias;
        this.valor = valor;
        this.data = data;
        this.cliente = cliente;
    }

    private final int id;
    private final int dias;
    private final double valor;
    private final LocalDateTime data;
    private final Cliente cliente;
    //----------------------------------------------------------------------------

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
    //----------------------------------------------------------------------------
    @Override
    public String toString(){
        return "Locação -> id: %s dias: %s valor: %.2f data: %d/%s/%d \ncliente: %s".formatted(id, dias, valor, data.getDayOfMonth(), data.getMonth(), data.getYear(), cliente);
    }

}

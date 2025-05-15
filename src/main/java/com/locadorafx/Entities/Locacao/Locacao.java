package com.locadorafx.Entities.Locacao;

import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Models.LocacaoDAO;

import java.time.LocalDateTime;

public class Locacao {

    public Locacao(int dias, double valor, LocalDateTime data, Cliente cliente) {
        this.dias = dias;
        this.valor = valor;
        this.data = data;
        this.cliente = cliente;
        LocacaoDAO.save(this);
    }

    private int id;
    private final int dias;
    private final double valor;
    private final LocalDateTime data;
    //Adicioanr referencia ao Id de cliente e carro NO DAO;
    private final Cliente cliente;
    //----------------------------------------------------------------------------

    public int getId() {
        return id;
    }
    public void setId(int id) {
        if(id != 0){
            throw new IllegalStateException("Id deve ser imutavel");
        }
        this.id = id;
    }

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

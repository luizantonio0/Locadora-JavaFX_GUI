package com.locadorafx.models.Locacao;

import com.locadorafx.models.Clientes.Cliente;
import com.locadorafx.models.Veiculos.Veiculo;

import java.time.LocalDate;

public class Locacao {

    //Construtor para buscar dados do banco de dados
    public Locacao(int id, int dias, LocalDate data, Cliente cliente, Veiculo veiculo, double valor, int ativo){
        this.id = id;
        this.dias = dias;
        this.data = data;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.valor = valor;
        this.ativo = ativo == 1;
    }

    //Salvar no banco de dados
    public Locacao(int dias, double valor, LocalDate data, Cliente cliente, Veiculo veiculo) {
        this.dias = dias;
        this.valor = valor;
        this.data = data;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.ativo = true;
    }

    private int id;
    private final int dias;
    private final double valor;
    private final LocalDate data;
    private final Cliente cliente;
    private final Veiculo veiculo;
    private boolean ativo;
    //----------------------------------------------------------------------------

    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public int getDias() {
        return dias;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        if(this.id != 0){
            throw new IllegalStateException("Id deve ser imutavel");
        }
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData() {
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

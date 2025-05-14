package com.locadorafx.Entities.Veiculos;

import java.time.LocalDateTime;
import java.time.Year;

import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Locacao.Locacao;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Placa;
import com.locadorafx.Entities.Veiculos.Interface.IVeiculo;

public abstract sealed class Veiculo implements IVeiculo permits Automovel, Motocicleta, Van {

    protected Veiculo(String placa, double valorCompra, Year ano, Estado estado) {
        //Formato da Placa "XXX-0X00"
        this.placa = new Placa(placa);
        this.valorCompra = valorCompra;
        this.ano = ano;
        this.estado = estado;
    }

    //------------------------------------------------------------------------------------
    private final Placa placa;
    private final double valorCompra;
    private final Year ano;
    private Estado estado;
    private Locacao locacao;
    private int id;
    //------------------------------------------------------------------------------------
    public int getId() {
        return id;
    }
    public Veiculo setId(int id) {
        if (this.id != 0) {
            throw new IllegalStateException("Id já foi definido");
        }
        this.id = id;
        return this;
    }
    @Override
    public Estado getEstado(){
        return this.estado;
    }
    @Override
    public Locacao getLocacao(){
        return this.locacao;
    }
    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }
    public double getValorCompra() {
        return valorCompra;
    }
    @Override
    public String getPlaca(){
        return this.placa.toString();
    }
    @Override
    public int getAno(){
        return this.ano.getValue();
    }
    //------------------------------------------------------------------------------------

    @Override
    public String toString(){
        return "\nVeiculo{\nPlaca: %s\nAno: %s\nLocacao: %s\nEstado: %s".formatted(this.placa, this.ano, this.locacao, this.estado);
    }
    @Override
    public void locar(int dias, LocalDateTime data, Cliente cliente){
        if (!this.getEstado().equals(Estado.DISPONIVEL)){
            throw new IllegalStateException("O Veiculo não está disponivel para locação");
        }

        Locacao locacaoLocal = new Locacao(1, dias, getValorDiariaLocacao()*dias, data, cliente);
        this.setLocacao(locacaoLocal);
        this.estado = Estado.LOCADO;
    }
    @Override
    public void vender(){
        this.locacao = null;
        this.estado = Estado.VENDIDO;
    }
    @Override
    public void devolver(){
        this.locacao = null;
        this.estado = Estado.DISPONIVEL;
    }
    @Override
    public double getValorParaVenda(){

        int idadeVeiculo = LocalDateTime.now().getYear() - this.ano.getValue();
        double tempValorCompra = getValorCompra();

        double valorVenda = tempValorCompra - idadeVeiculo * 0.15 * tempValorCompra;

        if (valorVenda < tempValorCompra * 0.1) {valorVenda = tempValorCompra*0.1;}

        return valorVenda;
    }

}

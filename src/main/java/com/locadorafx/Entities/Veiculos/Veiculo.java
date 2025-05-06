package com.locadorafx.Entities.Veiculos;

import com.locadorafx.Entities.Veiculos.Interface.IVeiculo;
import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Veiculos.Atributos.Placa;
import com.locadorafx.Entities.Veiculos.Atributos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Categoria.Categoria;
import com.locadorafx.Entities.Locacao.Locacao;

import java.time.LocalDateTime;
import java.time.Year;

public abstract sealed class Veiculo implements IVeiculo permits Automovel, Motocicleta, Van {
    //Alterar heranças, Adicionar herança para van e motocicleta


    public Veiculo(String placa, double valorCompra, Year ano, Marca marca, Estado estado, Categoria categoria) {
        //Formato da Placa "XXX-0X00"
        this.placa = new Placa(placa);
        this.valorCompra = valorCompra;
        this.ano = ano;
        this.marca = marca;
        this.estado = estado;
        this.categoria = categoria;
    }

    //------------------------------------------------------------------------------------
    private final Placa placa;
    private final double valorCompra;
    private final Year ano;
    private final Marca marca;
    private Estado estado;
    private final Categoria categoria;
    private Locacao locacao;
    //------------------------------------------------------------------------------------
    public Estado getEstado(){
        return this.estado;
    }
    public Marca getMarca(){
        return this.marca;
    }
    public Categoria getCategoria(){
        return this.categoria;
    }
    public Locacao getLocacao(){
        return this.locacao;
    }
    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }
    public double getValorCompra() {
        return valorCompra;
    }
    public String getPlaca(){
        return this.placa.toString();
    }
    public int getAno(){
        return this.ano.getValue();
    }
    //------------------------------------------------------------------------------------

    @Override
    public String toString(){
        return "\nVeiculo{\nPlaca: %s\nAno: %s\nLocacao: %s\nMarca: %s\nEstado: %s\nCategoria: %s".formatted(this.placa, this.ano, this.locacao, this.marca, this.estado, this.categoria);
    }

    public void locar(int dias, LocalDateTime data, Cliente cliente){
        if (!this.getEstado().equals(Estado.DISPONIVEL)){
            throw new IllegalStateException("O Veiculo não está disponivel para locação");
        }

        Locacao locacao = new Locacao(1, dias, getValorDiariaLocacao()*dias, data, cliente);
        this.setLocacao(locacao);
        this.estado = Estado.LOCADO;
    }

    public void vender(){
        this.locacao = null;
        this.estado = Estado.VENDIDO;
    }

    //Muda estado para DISPONIVEL
    public void devolver(){
        this.locacao = null;
        this.estado = Estado.DISPONIVEL;
    }

    public double getValorParaVenda(){

        int idadeVeiculo = LocalDateTime.now().getYear() - this.ano.getValue();
        double tempValorCompra = getValorCompra();

        double valorVenda = tempValorCompra - idadeVeiculo * 0.15 * tempValorCompra;

        if (valorVenda < tempValorCompra * 0.1) {valorVenda = tempValorCompra*0.1;}

        return valorVenda;
    }

}

package com.locadorafx.Entities.Veiculos;

import java.time.LocalDate;
import java.time.Year;

import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Locacao.Locacao;
import com.locadorafx.Entities.Veiculos.Atributos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Atributos.Placa;
import com.locadorafx.Entities.Veiculos.Interface.IVeiculo;
import com.locadorafx.Models.LocacaoDAO;
import com.locadorafx.Models.VeiculoDAO;

public abstract sealed class Veiculo implements IVeiculo permits Automovel, Motocicleta, Van {

    protected Veiculo(int id, String placa, double valorCompra, Year ano, Estado estado) {
        this.placa = new Placa(placa);
        this.valorCompra = valorCompra;
        this.ano = ano;
        this.estado = estado;
        this.id = id;
    }

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
    private double precoDiaria;
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
    public void setPrecoDiaria(double precoDiaria) {
        if (this.precoDiaria != 0) {
            throw new IllegalStateException("O preço diario não pode ser mudado");
        }
        this.precoDiaria = precoDiaria;
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
    public void locar(int dias, LocalDate data, Cliente cliente){

        if (!this.getEstado().equals(Estado.DISPONIVEL)){
            throw new IllegalStateException("O Veiculo não está disponivel para locação");
        }

        if (dias <= 0){
            throw new IllegalStateException("A data de termino deve ser maior que a data de inicio!");
        }

        Locacao locacaoLocal = new Locacao(dias, getValorDiariaLocacao()*dias, data, cliente, this);
        cliente.setAtivo(true);
        this.estado = Estado.LOCADO;
        this.locacao = locacaoLocal;

        VeiculoDAO.update(this);
        LocacaoDAO.save(locacaoLocal);
    }
    @Override
    public void vender(){
        if(estado.equals(Estado.LOCADO) || estado.equals(Estado.VENDIDO)){
            throw new IllegalStateException("O veiculo Não pode ser vendido, pois está LOCADO ou VENDIDO!!");
        }
        this.estado = Estado.VENDIDO;
    }
    @Override
    public void devolver(){
        if(!estado.equals(Estado.LOCADO)) {
            throw new IllegalStateException("O Veiculo não pode ser devolvido, pois não está LOCADO!!");
        }
        this.locacao.getCliente().setAtivo(false);
        this.locacao.setAtivo(false);
        this.estado = Estado.DISPONIVEL;
        VeiculoDAO.update(this);
        LocacaoDAO.update(this.locacao);
        this.locacao = null;
    }
    @Override
    public double getValorParaVenda(){

        int idadeVeiculo = LocalDate.now().getYear() - this.ano.getValue();
        double tempValorCompra = getValorCompra();

        double valorVenda = tempValorCompra - idadeVeiculo * 0.15 * tempValorCompra;

        if (valorVenda < tempValorCompra * 0.1) {valorVenda = tempValorCompra*0.1;}

        return valorVenda;
    }

}

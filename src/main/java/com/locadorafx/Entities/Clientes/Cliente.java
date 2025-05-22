package com.locadorafx.Entities.Clientes;

public final class Cliente extends Pessoa{

    //Construtor para buscar dados do banco de dados
    public Cliente(int id, String nome, String cpf, String email, String rg, String endereco, int ativo) {
        super(nome, cpf, email, rg, endereco);
        this.id = id;
        this.clienteAtivo = ativo == 1;
    }

    //Salvar no banco de dados
    public Cliente(String nome, String sobrenome, String cpf, String email, String rg, String endereco) {
        super(nome, sobrenome, cpf, email, rg, endereco);
        this.clienteAtivo = false;
    }
    private boolean clienteAtivo;
    private int id;

    //----------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    public Cliente setId(int id) {
        if (this.id != 0) {
            throw new UnsupportedOperationException("ID já foi definido e não pode ser alterado.");
        }
        this.id = id;
        return this;
    }

    public boolean isAtivo() {
        return clienteAtivo;
    }

    public void setAtivo(boolean clienteAtivo) {
        this.clienteAtivo = clienteAtivo;
    }

    //----------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Cliente -> id: -erro- nome: %s sobrenome: %s cpf: %s email: %s RG: %s, Endereço: %s".formatted(/*id,*/ getNome(), getSobrenome(), getCpf(), getEmail(), getRg(), getEndereco());
    }
}

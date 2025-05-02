package com.locadorafx.Models.Clientes;

import com.locadorafx.Models.Clientes.Atributos.CPF;
import com.locadorafx.Models.Clientes.Atributos.Email;
import com.locadorafx.Models.Clientes.Atributos.RG;

public abstract sealed class Pessoa permits Cliente {

    public Pessoa(String nome, String sobrenome, String cpf, String email, String rg, String endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = new CPF(cpf);
        this.email = new Email(email);
        this.rg = new RG(rg);
        this.endereco = endereco;
    }

    //--------------------------------------------------------------------------------------
    //Adicionar sobrenome, RG, Endereço
    private String nome;
    private String sobrenome;
    private final CPF cpf;
    private Email email;
    private final RG rg;
    private String endereco;

    //--------------------------------------------------------------------------------------
    public void setEmail(String email) {
        this.email = new Email(email);
    }
    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf.toString();
    }
    public String getEmail() {
        return email.toString();
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public RG getRg() {
        return rg;
    }
    public String getEndereco() {
        return endereco;
    }
}
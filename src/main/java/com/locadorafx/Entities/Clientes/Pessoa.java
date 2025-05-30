package com.locadorafx.Entities.Clientes;

import com.locadorafx.Entities.Clientes.Atributos.CPF;
import com.locadorafx.Entities.Clientes.Atributos.Email;
import com.locadorafx.Entities.Clientes.Atributos.RG;

public abstract sealed class Pessoa permits Cliente {

    public Pessoa(String nome, String cpf, String email, String rg, String endereco) {
        String sobrenome;
        if (nome.contains(" ")) {
            sobrenome = nome.substring(nome.lastIndexOf(" ") + 1);
            nome = nome.substring(0, nome.lastIndexOf(" "));
        } else {
            sobrenome = nome;
        }
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = new CPF(cpf);
        this.email = new Email(email);
        this.rg = new RG(rg);
        this.endereco = endereco;
    }

    public Pessoa(String nome, String sobrenome, String cpf, String email, String rg, String endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = new CPF(cpf);
        this.email = new Email(email);
        this.rg = new RG(rg);
        this.endereco = endereco;
    }

    //--------------------------------------------------------------------------------------
    private String nome;
    private String sobrenome;
    private final CPF cpf;
    private Email email;
    private final RG rg;
    private String endereco;

    //--------------------------------------------------------------------------------------
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
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
    public String getRg() {
        return rg.toString();
    }
    public String getEndereco() {
        return endereco;
    }
}
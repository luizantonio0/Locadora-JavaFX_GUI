package com.locadorafx.Entities.Locadora;

import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Veiculos.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class Locadora {
    private static List<Veiculo> estoque = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();

    public static void adicionarClientes(Cliente cliente) {
        Locadora.clientes.add(cliente);
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static void adicionarVeiculo(Veiculo veiculo) {
        estoque.add(veiculo);
    }

    public static List<Veiculo> getEstoque() {
        return estoque;
    }
}

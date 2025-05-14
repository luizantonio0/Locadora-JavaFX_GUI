package com.locadorafx.Models;

import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Locadora.Locadora;

public class ClienteDAO {

    public static void adicionarCliente(Cliente cliente) {      
        int id = Locadora.getClientes().size() + 1;
        cliente.setId(id);
        
        Locadora.adicionarClientes(cliente);    
    }

    
}

package com;

import com.locadorafx.DAO.ClienteDAO;
import com.locadorafx.models.Clientes.Cliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.SQLException;

public class TesteDAO {

    @Test
    void saveAndFindCliente(){

        //String nome, String sobrenome, String cpf, String email, String rg, String endereco
        var c1 = new Cliente(
                "João",
                "Silva",
                "39310960094",
                "joao@teste.com",
                "182378792",
                "Um lugar ai"
        );

        ClienteDAO dao = ClienteDAO.getInstance();
        try {
            dao.save(c1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        var c2 = dao.find(3);


        Assertions.assertEquals(c1.getCpf(), c2.getCpf());
    }
}

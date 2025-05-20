package com.locadorafx.Models;

import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Locacao.Locacao;
import com.locadorafx.Entities.Locadora.Locadora;
import com.locadorafx.Entities.Veiculos.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.locadorafx.Controllers.SceneController.AlertMensage.mensagemErro;

public class LocacaoDAO extends DAO{

    public static void save(Locacao locacao) {
        locacao.setId(Locadora.getLocacoes().size()+1);
        Locadora.adicionarLocacao(locacao);
    }
    public static void delete(Locacao locacao) {
        Locadora.getLocacoes().remove(locacao);
    }
}

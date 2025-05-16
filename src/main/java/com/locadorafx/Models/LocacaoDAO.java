package com.locadorafx.Models;

import com.locadorafx.Entities.Locacao.Locacao;
import com.locadorafx.Entities.Locadora.Locadora;

public class LocacaoDAO {
    public static void save(Locacao locacao) {
        locacao.setId(Locadora.getLocacoes().size());
        Locadora.adicionarLocacao(locacao);
    }
    public static void delete(Locacao locacao) {
        Locadora.getLocacoes().remove(locacao);
    }
}

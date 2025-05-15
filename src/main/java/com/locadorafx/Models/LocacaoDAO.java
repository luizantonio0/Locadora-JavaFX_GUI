package com.locadorafx.Models;

import com.locadorafx.Entities.Locacao.Locacao;
import com.locadorafx.Entities.Locadora.Locadora;

public class LocacaoDAO {
    public static void save(Locacao locacao) {
        Locadora.adicionarLocacao(locacao);
    }
}

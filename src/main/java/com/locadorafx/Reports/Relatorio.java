package com.locadorafx.Reports;

import com.locadorafx.models.Locacao.Locacao;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Relatorio {

    String pasta = "Relatorios/";

    static void criarPasta() {

        File diretorio = new File(pasta);

        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    static String[][] getDados(List<Locacao> locacoes){
        String[][] dados = new String[locacoes.size()][7];
        for (int i = 0; i < locacoes.size(); i++) {
            dados[i][0] = String.valueOf(locacoes.get(i).getId());
            dados[i][1] = String.valueOf(locacoes.get(i).getDias());
            dados[i][2] = String.valueOf(locacoes.get(i).getValor());
            dados[i][3] = String.valueOf(locacoes.get(i).getData());
            dados[i][4] = String.valueOf(locacoes.get(i).getCliente().getId());
            dados[i][5] = String.valueOf(locacoes.get(i).getVeiculo().getId());
            dados[i][6] = String.valueOf(locacoes.get(i).isAtivo());
        }
        return dados;
    }

     void gerarRelatorio(String[] cabecalhoString, List<Locacao> locacoes, String nomePlanilha) throws IOException;
}

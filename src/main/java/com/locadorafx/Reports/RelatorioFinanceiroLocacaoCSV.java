package com.locadorafx.Reports;

import com.locadorafx.models.Locacao.Locacao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RelatorioFinanceiroLocacaoCSV implements Relatorio {
    @Override
    public void gerarRelatorio(String[] cabecalhoString, List<Locacao> locacoes, String nomeArquivo) throws IOException {
        Relatorio.criarPasta();

        String caminho = pasta + nomeArquivo + ".csv";
        String[][] dados = Relatorio.getDados(locacoes);

        try (FileWriter writer = new FileWriter(caminho)) {
            // Escreve o cabeçalho
            writer.write(String.join(";", cabecalhoString) + "\n");

            // Escreve os dados
            for (String[] linha : dados) {
                writer.write(String.join(";", linha) + "\n");
            }
        }
    }

}

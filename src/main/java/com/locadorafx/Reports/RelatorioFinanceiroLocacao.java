package com.locadorafx.Reports;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.locadorafx.Entities.Locacao.Locacao;

public class RelatorioFinanceiroLocacao extends RelatorioExcel{

    public RelatorioFinanceiroLocacao(String[] cabecalhoString, List<Locacao> locacoes, String nomePlanilha) {
        String[][] dados = getDados(locacoes);
        try{
            this.workbook = RelatorioExcel.criarPlanilhaComDados(cabecalhoString, dados, nomePlanilha, "Relatorio-Financeiro-Locacao");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private Workbook workbook; 

    public Workbook getWorkbook(){
        return this.workbook;
    }

    private static String[][] getDados(List<Locacao> locacoes){
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

}

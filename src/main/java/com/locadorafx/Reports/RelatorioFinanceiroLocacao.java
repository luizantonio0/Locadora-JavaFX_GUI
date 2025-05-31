package com.locadorafx.Reports;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.locadorafx.Entities.Locacao.Locacao;

public class RelatorioFinanceiroLocacao extends RelatorioExcel{
    @Override
    public void gerarRelatorio(String[] cabecalhoString, List<Locacao> locacoes, String nomePlanilha) {
        String[][] dados = Relatorio.getDados(locacoes);
        try{
            this.workbook = RelatorioExcel.criarPlanilhaComDados(cabecalhoString, dados, nomePlanilha, "Relatorio-Financeiro-Locacao");
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private Workbook workbook;

    public Workbook getWorkbook(){
        return this.workbook;
    }



}

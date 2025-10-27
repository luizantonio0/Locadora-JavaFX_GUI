package com.locadorafx.Reports;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.locadorafx.models.Locacao.Locacao;

public class RelatorioFinanceiroLocacao extends RelatorioExcel{
    @Override
    public void gerarRelatorio(String[] cabecalhoString, List<Locacao> locacoes, String nomePlanilha) throws IOException {
            this.workbook = RelatorioExcel.criarPlanilhaComDados(cabecalhoString, locacoes, nomePlanilha, "Relatorio-Financeiro-Locacao");

    }

    private Workbook workbook;

    public Workbook getWorkbook(){
        return this.workbook;
    }



}

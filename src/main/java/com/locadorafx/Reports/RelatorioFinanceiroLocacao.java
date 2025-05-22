package com.locadorafx.Reports;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;

public class RelatorioFinanceiroLocacao extends RelatorioExcel{

    public RelatorioFinanceiroLocacao(String[] cabecalhoString, String[][] dados, String nomePlanilha) {
        try{
            this.workbook = RelatorioExcel.criarPlanilhaComDados(cabecalhoString, dados, nomePlanilha, "Relatorio-Financeiro-Locacao");
        } catch (IOException e){
        }
    }

    private Workbook workbook; 

    public Workbook getWorkbook(){
        return this.workbook;
    }

}

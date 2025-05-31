package com.locadorafx.Reports;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public abstract class RelatorioExcel implements Relatorio{




    public static Workbook criarPlanilhaComDados(String[] cabecalhoString ,String[][] dados, String nomePlanilha, String nomeArquivo) throws IOException{

        var workbook = WorkbookFactory.create(true);
        var planilha = workbook.createSheet(nomePlanilha);

        var cabecalho = planilha.createRow(0);

        for (int i = 0; i < cabecalhoString.length; i++) {
            cabecalho.createCell(i).setCellValue(cabecalhoString[i]);
        }

        for (int i = 0; i < dados.length; i++) {
            var linha = planilha.createRow(i + 1);
            for (int j = 0; j < dados[i].length; j++) {
                linha.createCell(j).setCellValue(dados[i][j]);
            }
        }

        Relatorio.criarPasta();

        try (var fileOut = new FileOutputStream(pasta + nomeArquivo + ".xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return workbook;
    }
}

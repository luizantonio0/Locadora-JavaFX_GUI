package com.locadorafx.Reports;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.locadorafx.models.Locacao.Locacao;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public abstract class RelatorioExcel implements Relatorio{

    public static Workbook criarPlanilhaComDados(String[] cabecalhoString , List<Locacao> locacoes, String nomePlanilha, String nomeArquivo) throws IOException{

        var workbook = WorkbookFactory.create(true);
        var planilha = workbook.createSheet(nomePlanilha);

        var cabecalho = planilha.createRow(0);

        for (int i = 0; i < cabecalhoString.length; i++) {
            cabecalho.createCell(i).setCellValue(cabecalhoString[i]);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < locacoes.size(); i++) {
            var linha = planilha.createRow(i + 1);
                linha.createCell(0).setCellValue(locacoes.get(i).getId());
                linha.createCell(1).setCellValue(locacoes.get(i).getDias());
                linha.createCell(2).setCellValue(locacoes.get(i).getData().format(formatter));
                linha.createCell(3).setCellValue(locacoes.get(i).getCliente().getId());
                linha.createCell(4).setCellValue(locacoes.get(i).getVeiculo().getId());
                linha.createCell(5).setCellValue(locacoes.get(i).isAtivo());
                linha.createCell(6).setCellValue(locacoes.get(i).getValor());
        }
        planilha.setColumnWidth(5, 12 * 256);
        planilha.autoSizeColumn(2);

        var linha = planilha.createRow(locacoes.size() + 1);
        linha.createCell(cabecalhoString.length - 1).setCellFormula("SUM(G2:G" + (locacoes.size() + 1) + ")");
        linha.createCell(cabecalhoString.length - 2).setCellValue("Total: ");

        Relatorio.criarPasta();

        try (var fileOut = new FileOutputStream(pasta + nomeArquivo + ".xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return workbook;
    }
}

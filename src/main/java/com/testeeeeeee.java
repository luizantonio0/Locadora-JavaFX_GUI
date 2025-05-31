package com;

import com.locadorafx.Models.LocacaoDAO;
import com.locadorafx.Reports.RelatorioFinanceiroLocacao;
import com.locadorafx.Reports.RelatorioFinanceiroLocacaoCSV;

import java.io.IOException;

public class testeeeeeee {
        public static void main(String[] args) throws IOException {


            String[] cabecalho = {"Id", "Dias", "Valor", "Data", "ClienteId", "VeiculoId", "Ativo"};

            RelatorioFinanceiroLocacao relatorio = new RelatorioFinanceiroLocacao();
            RelatorioFinanceiroLocacaoCSV relatorioCSV = new RelatorioFinanceiroLocacaoCSV();
            relatorio.gerarRelatorio(cabecalho, LocacaoDAO.findAll(100), "RelatorioFinanceiroLocacao");
            relatorioCSV.gerarRelatorio(cabecalho, LocacaoDAO.findAll(100), "RelatorioFinanceiroLocacao");
            System.out.println(relatorio.getWorkbook());


    }
}
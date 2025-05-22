package com.locadorafx.Controllers.MascaraFormatador;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.UnaryOperator;

import javafx.scene.control.TextFormatter;

public class MascaraFormatador {
    private static final Locale LOCALE_PT_BR = new Locale("pt", "BR");
    private static final String REGEX_APENAS_DIGITOS = "\\d+";
    
    public static UnaryOperator<TextFormatter.Change> formatarValorMonetario() {
        return change -> {
            String digito = change.getText();

            if (digito.isEmpty()) {
                return change;
            }

            if (!digito.matches(REGEX_APENAS_DIGITOS)) {
                return null;
            }

            String textoAtual = change.getControlText().replaceAll("[^0-9]", "");
            String novoTexto = textoAtual + digito;

            try {
                String valorFormatado = formatarParaValorMonetario(novoTexto);
                change.setText(valorFormatado);
                change.setRange(0, change.getControlText().length());
                return change;
            } catch (NumberFormatException e) {
                return null;
            }
        };
    }

    private static String formatarParaValorMonetario(String texto) {
        long valor = Long.parseLong(texto);
        double valorEmReais = valor / 100.0;
        
        NumberFormat formatador = NumberFormat.getCurrencyInstance(LOCALE_PT_BR);
        formatador.setMaximumFractionDigits(2);
        formatador.setMinimumFractionDigits(2);
        
        return formatador.format(valorEmReais).replace("R$ ", "");
    }
    
    // ... resto do código permanece igual
    public static UnaryOperator<TextFormatter.Change> rolagemTextoPlaca() {
        return change -> {
            StringBuilder novoValor = new StringBuilder(change.getControlText());

            if (change.getText().isEmpty()){
                return change;
            }
            if (!change.getText().matches("^[A-Za-z0-9-]+$")) {
                return null;
            }

            if (novoValor.length() >= 8) {
                novoValor.deleteCharAt(0);
            }

            novoValor.append(change.getText());

            change.setText(novoValor.toString());
            change.setRange(0, change.getControlText().length());
            return change;
        };
    }

    public static UnaryOperator<TextFormatter.Change> rolagemTextoAno() {
        return change -> {
            String newChar = change.getText();
            if (newChar.isEmpty()){
                return change;
            }
            if (!newChar.matches("\\d+")) {
                return null;
            }

            StringBuilder novoValor = new StringBuilder(change.getControlText());

            if (novoValor.length() >= 4){
                novoValor.deleteCharAt(0);
            }

            novoValor.append(newChar);

            change.setText(novoValor.toString());
            change.setRange(0, change.getControlText().length());
            return change;
        };
    }

    public static double getDouble(StringBuilder valor) {
        for (int i = 0; i < valor.length(); i++) {
            if (valor.charAt(i) == '.') {
                valor.deleteCharAt(i);
                i--;
            }
        }

        return Double.parseDouble(valor.toString().replace(",", "."));
    }

}
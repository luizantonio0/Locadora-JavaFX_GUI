package com.locadorafx.controllers.MascaraFormatador;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.UnaryOperator;

import javafx.scene.control.TextFormatter;

public class MascaraFormatador {

    public static UnaryOperator<TextFormatter.Change> formatarCpf() {
        return change -> {
            String novoTexto = change.getControlNewText().replaceAll("\\D", "");
            if (novoTexto.length() > 11) return null;

            if (novoTexto.length() == 11) {
                String formatado = String.format("%s.%s.%s-%s",
                        novoTexto.substring(0, 3),
                        novoTexto.substring(3, 6),
                        novoTexto.substring(6, 9),
                        novoTexto.substring(9, 11));
                change.setText(formatado);
                change.setRange(0, change.getControlText().length());
            } else {
                change.setText(novoTexto);
                change.setRange(0, change.getControlText().length());
            }

            return change;
        };
    }

    public static UnaryOperator<TextFormatter.Change> formatarRg() {
        return change -> {
            String novoTexto = change.getControlNewText().replaceAll("\\D", "");
            if (novoTexto.length() > 9) return null;

            if (novoTexto.length() == 9) {
                String formatado = String.format("%s.%s.%s-%s",
                        novoTexto.substring(0, 2),
                        novoTexto.substring(2, 5),
                        novoTexto.substring(5, 8),
                        novoTexto.substring(8));
                change.setText(formatado);
                change.setRange(0, change.getControlText().length());
            } else {
                change.setText(novoTexto);
                change.setRange(0, change.getControlText().length());
            }

            return change;
        };
    }

    public static UnaryOperator<TextFormatter.Change> formatarValorMonetario() {
        return change -> {
            String digito = change.getText();

            if (digito.isEmpty()) {
                return change;
            }

            if (!digito.matches("\\d+")) {
                return null;
            }

            String textoAtual = change.getControlText().replaceAll("[^0-9]", "");

            String novoTexto = textoAtual + digito;

            try {
                long valor = Long.parseLong(novoTexto);
                double valorDouble = valor / 100.0;

                NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                nf.setMaximumFractionDigits(2);
                nf.setMinimumFractionDigits(2);
                String formatado = nf.format(valorDouble).replace("R$ ", ""); // remove "R$" se quiser só o número

                change.setText(formatado);
                change.setRange(0, change.getControlText().length());

                return change;

            } catch (NumberFormatException e) {
                return null;
            }
        };
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
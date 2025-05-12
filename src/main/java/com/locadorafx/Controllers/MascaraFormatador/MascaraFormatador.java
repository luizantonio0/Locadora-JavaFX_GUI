package com.locadorafx.Controllers.MascaraFormatador;

import javafx.scene.control.TextFormatter;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.UnaryOperator;

public class MascaraFormatador {

    public static UnaryOperator<TextFormatter.Change> rolagemTextoValor() {
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

    public static UnaryOperator<TextFormatter.Change> rolagemTextoPlaca() {
        return change -> {
            StringBuilder novoValor = new StringBuilder(change.getControlText());

            if (change.getText().isEmpty()){
                return change;
            }
            if (!change.getText().matches("^[A-Za-z0-9]+$")) {
                return null;
            }

            if (novoValor.length() >= 7) {
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


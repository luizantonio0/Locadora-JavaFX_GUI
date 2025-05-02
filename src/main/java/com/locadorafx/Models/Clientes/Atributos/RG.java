package com.locadorafx.Models.Clientes.Atributos;

public record RG(String rg) {

    public RG(String rg) {
        if (
                validarRG(rg)) {
            this.rg = rg;
        } else {
            throw new IllegalArgumentException("O RG é inválido");
        }
    }

    @Override
    public String toString() {
        return rg;
    }

    private  boolean validarRG(String rg) {
        // Remove tudo que não for número ou 'X'
        rg = rg.replaceAll("[^0-9Xx]", "");

        // Verifica se o RG tem pelo menos 2 e no máximo 9 caracteres
        if (rg.length() < 2 || rg.length() > 9) return false;

        // Se o RG tiver menos de 9 dígitos, preenche com zeros à esquerda
        rg = String.format("%9s", rg).replace(' ', '0');

        // Verifica se todos os caracteres são iguais (ex: "111111111")
        if (rg.matches("(\\d)\\1{8}")) return false;

        int soma = 0;
        for (int i = 0; i < 8; i++) {
            char c = rg.charAt(i);
            if (!Character.isDigit(c)) return false;
            soma += Character.getNumericValue(c) * (9 - i);
        }

        char digitoVerificador = rg.charAt(8);
        int resto = soma % 11;
        String digitoCalculado;

        if (resto == 10) {
            digitoCalculado = "X";
        } else {
            digitoCalculado = String.valueOf(resto);
        }

        return digitoCalculado.equalsIgnoreCase(String.valueOf(digitoVerificador));
    }

}

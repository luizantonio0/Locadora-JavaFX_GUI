package com.locadorafx.models.Clientes.Atributos;

public record CPF(String cpf) {
    public CPF(String cpf) {
        if (validarCPF(cpf)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("O CPF é inválido");
        }
    }

    @Override
    public String toString() {
        return cpf;
    }

    private  boolean validarCPF(String cpf) {

        cpf = cpf.replaceAll("[.\\-]", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += (cpf.charAt(i) - '0') * (10 - i);
            }
            int firstDigit = 11 - (sum % 11);
            if (firstDigit >= 10) {
                firstDigit = 0;
            }
            if (firstDigit != (cpf.charAt(9) - '0')) {
                return false;
            }

            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += (cpf.charAt(i) - '0') * (11 - i);
            }
            int secondDigit = 11 - (sum % 11);
            if (secondDigit >= 10) {
                secondDigit = 0;
            }
            return secondDigit == (cpf.charAt(10) - '0');

        } catch (NumberFormatException e) {
            return false;
        }
    }

}

package com.locadorafx.models.Veiculos.Atributos;

public record Placa(String placa) {

    public Placa(String placa){
        if (validarPlaca(placa)){
            this.placa = placa.toUpperCase();
        } else {throw new IllegalArgumentException("A placa é invalida");}
    }

    @Override
    public String toString() {
        return placa;
    }

    private String removeHifen(String placa) {
        StringBuilder sb = new StringBuilder(placa);
        sb.deleteCharAt(3);
        return sb.toString();
    }

    public boolean  validarPlaca(String string) {
        //Formato da Placa "XXX-0X00"
        if (string.length() > 7) { string = removeHifen(string); }
        
        if (string.length() != 7) { return false; }

            for (int i = 0; i < 7; i++){
                if (i < 3 || i == 4){
                    if (!Character.isLetter(string.charAt(i))) { return false; }
                } else if (!Character.isDigit(string.charAt(i))) { return false; }
            }
        return true;
    }
}

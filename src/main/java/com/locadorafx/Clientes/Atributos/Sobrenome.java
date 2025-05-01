/*package Clientes.Atributos;

public record Sobrenome(String sobrenome){

    public Sobrenome(String sobrenome){
        this.sobrenome = extrairSobrenome(sobrenome);
    }

    @Override
    public String toString() {return sobrenome;}

    private String extrairSobrenome(String nomeCompleto) {

        String[] partes = nomeCompleto.trim().split("\\s+");

        // Se só houver uma palavra, não há sobrenome
        if (partes.length < 2) {
            return "";
        }

        return partes[partes.length - 1]; // Última palavra é o sobrenome
    }
}*/

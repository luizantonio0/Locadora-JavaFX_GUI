package Clientes;

public final class Cliente extends Pessoa{

    public Cliente(String nome, String cpf, String email, String rg, String endereço, int id) {
        super(nome, cpf, email, rg, endereço);
        this.id = id;
    }
    //--------------------------------------------------------------------------------------
    private final int id;
    //telefone opcional
    private String telefone;
    //--------------------------------------------------------------------------------------

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public String getTelefone() {
        return telefone;
    }
    //----------------------------------------------------------------------------
    public String toString() {
        return "Cliente -> id: %s nome: %s sobrenome: %s cpf: %s email: %s RG: %s, Endereço: %s".formatted(id, getNome(), getSobrenome(), getCpf(), getEmail(), getRg(), getEndereco());
    }
}

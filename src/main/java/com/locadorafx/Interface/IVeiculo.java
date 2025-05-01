package Interface;

import Clientes.Cliente;
import Veiculos.Marca.Marca;
import Veiculos.Estado.Estado;
import Veiculos.Categoria.Categoria;
import Veiculos.Locacao.Locacao;

import java.time.LocalDateTime;

public interface IVeiculo {
    void locar(int dias, LocalDateTime data, Cliente cliente);
    void vender();
    void devolver();
    Estado getEstado();
    Marca getMarca();
    Categoria getCategoria();
    Locacao getLocacao();
    String getPlaca();
    int getAno();
    double getValorParaVenda();
    double getValorDiariaLocacao();


}

package com.locadorafx.Controllers;

import com.locadorafx.App;
import com.locadorafx.Entities.Veiculos.Automovel;
import com.locadorafx.Entities.Veiculos.Categoria.Categoria;
import com.locadorafx.Entities.Veiculos.Estado.Estado;
import com.locadorafx.Entities.Veiculos.Marca.Marca;
import com.locadorafx.Entities.Veiculos.Modelos.ModeloAutomovel;
import com.locadorafx.Entities.Veiculos.Modelos.ModeloMotocicleta;
import com.locadorafx.Entities.Veiculos.Modelos.ModeloVan;
import com.locadorafx.Entities.Veiculos.Motocicleta;
import com.locadorafx.Entities.Veiculos.Van;
import com.locadorafx.Entities.Veiculos.Veiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class locarCarro1Controller {

    @FXML
    private Label TexFieldPrice1;

    @FXML
    private Label TexFieldPrice2;

    @FXML
    private Label TexFieldPrice3;

    //private Label[] TextFieldPrices = {TexFieldPrice1, TexFieldPrice2, TexFieldPrice3};

    @FXML
    private Label TextFieldCategoria1;

    @FXML
    private Label TextFieldCategoria2;

    @FXML
    private Label TextFieldCategoria3;

    @FXML
    private Label TextFieldMarca1;

    @FXML
    private Label TextFieldMarca2;

    @FXML
    private Label TextFieldMarca3;

    @FXML
    private Label TextFieldModelo1;

    @FXML
    private Label TextFieldModelo2;

    @FXML
    private Label TextFieldModelo3;

    private static int ordemCarros = 0;
    public static Veiculo veiculoSelecionado;
    //Lista temporaria para testes substitir por conexao com banco de dados posteriormente
    private static final List<Veiculo> veiculos = new ArrayList<>();

    public void initialize() {
        Veiculo gol = new Automovel("AAA0A00", 50000, Year.of(2010), Marca.VW, Estado.DISPONIVEL, Categoria.POPULAR, 1, ModeloAutomovel.GOL);
        Veiculo virtus = new Automovel("BBB0B00", 100000, Year.of(2010), Marca.VW, Estado.DISPONIVEL, Categoria.INTEMERIARIO, 2, ModeloAutomovel.VIRTUS);
        Veiculo jetta = new Automovel("CCC0C00", 200000, Year.of(2024), Marca.VW, Estado.DISPONIVEL, Categoria.LUXO, 3, ModeloAutomovel.JETTA);

        Veiculo van1 = new Van("AAA0A00", 50000, Year.of(2010), Marca.Fiat, Estado.DISPONIVEL, Categoria.POPULAR,  ModeloVan.DUCATO);
        Veiculo van2 = new Van("BBB0B00", 100000, Year.of(2010), Marca.GM, Estado.DISPONIVEL, Categoria.INTEMERIARIO, ModeloVan.MASTER);
        Veiculo van3 = new Van("CCC0C00", 200000, Year.of(2024), Marca.Mercedes, Estado.DISPONIVEL, Categoria.LUXO,  ModeloVan.SPRINTER);

        Veiculo moto1 = new Motocicleta("AAA0A00", 5000, Year.of(2010), Marca.Honda, Estado.DISPONIVEL, Categoria.POPULAR,  ModeloMotocicleta.CG150);
        Veiculo moto2 = new Motocicleta("BBB0B00", 10000, Year.of(2010), Marca.Honda, Estado.DISPONIVEL, Categoria.INTEMERIARIO, ModeloMotocicleta.CB300F);
        Veiculo moto3 = new Motocicleta("CCC0C00", 20000, Year.of(2024), Marca.Honda, Estado.DISPONIVEL, Categoria.LUXO,  ModeloMotocicleta.CBR1000);

        veiculos.add(gol); veiculos.add(virtus); veiculos.add(jetta);
        veiculos.add(van1); veiculos.add(van2); veiculos.add(van3);
        veiculos.add(moto1); veiculos.add(moto2); veiculos.add(moto3);

        carregarCarros(veiculos);

    }

    @FXML
    void VoltarParaLogin() throws IOException {
        App.setRoot("login-View");
    }

    @FXML
    void abrirLocarVeiculo(){

    }

    @FXML
    void abrirMeusDados() throws IOException {
        App.setRoot("");
    }

    @FXML
    void avancar(ActionEvent event) {
        if (ordemCarros != veiculos.size()-3){
            ordemCarros += 3;
        }

        carregarCarros(veiculos);

    }

    @FXML
    void locarVeiculo(ActionEvent event) throws IOException {
        veiculoSelecionado = veiculos.get(ordemCarros);
        App.setRoot("locarCarro2-View");
    }

    @FXML
    void voltar(ActionEvent event) {
        if (ordemCarros != 0){
            ordemCarros -= 3;
        }
        carregarCarros(veiculos);
    }

    private void carregarCarros(List<Veiculo> veiculos) {

        carregarPreco(TexFieldPrice1, ordemCarros);
        carregarPreco(TexFieldPrice2, ordemCarros+1);
        carregarPreco(TexFieldPrice3, ordemCarros+2);

        carregarCategoria(TextFieldCategoria1, ordemCarros);
        carregarCategoria(TextFieldCategoria2, ordemCarros+1);
        carregarCategoria(TextFieldCategoria3, ordemCarros+2);

        carregarMarca(TextFieldMarca1, ordemCarros);
        carregarMarca(TextFieldMarca2, ordemCarros+1);
        carregarMarca(TextFieldMarca3, ordemCarros+2);

        carregarModelo(veiculos.get(ordemCarros), TextFieldModelo1);
        carregarModelo(veiculos.get(ordemCarros+1), TextFieldModelo2);
        carregarModelo(veiculos.get(ordemCarros+2), TextFieldModelo3);

    }
    private void carregarPreco(Label preco, int index){
        preco.setText("R$ %.2f".formatted((veiculos.get(index).getValorDiariaLocacao())));
    }
    private void carregarCategoria(Label categoria, int index){
        categoria.setText(veiculos.get(index).getCategoria().toString());
    }
    private void carregarMarca(Label marca, int index){
        marca.setText(veiculos.get(index).getMarca().toString());
    }
    private void carregarModelo(Veiculo veiculo, Label modelo){
        modelo.setText(switch (veiculo) {
            case Automovel a -> a.getModelo().toString();
            case Motocicleta m -> m.getModelo().toString();
            case Van v -> v.getModelo().toString();
        });
    }
}


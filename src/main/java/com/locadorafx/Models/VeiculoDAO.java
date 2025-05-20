package com.locadorafx.Models;

import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Veiculos.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.locadorafx.Controllers.SceneController.AlertMensage.mensagemErro;

public class VeiculoDAO extends DAO{

    //OK?
    public static void save (Veiculo veiculo){
        try (var conexao = connect()){

            String sql = "INSERT INTO Veiculo (valorCompra, ano, estado, marca, modelo, categoria, placa) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = setPreparedStatementVeiculo(veiculo, conexao, sql);
            stmt.executeUpdate();
            stmt.close();
            try (var rs = conexao.createStatement().executeQuery("SELECT last_insert_rowid()")) {
                if (rs.next()) {
                    veiculo.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e){
            mensagemErro(e.getMessage());
        }
    }

    private static PreparedStatement setPreparedStatementVeiculo(Veiculo veiculo, Connection conexao, String sql) throws SQLException {

        var stmt = conexao.prepareStatement(sql);
        stmt.setDouble(1, veiculo.getValorCompra());
        stmt.setInt(2, veiculo.getAno());
        stmt.setString(3, veiculo.getEstado().toString());
        stmt.setString(4, veiculo.getMarca().toString());
        stmt.setString(5, veiculo.getModeloToString());
        stmt.setString(6, veiculo.getCategoria().toString());
        stmt.setString(7, veiculo.getPlaca());
        return stmt;
    }

    //OK?
    public static void update (Veiculo veiculo){
        try (var conexao = connect()){
            String sql = "UPDATE Veiculo SET valorCompra = ?,  ano= ?, estado= ?, marca= ?, modelo = ?, categoria = ?, placa = ? WHERE id = ? ";
            var stmt = setPreparedStatementVeiculo(veiculo, conexao, sql);
            stmt.setInt(8, veiculo.getId());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e){
            mensagemErro(e.getMessage());
        }
    }

    public static Cliente get(int id) {
        try (var conexao = connect()){
            var stmt = conexao.prepareStatement("SELECT * FROM Cliente WHERE id = ?");
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (!rs.next()) throw new RuntimeException("Cliente não encontrado");

                return new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("email"), rs.getString("rg"), rs.getString("endereco"));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public static List<Veiculo> find(int quantidade) {
        try (var conexao = connect()){
            var stmt = conexao.prepareStatement("SELECT * FROM Cliente ORDER BY nome LIMIT ?");
            stmt.setInt(1, quantidade);
            try (var rs = stmt.executeQuery()) {
                List<Veiculo> veiculos = new java.util.ArrayList<>();
                while (rs.next()) {
                    //TODO: Problema preciso criar classes filhas com um só metodo
                    //veiculos.add(new FactoryVeiculos.factory(rs.getInt("id"), rs.getDouble("valorCompra"), rs.getInt("ano"), rs.getString("estado"), rs.getString("marca"), rs.getString("modelo"), rs.getString("categoria"), rs.getString("placa"))
                }
                return veiculos;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    //TODO: TESTAR
    public static boolean delete(int id) {
        try (var conexao = connect()){
            var stmt = conexao.prepareStatement("DELETE FROM Cliente WHERE id = ?");
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

//    public static void save(Veiculo veiculo) {
//        int id = Locadora.getEstoque().size() + 1;
//        veiculo.setId(id);
//
//        Locadora.adicionarVeiculo(veiculo);
//    }
}

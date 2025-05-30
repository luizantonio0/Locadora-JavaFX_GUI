package com.locadorafx.Models;

import com.locadorafx.Entities.Clientes.Cliente;
import com.locadorafx.Entities.Locacao.Locacao;
import com.locadorafx.Entities.Veiculos.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static com.locadorafx.Controllers.SceneController.AlertMensage.mensagemErro;
import static com.locadorafx.Entities.Veiculos.FactoryVeiculos.factoryVeiculo;

public class LocacaoDAO extends DAO{

    //OK?
    public static void save (Locacao locacao){
        try (var conexao = connect()){
            String sql = "INSERT INTO Locacao (dias, dataLocacao, idCliente, idVeiculo, valor, ativo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = setPreparedStatementVeiculo(locacao, conexao, sql);
            stmt.executeUpdate();
            stmt.close();
            try (var rs = conexao.createStatement().executeQuery("SELECT last_insert_rowid()")) {
                if (rs.next()) {
                    locacao.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e){
            mensagemErro(e.getMessage());
        }
    }

    private static PreparedStatement setPreparedStatementVeiculo(Locacao locacao, Connection conexao, String sql) throws SQLException {

        var stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, locacao.getDias());
        stmt.setString(2, locacao.getData().toString());
        stmt.setInt(3, locacao.getCliente().getId());
        stmt.setInt(4, locacao.getVeiculo().getId());
        stmt.setDouble(5, locacao.getValor());
        stmt.setInt(6, locacao.isAtivo() ? 1 : 0);
        return stmt;
    }

    //OK?
    public static void update (Locacao locacao){
        try (var conexao = connect()){
            String sql = "UPDATE Locacao SET dias = ?, dataLocacao = ?, idCliente = ?, idVeiculo = ?,  valor = ?, ativo = ? WHERE id = ? ";
            var stmt = setPreparedStatementVeiculo(locacao, conexao, sql);
            stmt.setInt(7, locacao.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e){
            mensagemErro(e.getMessage());
        }
    }

    public static Locacao get(int id) {
        try (var conexao = connect()){
            var stmt = conexao.prepareStatement("""
                    
                    SELECT\s
                                                                Locacao.id AS locacao_id,
                                                                Locacao.dias,
                                                                Locacao.dataLocacao,
                                                                Locacao.valor,
                                                                Locacao.ativo AS locacao_ativo,
                    
                                                                Cliente.id AS cliente_id,
                                                                Cliente.nome,
                                                                Cliente.cpf,
                                                                Cliente.email,
                                                                Cliente.rg,
                                                                Cliente.endereco,
                                                                Cliente.ativo AS cliente_ativo,
                    
                                                                Veiculo.id AS veiculo_id,
                                                                Veiculo.valorCompra,
                                                                Veiculo.ano,
                                                                Veiculo.estado,
                                                                Veiculo.marca,
                                                                Veiculo.modelo,
                                                                Veiculo.categoria,
                                                                Veiculo.placa,
                                                                Veiculo.tipo
                                                            FROM Locacao
                                                            INNER JOIN Cliente ON Locacao.idCliente = Cliente.id
                                                            INNER JOIN Veiculo ON Locacao.idVeiculo = Veiculo.id
                                                            WHERE Locacao.id= ?

                    """);
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (!rs.next()) throw new RuntimeException("Locação não encontrada");
                Cliente cliente = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("rg"),
                        rs.getString("endereco"),
                        rs.getInt("cliente_ativo")
                );

                Veiculo veiculo = factoryVeiculo(
                        rs.getInt("veiculo_id"),
                        rs.getString("placa"),
                        rs.getDouble("valorCompra"),
                        rs.getInt("ano"),
                        rs.getString("estado"),
                        rs.getString("modelo"),
                        rs.getString("tipo")
                );

                return new Locacao(
                        rs.getInt("locacao_id"),
                        rs.getInt("dias"),
                        LocalDate.parse(rs.getString("dataLocacao")),
                        cliente,
                        veiculo,
                        rs.getDouble("valor"),
                        rs.getInt("locacao_ativo")
                );
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public static List<Locacao> find(int quantidade, boolean ativo) {
        try (var conexao = connect()){
            var stmt = conexao.prepareStatement("""
                                                     SELECT\s
                                                                Locacao.id AS locacao_id,
                                                                Locacao.dias,
                                                                Locacao.dataLocacao,
                                                                Locacao.valor,
                                                                Locacao.ativo AS locacao_ativo,
                                                    
                                                                Cliente.id AS cliente_id,
                                                                Cliente.nome,
                                                                Cliente.cpf,
                                                                Cliente.email,
                                                                Cliente.rg,
                                                                Cliente.endereco,
                                                                Cliente.ativo AS cliente_ativo,
                                                    
                                                                Veiculo.id AS veiculo_id,
                                                                Veiculo.valorCompra,
                                                                Veiculo.ano,
                                                                Veiculo.estado,
                                                                Veiculo.marca,
                                                                Veiculo.modelo,
                                                                Veiculo.categoria,
                                                                Veiculo.placa,
                                                                Veiculo.tipo
                                                            FROM Locacao
                                                            INNER JOIN Cliente ON Locacao.idCliente = Cliente.id
                                                            INNER JOIN Veiculo ON Locacao.idVeiculo = Veiculo.id
                                                            WHERE Locacao.ativo = ?
                                                            ORDER BY Locacao.id
                                                            LIMIT ?
                                                    """);
            stmt.setInt(1, ativo ? 1 : 0);
            stmt.setInt(2, quantidade);
            try (var rs = stmt.executeQuery()) {
                List<Locacao> locacoes = new java.util.ArrayList<>();
                while (rs.next()) {

                    Cliente cliente = new Cliente(
                            rs.getInt("cliente_id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("email"),
                            rs.getString("rg"),
                            rs.getString("endereco"),
                            rs.getInt("cliente_ativo")
                    );

                    Veiculo veiculo = factoryVeiculo(
                            rs.getInt("veiculo_id"),
                            rs.getString("placa"),
                            rs.getDouble("valorCompra"),
                            rs.getInt("ano"),
                            rs.getString("estado"),
                            rs.getString("modelo"),
                            rs.getString("tipo")
                    );

                    locacoes.add(new Locacao(
                            rs.getInt("locacao_id"),
                            rs.getInt("dias"),
                            LocalDate.parse(rs.getString("dataLocacao")),
                            cliente,
                            veiculo,
                            rs.getDouble("valor"),
                            rs.getInt("locacao_ativo")
                    ));
                    locacoes.getLast().getVeiculo().setLocacao(locacoes.getLast());
                }
                return locacoes;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static boolean isAtivo(int id) {
        try (var conexao = connect()){
            var stmt = conexao.prepareStatement("SELECT ativo FROM Locacao WHERE id = ?");
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                return rs.getInt("ativo") == 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(int id) {
        try (var conexao = connect()){
            var stmt = conexao.prepareStatement("DELETE FROM Locacao WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}

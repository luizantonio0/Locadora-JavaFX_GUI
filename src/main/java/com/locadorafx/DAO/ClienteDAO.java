package com.locadorafx.DAO;

import com.locadorafx.models.Clientes.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.locadorafx.controllers.SceneController.AlertMensage.mensagemErro;

public class ClienteDAO extends DAO{

    public static void save (Cliente cliente) throws SQLException{
        try (var conexao = connect()){
            String sql = "INSERT INTO Cliente (nome, cpf, email, rg, endereco, ativo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = setPreparedStatementCliente(cliente, conexao, sql);
            stmt.executeUpdate();
            stmt.close();
            try (var rs = conexao.createStatement().executeQuery("SELECT last_insert_rowid()")) {
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                }
            }
        }
    }

    private static PreparedStatement setPreparedStatementCliente(Cliente cliente, Connection conexao, String sql) throws SQLException {
        var stmt = conexao.prepareStatement(sql);
        stmt.setString(1, cliente.getNome() + " " + cliente.getSobrenome());
        stmt.setString(2, cliente.getCpf());
        stmt.setString(3, cliente.getEmail());
        stmt.setString(4, cliente.getRg());
        stmt.setString(5, cliente.getEndereco());
        stmt.setInt(6, cliente.isAtivo()? 1: 0);
        return stmt;
    }

    public static Cliente get(int id) {
        try (var conexao = connect()){
            var stmt = conexao.prepareStatement("SELECT * FROM Cliente WHERE id = ?");
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (!rs.next()) throw new RuntimeException("Cliente não encontrado");

                return new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("email"), rs.getString("rg"), rs.getString("endereco"), rs.getInt("ativo"));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public static boolean isAtivo(int id) {
        try (var conexao = connect()){
            var stmt = conexao.prepareStatement("SELECT ativo FROM Locacao WHERE idCliente = ? AND ativo = 1");
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update (Cliente cliente){
        try (var conexao = connect()){
            String sql = "UPDATE Cliente SET nome = ?,  cpf= ?, email= ?, rg= ?, endereco = ?, ativo = ? WHERE id = ? ";
            var stmt = setPreparedStatementCliente(cliente, conexao, sql);
            stmt.setInt(7, cliente.getId());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e){
            mensagemErro(e.getMessage());
        }
    }

    public static List<Cliente> find(int quantidade) {
        try (var conexao = connect()){
            var stmt = conexao.prepareStatement("""
                                                    SELECT * FROM Cliente
                                                    WHERE cpf != '00000000000'
                                                    ORDER BY nome
                                                    LIMIT ?
                                                   """);
            stmt.setInt(1, quantidade);
            try (var rs = stmt.executeQuery()) {
                List<Cliente> clientes = new java.util.ArrayList<>();
                while (rs.next()) {
                    clientes.add(new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("email"), rs.getString("rg"), rs.getString("endereco"), rs.getInt("ativo")));
                }
                return clientes;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static void delete(int id) throws SQLException {
        try (var conexao = connect()) {
            var stmt = conexao.prepareStatement("UPDATE Cliente SET nome = ?,  cpf= ?, email= ?, rg= ?, endereco = ? WHERE id = ?");
            stmt.setString(1, "Cliente removido");
            stmt.setString(2, "00000000000");
            stmt.setString(3, "Cliente removido");
            stmt.setString(4, "000000000");
            stmt.setString(5, "Cliente removido");
            stmt.setInt(6, id);
            stmt.executeUpdate();
        }
    }
}
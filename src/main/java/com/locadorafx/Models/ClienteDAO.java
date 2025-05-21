package com.locadorafx.Models;

import com.locadorafx.Entities.Clientes.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.locadorafx.Controllers.SceneController.AlertMensage.mensagemErro;

public class ClienteDAO extends DAO{


    public static void save (Cliente cliente){
        try (var conexao = connect()){
            //TODO: Tente fazer sem usar o campo ativo, para verificar se esta ativo: try catch ao tentar apagar dados
            String sql = "INSERT INTO Cliente (nome, cpf, email, rg, endereco) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = setPreparedStatementCliente(cliente, conexao, sql);
            stmt.executeUpdate();
            stmt.close();
            try (var rs = conexao.createStatement().executeQuery("SELECT last_insert_rowid()")) {
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e){
            mensagemErro(e.getMessage());
        }
    }

    private static PreparedStatement setPreparedStatementCliente(Cliente cliente, Connection conexao, String sql) throws SQLException {
        var stmt = conexao.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpf());
        stmt.setString(3, cliente.getEmail());
        stmt.setString(4, cliente.getRg());
        stmt.setString(5, cliente.getEndereco());
        return stmt;
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

    public static void update (Cliente cliente){
        try (var conexao = connect()){
            //TODO: Tente fazer sem usar o campo ativo, para verificar se esta ativo: try catch ao tentar apagar dados
            String sql = "UPDATE Cliente SET nome = ?,  cpf= ?, email= ?, rg= ?, endereco = ? WHERE id = ? ";
            var stmt = setPreparedStatementCliente(cliente, conexao, sql);
            stmt.setInt(6, cliente.getId());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e){
            mensagemErro(e.getMessage());
        }
    }

    public static List<Cliente> find(int quantidade) {
        try (var conexao = connect()){
            var stmt = conexao.prepareStatement("SELECT * FROM Cliente ORDER BY nome LIMIT ?");
            stmt.setInt(1, quantidade);
            try (var rs = stmt.executeQuery()) {
                List<Cliente> clientes = new java.util.ArrayList<>();
                while (rs.next()) {
                    clientes.add(new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("email"), rs.getString("rg"), rs.getString("endereco")));
                }
                return clientes;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
//TODO: TESTAR
    public static boolean delete(int id) {
        try (var conexao = connect()){
            //TODO: VERIFICAR SE ESTÀ ATIVO
           // var stmt = conexao.prepareStatement("UPDATE Cliente SET ativo = 0 WHERE id = ?");
            var stmt = conexao.prepareStatement("DELETE FROM Cliente WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
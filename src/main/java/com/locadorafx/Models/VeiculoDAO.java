package com.locadorafx.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.locadorafx.Controllers.SceneController.AlertMensage.mensagemErro;
import com.locadorafx.Entities.Veiculos.*;

public class VeiculoDAO extends DAO{

    public static void save (Veiculo veiculo){
        try (var conexao = connect()){

            String sql = "INSERT INTO Veiculo (valorCompra, ano, estado, marca, modelo, categoria, placa, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
        stmt.setString(8, switch (veiculo){
            case Motocicleta m -> "Motocicleta";
            case Van v-> "Van";
            case Automovel a -> "Automovel";
        });
        return stmt;
    }

    public static void update (Veiculo veiculo){
        try (var conexao = connect()){
            String sql = "UPDATE Veiculo SET valorCompra = ?,  ano= ?, estado= ?, marca= ?, modelo = ?, categoria = ?, placa = ?, tipo = ? WHERE id = ? ";
            var stmt = setPreparedStatementVeiculo(veiculo, conexao, sql);
            stmt.setInt(9, veiculo.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e){
            mensagemErro(e.getMessage());
        }
    }

    public static Veiculo get(int id) {
        try (var conexao = connect()){
            var stmt = conexao.prepareStatement("SELECT * FROM Veiculo WHERE id = ?");
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (!rs.next()) throw new RuntimeException("Veículo não encontrado");

                return FactoryVeiculos.factoryVeiculo(rs.getInt("id"),
                                                      rs.getString("placa"),
                                                      rs.getDouble("valorCompra"),
                                                      rs.getInt("ano"),
                                                      rs.getString("estado"),
                                                      rs.getString("modelo"),
                                                      rs.getString("tipo"));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static List<Veiculo> find(int quantidade) {
        try (var conexao = connect()){
            var stmt = conexao.prepareStatement("SELECT * FROM Veiculo ORDER BY id LIMIT ?");
            stmt.setInt(1, quantidade);
            try (var rs = stmt.executeQuery()) {
                List<Veiculo> veiculos = new java.util.ArrayList<>();
                while (rs.next()) {
                    veiculos.add(FactoryVeiculos.factoryVeiculo(rs.getInt("id"),
                                                                rs.getString("placa"),
                                                                rs.getDouble("valorCompra"),
                                                                rs.getInt("ano"),
                                                                rs.getString("estado"),
                                                                rs.getString("modelo"),
                                                                rs.getString("tipo")));
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
            var stmt = conexao.prepareStatement("DELETE FROM Veiculo WHERE id = ?");
            stmt.setInt(1, id);

            try (var _ = stmt.executeQuery()) {
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}

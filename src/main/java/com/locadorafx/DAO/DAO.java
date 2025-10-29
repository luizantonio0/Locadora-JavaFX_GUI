package com.locadorafx.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class DAO<T> {
    private static final String url = "jdbc:sqlite:src/main/dataBase/data.db";

    abstract void save(T object) throws SQLException;
    abstract void update(T object) throws SQLException;
    abstract void delete(int id) throws SQLException;
    abstract T find(int id) throws SQLException;
    abstract Iterable<T> findAll(int quantidade) throws SQLException;

    public static Connection connect() throws java.sql.SQLException{
        return DriverManager.getConnection(url);
    }
    public static ResultSet queryId(Connection conexao){
        try (var rs = conexao.createStatement().executeQuery("SELECT last_insert_rowid()")) {
            if (rs.next()) {
                return rs;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Não foi possível obter o id da ultima inserção");
    }
}

package com.locadorafx.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class DAO {
    //TODO: testar o caminho relativo
    private static final String url = "jdbc:sqlite:src/main/dataBase/data.db";

    public static Connection connect() throws java.sql.SQLException{
        return DriverManager.getConnection(url);
    }
    //TODO: testar
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

    public static void close(Connection conexao){
        try {
            conexao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

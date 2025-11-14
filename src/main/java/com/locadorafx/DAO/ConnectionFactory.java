package com.locadorafx.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Properties properties;
    private ConnectionFactory(){

    }
    public static Connection getConnection() throws SQLException {

        try {
            readProperties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String url = properties.getProperty("db.url");
        return DriverManager.getConnection(url);
    }
    private static void readProperties() throws IOException {
        if(properties == null){
            Properties props = new Properties();
            FileInputStream file = new FileInputStream(
                    "./src/main/resources/DataBase.properties");
            props.load(file);
            properties = props;
        }
    }
}

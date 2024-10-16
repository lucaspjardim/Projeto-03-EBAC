package org.br.lucaspjardim.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Author: Lucas Jardim
 */
public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER= "postgres";
    private static final String PASSWORD = "coco123";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Conectando ao banco de dados...");

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Erro ao carregar o driver do PostgreSQL");
        }
    }
}

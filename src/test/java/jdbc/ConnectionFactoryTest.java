package jdbc;

import org.br.lucaspjardim.jdbc.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Author: Lucas Jardim
 */
public class ConnectionFactoryTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = ConnectionFactory.getConnection();
        assertNotNull(connection, "A conexão não pode ser nula.");
    }

    @Test
    public void testConnection() throws SQLException {
        assertTrue(connection.isValid(2), "A conexão deve ser válida");

        System.out.println("Conexão bem sucedida com o banco de dados!");
    }
}

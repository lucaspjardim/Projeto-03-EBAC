package dao;

import org.br.lucaspjardim.dao.cliente.ClienteDAO;
import org.br.lucaspjardim.jdbc.ConnectionFactory;
import org.br.lucaspjardim.model.cliente.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Lucas Jardim
 */

public class ClienteDAOTest {
    private ClienteDAO clienteDAO;
    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = ConnectionFactory.getConnection();
        clienteDAO = new ClienteDAO();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testCadastrarCliente() throws SQLException {
        Date dataNascimento = new Date();
        Cliente cliente = new Cliente("Lucas Jardim", "lucas4@exemplo.com", "99999-9999", "Rua X", dataNascimento);

        clienteDAO.cadastrar(connection, cliente);

        assertNotNull(cliente.getEmail(), "O email do cliente não deve ser nulo.");
        System.out.println("Cliente cadastrado com sucesso no teste.");
    }

    @Test
    public void testAtualizarCliente() throws SQLException {
        Date dataNascimento = new Date();
        Cliente cliente = new Cliente("Lucas Pasquali", "lucas5@gmail.com", "00000-0000", "Rua Y", dataNascimento);
        cliente.setId(9L);

        clienteDAO.atualizar(connection, cliente);
        System.out.println("Cliente atualizado com sucesso no teste.");
    }

    @Test
    public void testDeletarCliente() throws SQLException {
        clienteDAO.deletar(connection, 2L);
        System.out.println("Cliente deletado com sucesso no teste.");
    }

    @Test
    public void testBuscarClientePorId() throws SQLException {
        Cliente cliente = clienteDAO.buscarPorId(connection, 3L);

        assertNotNull(cliente, "O cliente não encontrado!");
        assertEquals(3L, cliente.getId(), "O ID do cliente deve corresponder ao que foi buscado.");

        System.out.println("Cliente buscado: ");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("Endereço: " + cliente.getEndereco());
        System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
    }

    @Test
    public void testBuscarTodosClientes() throws SQLException {
        List<Cliente> clientes = clienteDAO.buscarTodos(connection);

        assertNotNull(clientes, "A lista de clientes não deve ser nula.");
        assertFalse(clientes.isEmpty(), "A lista de clientes não deve estar vazia.");

        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("Endereço: " + cliente.getEndereco());
            System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
            System.out.println("-----------------------------");
        }
    }
}

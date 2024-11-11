package dao;

import org.br.lucaspjardim.dao.cliente.ClienteDAO;
import org.br.lucaspjardim.model.cliente.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Lucas Jardim
 */
public class ClienteDAOTest {
    private ClienteDAO clienteDAO;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("vendasPU");
        entityManager = entityManagerFactory.createEntityManager();
        clienteDAO = new ClienteDAO(entityManager);
    }

    @AfterEach
    public void tearDown() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testCadastrarCliente() {
        Date dataNascimento = new Date();
        Cliente cliente = new Cliente("Lucas Jardim", "lucas4@exaemplo.com", "99999-9999", "Rua X", dataNascimento);

        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        clienteDAO.cadastrar(cliente);
        entityManager.getTransaction().commit();

        assertNotNull(cliente.getEmail(), "O email do cliente não deve ser nulo.");
        System.out.println("Cliente cadastrado com sucesso no teste.");
    }


    @Test
    public void testAtualizarCliente() {
        Date dataNascimento = new Date();
        Cliente cliente = new Cliente("Lucas Pasquali", "lucas5@gmaail.com", "00000-0000", "Rua Y", dataNascimento);
        cliente.setId(1L);

        entityManager.getTransaction().begin();
        clienteDAO.atualizar(cliente);
        entityManager.getTransaction().commit();

        System.out.println("Cliente atualizado com sucesso no teste.");
    }

    @Test
    public void testDeletarCliente() {

        clienteDAO.deletar(6L);

        Cliente cliente = clienteDAO.buscarPorId(6L);
        Assertions.assertNull(cliente, "O cliente deveria ter sido deletado.");

        System.out.println("Cliente deletado com sucesso no teste.");
    }


    @Test
    public void testBuscarClientePorId() {
        Cliente cliente = clienteDAO.buscarPorId(5L);

        assertNotNull(cliente, "O cliente não foi encontrado!");
        assertEquals(5L, cliente.getId(), "O ID do cliente deve corresponder ao que foi buscado.");

        System.out.println("Cliente buscado: ");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("Endereço: " + cliente.getEndereco());
        System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
    }

    @Test
    public void testBuscarTodosClientes() {
        List<Cliente> clientes = clienteDAO.buscarTodos();

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

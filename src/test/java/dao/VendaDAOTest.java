package dao;

import org.br.lucaspjardim.dao.venda.VendaDAO;
import org.br.lucaspjardim.jdbc.ConnectionFactory;
import org.br.lucaspjardim.model.venda.Venda;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VendaDAOTest {
    private VendaDAO vendaDAO;
    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = ConnectionFactory.getConnection();
        vendaDAO = new VendaDAO();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testCadastrarVenda() throws SQLException {
        Venda venda = new Venda(1L, 1L, 10, 5.0, new Date());

        vendaDAO.cadastrarVenda(connection, venda);

        System.out.println("Venda cadastrada com sucesso!");
    }

    @Test
    public void testAtualizarVenda() throws SQLException {
        Venda venda = new Venda(1L, 1L, 100, 5.0, new Date());
        venda.setId(1L);

        vendaDAO.atualizarVenda(connection, venda);

        System.out.println("Venda atualizada com sucesso!");
    }

    @Test
    public void testDeletarVenda() throws SQLException {
        vendaDAO.deletarVenda(connection, 1L);

        System.out.println("Venda deletada com sucesso!");
    }

    @Test
    public void testBuscarVendaPorId() throws SQLException {
        Venda venda = vendaDAO.buscarVenda(connection, 2L);

        assertNotNull(venda, "Venda não encontrada!");
        assertEquals(2L, venda.getId(), "ID da venda não corresponde.");

        System.out.println("Venda buscada: ");
        System.out.println("ID: " + venda.getId());
        System.out.println("ID Cliente: " + venda.getIdCliente());
        System.out.println("ID Produto: " + venda.getIdProduto());
        System.out.println("Quantidade: " + venda.getQuantidade());
        System.out.println("Valor Total: " + venda.getValorTotal());
        System.out.println("Data da Venda: " + venda.getDataVenda());
    }

    @Test
    public void testBuscarTodasVendas() throws SQLException {
        List<Venda> vendas = vendaDAO.buscarTodasVendas(connection);

        assertNotNull(vendas, "A lista de vendas não deve ser nula.");
        assertFalse(vendas.isEmpty(), "A lista de vendas não deve estar vazia.");

        for (Venda venda : vendas) {
            System.out.println("ID: " + venda.getId());
            System.out.println("ID Cliente: " + venda.getIdCliente());
            System.out.println("ID Produto: " + venda.getIdProduto());
            System.out.println("Quantidade: " + venda.getQuantidade());
            System.out.println("Valor Total: " + venda.getValorTotal());
            System.out.println("Data da Venda: " + venda.getDataVenda());
            System.out.println("-----------------------------");
        }
    }

}

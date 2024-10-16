package dao;

import org.br.lucaspjardim.dao.produto.ProdutoDAO;
import org.br.lucaspjardim.jdbc.ConnectionFactory;
import org.br.lucaspjardim.model.produto.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Lucas Jardim
 */
public class ProdutoDAOTest {
    private ProdutoDAO produtoDAO;
    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = ConnectionFactory.getConnection();
        produtoDAO = new ProdutoDAO();
    }
    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testCadastrarProduto() throws SQLException {
        Produto produto = new Produto("Produto Teste", "Descrição do Produto", 29.99);

        produtoDAO.cadastrarProduto(connection, produto);

        System.out.println("Produto cadastrado com sucesso!");
    }

    @Test
    public void testAtualizarProduto() throws SQLException {
        Produto produto = new Produto("Produto atualizado", "Descrição atualizada", 10.00);
        produto.setId(1L);

        produtoDAO.atualizarProduto(connection, produto);
        System.out.println("Produto atualizado com sucesso!");
    }

    @Test
    public void testDeletarProduto() throws SQLException {
        produtoDAO.deletarProduto(connection, 1L);

        System.out.println("Produto deletado!");
    }

    @Test
    public void testBuscarProdutoPorId() throws SQLException {
        Produto produto = produtoDAO.buscarProduto(connection, 2L);

        assertNotNull(produto, "Produto não encontrado!");

        assertEquals(2L, produto.getId(), "Insira um ID Válido");
        System.out.println("Produto buscado: ");
        System.out.println("ID: "+ produto.getId());
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Descrição: " + produto.getDescricao());
        System.out.println("Preço: " + produto.getPreco());
    }

    @Test
    public void testBuscarTodosClientes() throws SQLException {
        List<Produto> produtos = produtoDAO.buscarTodosProdutos(connection);

        assertNotNull(produtos, "A lista de produtos não deve ser nula.");
        assertFalse(produtos.isEmpty(), "A lista de produtos não deve estar vazia.");

        for (Produto produto : produtos) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Email: " + produto.getDescricao());
            System.out.println("Telefone: " + produto.getPreco());
            System.out.println("-----------------------------");
        }
    }
}

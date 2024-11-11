package dao;

import org.br.lucaspjardim.dao.produto.ProdutoDAO;
import org.br.lucaspjardim.model.produto.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Lucas Jardim
 */
public class ProdutoDAOTest {
    private ProdutoDAO produtoDAO;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("vendasPU");
        entityManager = entityManagerFactory.createEntityManager();
        produtoDAO = new ProdutoDAO(entityManager);
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
    public void testCadastrarProduto() {
        Produto produto = new Produto("Produto Teste", "Descrição do Produto", 29.99, "X", 100);

        entityManager.getTransaction().begin();
        produtoDAO.cadastrar(produto);
        entityManager.getTransaction().commit();

        System.out.println("Produto cadastrado com sucesso!");
    }

    @Test
    public void testAtualizarProduto() {
        Produto produto = new Produto("Produto atualizado", "Descrição atualizada", 10.00,"Y", 10);
        produto.setId(1L);

        entityManager.getTransaction().begin();
        produtoDAO.atualizar(produto);
        entityManager.getTransaction().commit();

        System.out.println("Produto atualizado com sucesso!");
    }

    @Test
    public void testDeletarProduto() {
        entityManager.getTransaction().begin();
        produtoDAO.deletar(1L);
        entityManager.getTransaction().commit();

        System.out.println("Produto deletado!");
    }

    @Test
    public void testBuscarProdutoPorId() {
        Produto produto = produtoDAO.buscarPorId(2L);

        assertNotNull(produto, "Produto não encontrado!");
        assertEquals(2L, produto.getId(), "Insira um ID Válido");

        System.out.println("Produto buscado: ");
        System.out.println("ID: " + produto.getId());
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Descrição: " + produto.getDescricao());
        System.out.println("Preço: " + produto.getPreco());
        System.out.println("Categoria: " + produto.getCategoria());
        System.out.println("Estoque: " + produto.getEstoque());
    }

    @Test
    public void testBuscarTodosProdutos() {
        List<Produto> produtos = produtoDAO.buscarTodos();

        assertNotNull(produtos, "A lista de produtos não deve ser nula.");
        assertFalse(produtos.isEmpty(), "A lista de produtos não deve estar vazia.");

        for (Produto produto : produtos) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Preço: " + produto.getPreco());
            System.out.println("Categoria: " + produto.getCategoria());
            System.out.println("Estoque: " + produto.getEstoque());
            System.out.println("-----------------------------");
        }
    }
}

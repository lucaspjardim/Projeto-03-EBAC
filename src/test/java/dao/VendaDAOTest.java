package dao;

import org.br.lucaspjardim.dao.cliente.ClienteDAO;
import org.br.lucaspjardim.dao.produto.ProdutoDAO;
import org.br.lucaspjardim.dao.venda.VendaDAO;
import org.br.lucaspjardim.model.cliente.Cliente;
import org.br.lucaspjardim.model.produto.Produto;
import org.br.lucaspjardim.model.venda.Venda;
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

public class VendaDAOTest {
    private VendaDAO vendaDAO;
    private ProdutoDAO produtoDAO;
    private ClienteDAO clienteDAO;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("vendasPU");
        entityManager = entityManagerFactory.createEntityManager();
        produtoDAO = new ProdutoDAO(entityManager);
        clienteDAO = new ClienteDAO(entityManager);
        vendaDAO = new VendaDAO(entityManager, produtoDAO);
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
    public void testCadastrarVenda() {
        Long idCliente = 6L;
        Long idProduto = 2L;
        int quantidadeVenda = 10;

        Cliente cliente = clienteDAO.buscarPorId(idCliente);
        Produto produto = produtoDAO.buscarPorId(idProduto);

        Assertions.assertNotNull(cliente, "Cliente com ID " + idCliente + " não encontrado.");
        Assertions.assertNotNull(produto, "Produto com ID " + idProduto + " não encontrado.");

        int estoqueAntes = produto.getEstoque();

        Venda venda = new Venda(cliente, produto, quantidadeVenda, produto.getPreco() * quantidadeVenda, new Date());

        entityManager.getTransaction().begin();
        vendaDAO.cadastrarVenda(venda);
        entityManager.getTransaction().commit();

        entityManager.refresh(produto);
        int estoqueDepois = produtoDAO.getEstoque(idProduto);

        assertEquals(estoqueAntes - quantidadeVenda, estoqueDepois, "O estoque não foi atualizado corretamente após a venda.");

        System.out.println("Venda cadastrada com sucesso!");
        System.out.println("Quantidade no estoque agora: " + estoqueDepois);
    }

    @Test
    public void testAtualizarVenda() {
        Long idCliente = 5L;
        Long idProduto = 2L;
        Long idVenda = 2L;

        Cliente cliente = entityManager.find(Cliente.class, idCliente);
        Produto produto = entityManager.find(Produto.class, idProduto);
        Venda venda = vendaDAO.buscarPorId(idVenda);

        Assertions.assertNotNull(cliente, "Cliente com ID " + idCliente + " não encontrado.");
        Assertions.assertNotNull(produto, "Produto com ID " + idProduto + " não encontrado.");
        Assertions.assertNotNull(venda, "Venda com ID " + idVenda + " não encontrada.");

        venda.setCliente(cliente);
        venda.setProduto(produto);
        venda.setQuantidade(20);
        venda.setValorTotal(produto.getPreco() * venda.getQuantidade());
        venda.setDataVenda(new Date());

        entityManager.getTransaction().begin();
        vendaDAO.atualizar(venda);
        entityManager.getTransaction().commit();

        System.out.println("Venda atualizada com sucesso!");
    }

    @Test
    public void testDeletarVenda() {
        entityManager.getTransaction().begin();
        vendaDAO.deletar(2L);
        entityManager.getTransaction().commit();

        System.out.println("Venda deletada com sucesso!");
    }

    @Test
    public void testBuscarVendaPorId() {
        Venda venda = vendaDAO.buscarPorId(3L);

        assertNotNull(venda, "Venda não encontrada!");
        assertEquals(3L, venda.getId(), "ID da venda não corresponde.");

        System.out.println("Venda buscada: ");
        System.out.println("ID: " + venda.getId());
        System.out.println("ID Produto: " + venda.getProduto().getId());
        System.out.println("Nome do produto: " + venda.getProduto().getNome());
        System.out.println("Quantidade: " + venda.getQuantidade());
        System.out.println("Valor Total: " + venda.getValorTotal());
        System.out.println("Data da Venda: " + venda.getDataVenda());
    }

    @Test
    public void testBuscarTodasVendas() {
        List<Venda> vendas = vendaDAO.buscarTodos();

        assertNotNull(vendas, "A lista de vendas não deve ser nula.");
        assertFalse(vendas.isEmpty(), "A lista de vendas não deve estar vazia.");

        for (Venda venda : vendas) {
            System.out.println("ID: " + venda.getId());
            System.out.println("ID Produto: " + venda.getProduto().getId());
            System.out.println("Nome do produto: " + venda.getProduto().getNome());
            System.out.println("Quantidade: " + venda.getQuantidade());
            System.out.println("Valor Total: " + venda.getValorTotal());
            System.out.println("Data da Venda: " + venda.getDataVenda());
            System.out.println("-----------------------------");
        }
    }
}

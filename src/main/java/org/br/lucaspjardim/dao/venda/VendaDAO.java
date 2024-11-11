package org.br.lucaspjardim.dao.venda;

import jakarta.persistence.EntityManager;
import org.br.lucaspjardim.dao.generic.GenericDAO;
import org.br.lucaspjardim.dao.produto.ProdutoDAO;
import org.br.lucaspjardim.model.venda.Venda;
import org.br.lucaspjardim.model.produto.Produto;

/**
 * Author: Lucas Jardim
 */
public class VendaDAO extends GenericDAO<Venda> implements IVendaDAO {

    private final EntityManager entityManager;
    private final ProdutoDAO produtoDAO;

    public VendaDAO(EntityManager entityManager, ProdutoDAO produtoDAO) {
        super(entityManager, Venda.class);
        this.entityManager = entityManager;
        this.produtoDAO = produtoDAO;
    }

    /**
     * Cadastra uma nova venda e atualiza o estoque do produto.
     * @param venda a venda a ser cadastrada.
     */
    public void cadastrarVenda(Venda venda) {
        entityManager.persist(venda);

        Produto produto = venda.getProduto();
        int novoEstoque = produto.getEstoque() - venda.getQuantidade();
        produtoDAO.atualizarEstoque(produto.getId(), novoEstoque);
    }
}

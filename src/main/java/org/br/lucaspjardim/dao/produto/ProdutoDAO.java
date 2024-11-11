package org.br.lucaspjardim.dao.produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import org.br.lucaspjardim.dao.generic.GenericDAO;
import org.br.lucaspjardim.model.produto.Produto;

/**
 * Author: Lucas Jardim
 */
public class ProdutoDAO extends GenericDAO<Produto> implements IProdutoDAO {

    private final EntityManager entityManager;

    public ProdutoDAO(EntityManager entityManager) {
        super(entityManager, Produto.class); // Passando EntityManager e Produto.class para o construtor do GenericDAO
        this.entityManager = entityManager;
    }

    /**
     * Obtém o estoque de um produto pelo ID.
     * @param idProduto o ID do produto.
     * @return a quantidade em estoque do produto.
     */
    public int getEstoque(Long idProduto) {
        try {
            return entityManager.createQuery("SELECT p.estoque FROM Produto p WHERE p.id = :id", Integer.class)
                    .setParameter("id", idProduto)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new IllegalArgumentException("Produto não encontrado com ID: " + idProduto);
        }
    }

    /**
     * Atualiza o estoque de um produto.
     * @param idProduto o ID do produto.
     * @param quantidade nova quantidade de estoque.
     */
    public void atualizarEstoque(Long idProduto, int quantidade) {
        Produto produto = entityManager.find(Produto.class, idProduto);
        if (produto != null) {
            produto.setEstoque(quantidade);
            entityManager.merge(produto);
        }
    }

}

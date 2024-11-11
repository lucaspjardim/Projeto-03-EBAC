package org.br.lucaspjardim.dao.produto;

import org.br.lucaspjardim.model.produto.Produto;

public interface IProdutoDAO {
    /**
     * Obtém o estoque de um produto pelo ID.
     * @param idProduto o ID do produto.
     * @return a quantidade em estoque do produto.
     */
    int getEstoque(Long idProduto);

    /**
     * Atualiza o estoque de um produto.
     * @param idProduto o ID do produto.
     * @param quantidade nova quantidade de estoque.
     */
    void atualizarEstoque(Long idProduto, int quantidade);
}

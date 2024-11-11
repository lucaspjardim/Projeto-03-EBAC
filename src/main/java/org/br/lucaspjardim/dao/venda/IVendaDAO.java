package org.br.lucaspjardim.dao.venda;

import org.br.lucaspjardim.model.venda.Venda;

/**
 * Author: Lucas Jardim
 */
public interface IVendaDAO {
    /**
     * Cadastra uma nova venda e atualiza o estoque do produto associado.
     * @param venda a venda a ser cadastrada.
     */
    void cadastrarVenda(Venda venda);
}

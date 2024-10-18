package org.br.lucaspjardim.dao.venda;

import org.br.lucaspjardim.model.venda.Venda;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 * Author: Lucas Jardim
 */
public interface IVendaDAO {
    void cadastrarVenda(Connection connection, Venda venda) throws SQLException;
    void atualizarEstoque(Connection connection, Long produtoId, int quantidadeVendida) throws SQLException;

}

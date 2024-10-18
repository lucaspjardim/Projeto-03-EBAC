package org.br.lucaspjardim.dao.produto;

import org.br.lucaspjardim.model.cliente.Cliente;
import org.br.lucaspjardim.model.produto.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author: Lucas Jardim
 */
public interface IProdutoDAO {
    int getEstoque(Connection connection, Long idProduto) throws SQLException;
}

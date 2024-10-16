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
    void cadastrarProduto(Connection connection, Produto produto) throws SQLException;
    void atualizarProduto(Connection connection, Produto produto) throws SQLException;
    void deletarProduto(Connection connection, Long id) throws SQLException;
    Produto buscarProduto(Connection connection, Long id) throws SQLException;
    List<Produto> buscarTodosProdutos(Connection connection) throws SQLException;
}

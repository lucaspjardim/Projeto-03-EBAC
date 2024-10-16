package org.br.lucaspjardim.dao.cliente;

import org.br.lucaspjardim.model.cliente.Cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author: Lucas Jardim
 */
public interface IClientDAO {
    void cadastroCliente(Connection connection, Cliente cliente) throws SQLException;
    void atualizarCliente(Connection connection, Cliente cliente) throws SQLException;
    void deletarCliente(Connection connection, Long id) throws SQLException;
    Cliente buscarCliente(Connection connection, Long id) throws SQLException;
    List<Cliente> buscarTodosClientes(Connection connection) throws SQLException;
}

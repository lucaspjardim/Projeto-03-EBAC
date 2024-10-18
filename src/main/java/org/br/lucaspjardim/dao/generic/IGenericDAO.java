package org.br.lucaspjardim.dao.generic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author: Lucas Jardim
 */
public interface IGenericDAO<T> {
    void cadastrar(Connection connection, T entity) throws SQLException;
    void atualizar(Connection connection, T entity) throws SQLException;
    void deletar(Connection connection, Long id) throws SQLException;
    T buscarPorId(Connection connection, Long id) throws SQLException;
    List<T> buscarTodos(Connection connection) throws SQLException;
}

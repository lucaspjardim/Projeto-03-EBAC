package org.br.lucaspjardim.dao.cliente;

import org.br.lucaspjardim.dao.generic.GenericDAO;
import org.br.lucaspjardim.model.cliente.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: Lucas Jardim
 */
public class ClienteDAO extends GenericDAO<Cliente> {

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO cliente (nome, email, telefone) VALUES (?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE cliente SET nome = ?, email = ?, telefone = ? WHERE id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM cliente WHERE id = ?";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM cliente WHERE id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM cliente";
    }

    @Override
    protected void setInsertParameters(PreparedStatement preparedStatement, Cliente entity) throws SQLException {
        preparedStatement.setString(1, entity.getNome());
        preparedStatement.setString(2, entity.getEmail());
        preparedStatement.setString(3, entity.getTelefone());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, Cliente entity) throws SQLException {
        preparedStatement.setString(1, entity.getNome());
        preparedStatement.setString(2, entity.getEmail());
        preparedStatement.setString(3, entity.getTelefone());
        preparedStatement.setLong(4, entity.getId());
    }

    @Override
    protected Cliente mapRow(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente(
                resultSet.getString("nome"),
                resultSet.getString("email"),
                resultSet.getString("telefone")
        );
        cliente.setId(resultSet.getLong("id"));
        return cliente;
    }
}

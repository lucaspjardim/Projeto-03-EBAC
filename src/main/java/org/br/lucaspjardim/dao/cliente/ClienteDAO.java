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
        return "INSERT INTO cliente (nome, email, telefone, endereco, data_nascimento) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE cliente SET nome = ?, email = ?, telefone = ?, endereco = ?, data_nascimento = ? WHERE id = ?";
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
        preparedStatement.setString(4, entity.getEndereco());
        preparedStatement.setDate(5, new java.sql.Date(entity.getDataNascimento().getTime()));
    }

    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, Cliente entity) throws SQLException {
        preparedStatement.setString(1, entity.getNome());
        preparedStatement.setString(2, entity.getEmail());
        preparedStatement.setString(3, entity.getTelefone());
        preparedStatement.setString(4, entity.getEndereco()); // Adicione este campo
        preparedStatement.setDate(5, new java.sql.Date(entity.getDataNascimento().getTime()));
        preparedStatement.setLong(6, entity.getId()); // Ajuste o índice para 6
    }


    @Override
    protected Cliente mapRow(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente(
                resultSet.getString("nome"),
                resultSet.getString("email"),
                resultSet.getString("telefone"),
                resultSet.getString("endereco"),
                resultSet.getDate("data_nascimento")
        );
        cliente.setId(resultSet.getLong("id"));
        return cliente;
    }
}

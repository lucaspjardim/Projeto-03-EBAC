package org.br.lucaspjardim.dao.generic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Lucas Jardim
 */
public abstract class GenericDAO<T> implements IGenericDAO<T> {
    protected abstract String getInsertQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();
    protected abstract String getSelectQuery();
    protected abstract String getSelectAllQuery();
    protected abstract T mapRow(ResultSet resultSet) throws SQLException;

    protected abstract void setInsertParameters(PreparedStatement preparedStatement, T entity) throws SQLException;
    protected abstract void setUpdateParameters(PreparedStatement preparedStatement, T entity) throws SQLException;

    @Override
    public void cadastrar(Connection connection, T entity) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery())) {
            setInsertParameters(preparedStatement, entity);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void atualizar(Connection connection, T entity) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery())) {
            setUpdateParameters(preparedStatement, entity);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deletar(Connection connection, Long id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery())) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public T buscarPorId(Connection connection, Long id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getSelectQuery())) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapRow(resultSet);
            }
            return null;
        }

    }

    @Override
    public List<T> buscarTodos(Connection connection) throws SQLException {
        List<T> entities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(getSelectAllQuery())) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entities.add(mapRow(resultSet));
            }
        }
        return entities;
    }
}

package org.br.lucaspjardim.dao.produto;

import org.br.lucaspjardim.dao.generic.GenericDAO;
import org.br.lucaspjardim.model.produto.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: Lucas Jardim
 */
public class ProdutoDAO extends GenericDAO<Produto> {

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO produto (nome, descricao, preco) VALUES (?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE produto SET nome = ?, descricao = ?, preco = ? WHERE id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM produto WHERE id = ?";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM produto WHERE id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM produto";
    }

    @Override
    protected Produto mapRow(ResultSet resultSet) throws SQLException {
        Produto produto = new Produto(
                resultSet.getString("nome"),
                resultSet.getString("descricao"),
                resultSet.getDouble("preco")
        );
        produto.setId(resultSet.getLong("id"));
        return produto;
    }

    @Override
    protected void setInsertParameters(PreparedStatement preparedStatement, Produto entity) throws SQLException {
        preparedStatement.setString(1, entity.getNome());
        preparedStatement.setString(2, entity.getDescricao());
        preparedStatement.setDouble(3, entity.getPreco());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, Produto entity) throws SQLException {
        preparedStatement.setString(1, entity.getNome());
        preparedStatement.setString(2, entity.getDescricao());
        preparedStatement.setDouble(3, entity.getPreco());
        preparedStatement.setLong(4, entity.getId());
    }
}

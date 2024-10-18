package org.br.lucaspjardim.dao.venda;

import org.br.lucaspjardim.dao.generic.GenericDAO;
import org.br.lucaspjardim.model.venda.Venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: Lucas Jardim
 */
public class VendaDAO extends GenericDAO<Venda> {

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO venda (cliente_id, produto_id, quantidade, valor_total) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE venda SET cliente_id = ?, produto_id = ?, quantidade = ?, valor_total = ? WHERE id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM venda WHERE id = ?";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM venda WHERE id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM venda";
    }

    @Override
    protected Venda mapRow(ResultSet resultSet) throws SQLException {
        Venda venda = new Venda(
                resultSet.getLong("cliente_id"),
                resultSet.getLong("produto_id"),
                resultSet.getInt("quantidade"),
                resultSet.getDouble("valor_total"),
                resultSet.getDate("data_venda")
        );
        venda.setId(resultSet.getLong("id"));
        return venda;
    }

    @Override
    protected void setInsertParameters(PreparedStatement preparedStatement, Venda entity) throws SQLException {
        preparedStatement.setLong(1, entity.getIdCliente());
        preparedStatement.setLong(2, entity.getIdProduto());
        preparedStatement.setInt(3, entity.getQuantidade());
        preparedStatement.setDouble(4, entity.getValorTotal());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, Venda entity) throws SQLException {
        preparedStatement.setLong(1, entity.getIdCliente());
        preparedStatement.setLong(2, entity.getIdProduto());
        preparedStatement.setInt(3, entity.getQuantidade());
        preparedStatement.setDouble(4, entity.getValorTotal());
        preparedStatement.setLong(5, entity.getId());
    }
}

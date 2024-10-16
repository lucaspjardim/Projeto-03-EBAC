package org.br.lucaspjardim.dao.venda;

import org.br.lucaspjardim.model.venda.Venda;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IVendaDAO {
    void cadastrarVenda(Connection connection, Venda venda) throws SQLException;
    void atualizarVenda(Connection connection, Venda venda) throws SQLException;
    void deletarVenda(Connection connection, Long id) throws SQLException;
    Venda buscarVenda(Connection connection, Long id) throws SQLException;
    List<Venda> buscarTodasVendas(Connection connection) throws SQLException;
}

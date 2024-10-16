package org.br.lucaspjardim.dao.venda;

import org.br.lucaspjardim.model.venda.Venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO implements IVendaDAO {

    @Override
    public void cadastrarVenda(Connection connection, Venda venda) throws SQLException {
        String sql = "INSERT INTO venda (cliente_id, produto_id, quantidade, valor_total) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, venda.getIdCliente());
            stmt.setLong(2, venda.getIdProduto());
            stmt.setInt(3, venda.getQuantidade());
            stmt.setDouble(4, venda.getValorTotal());

            stmt.executeUpdate();
        }
    }

    @Override
    public void atualizarVenda(Connection connection, Venda venda) throws SQLException {
        String sql = "UPDATE venda SET cliente_id = ?, produto_id = ?, quantidade = ?, valor_total = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, venda.getIdCliente());
            stmt.setLong(2, venda.getIdProduto());
            stmt.setInt(3, venda.getQuantidade());
            stmt.setDouble(4, venda.getValorTotal());
            stmt.setLong(5, venda.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public Venda buscarVenda(Connection connection, Long id) throws SQLException {
        String sql = "SELECT * FROM venda WHERE id = ?";
        Venda venda = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    venda = new Venda(
                            rs.getLong("cliente_id"),
                            rs.getLong("produto_id"),
                            rs.getInt("quantidade"),
                            rs.getDouble("valor_total"),
                            rs.getDate("data_venda")
                    );
                    venda.setId(rs.getLong("id"));
                }
            }
        }
        return venda;
    }

    @Override
    public List<Venda> buscarTodasVendas(Connection connection) throws SQLException {
        String sql = "SELECT * FROM venda";
        List<Venda> vendas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Venda venda = new Venda(
                        rs.getLong("cliente_id"),
                        rs.getLong("produto_id"),
                        rs.getInt("quantidade"),
                        rs.getDouble("valor_total"),
                        rs.getDate("data_venda")
                );
                venda.setId(rs.getLong("id"));
                vendas.add(venda);
            }
        }
        return vendas;
    }

    @Override
    public void deletarVenda(Connection connection, Long id) throws SQLException {
        String sql = "DELETE FROM venda WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}

package org.br.lucaspjardim.dao.produto;

import org.br.lucaspjardim.model.cliente.Cliente;
import org.br.lucaspjardim.model.produto.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Lucas Jardim
 */
public class ProdutoDAO implements IProdutoDAO {

    @Override
    public void cadastrarProduto(Connection connection, Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (nome, descricao, preco) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());

            stmt.executeUpdate();
        }
    }

    @Override
    public void atualizarProduto(Connection connection, Produto produto) throws SQLException {
        String sql = "UPDATE produto SET nome = ?, descricao = ?, preco = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setLong(4, produto.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deletarProduto(Connection connection, Long id) throws SQLException {
        String sql = "DELETE FROM produto WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Produto buscarProduto(Connection connection, Long id) throws SQLException {
        String sql = "SELECT * FROM produto WHERE id = ?";
        Produto produto = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto(
                            rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getDouble("preco")
                    );
                    produto.setId(rs.getLong("id"));
                }
            }
        }
        return produto;
    }

    @Override
    public List<Produto> buscarTodosProdutos(Connection connection) throws SQLException {
        String sql = "SELECT * FROM produto";
        List<Produto> produtos = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("preco")
                );
                produto.setId(rs.getLong("id"));
                produtos.add(produto);
            }
        }
        return produtos;
    }
}

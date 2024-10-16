package org.br.lucaspjardim.dao.cliente;

import org.br.lucaspjardim.model.cliente.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClientDAO {

    @Override
    public void cadastroCliente(Connection connection, Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nome, email, telefone) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());

            stmt.executeUpdate();
            System.out.println("Cliente cadastrado!");
        }
    }

    @Override
    public void atualizarCliente(Connection connection, Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET nome = ?, email = ?, telefone = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setLong(4, cliente.getId());

            stmt.executeUpdate();
            System.out.println("Cliente atualizado!");
        }
    }

    @Override
    public void deletarCliente(Connection connection, Long id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Cliente buscarCliente(Connection connection, Long id) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        Cliente cliente = null;

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("telefone")
                    );
                    cliente.setId(rs.getLong("id"));
                }
            }
        }

        return cliente;
    }

    @Override
    public List<Cliente> buscarTodosClientes(Connection connection) throws SQLException {
        String sql = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("telefone")
                    );
                    cliente.setId(rs.getLong("id"));
                    clientes.add(cliente);
                }
        }
        return clientes;
    }
}

package br.agencia.dao;

import br.agencia.model.*;
import br.agencia.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDao {
    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO Clientes (nome, cpf, passaporte, telefone, email, nacionalidade) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());

            if (cliente instanceof ClienteNacional) {
                stmt.setString(2, cliente.getDocumento()); // CPF
                stmt.setString(3, null);                   // Passaporte
            } else {
                stmt.setString(2, null);
                stmt.setString(3, cliente.getDocumento()); // Passaporte
            }

            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getNacionalidade());

            stmt.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }
}

package br.agencia.dao;

import br.agencia.model.ServicoAdicional;
import br.agencia.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServicoAdicionalDAO {
    public void inserir(ServicoAdicional servico) {
        String sql = "INSERT INTO servico_adicional (pedido_id, descricao, preco) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, servico.getPedidoId());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getPreco());

            stmt.executeUpdate();
            System.out.println("Serviço adicional inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir serviço adicional: " + e.getMessage());
        }
    }
}
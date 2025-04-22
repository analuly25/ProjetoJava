package br.agencia.dao;

import br.agencia.model.Pedido;
import br.agencia.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class PedidoDao {
    public void cadastrar(Pedido pedido) {
        String sql = "INSERT INTO pedido (cliente_id, pacote_id, data_pedido) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedido.getClienteId());
            stmt.setInt(2, pedido.getPacoteId());
            stmt.setDate(3, java.sql.Date.valueOf(pedido.getDataPedido()));

            stmt.executeUpdate();
            System.out.println("Pedido cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar pedido: " + e.getMessage());
        }
    }
}

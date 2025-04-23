package br.agencia.dao;

import br.agencia.model.Pedido;
import br.agencia.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PedidoDao {
    public void cadastrar(Pedido pedido) {
        String sql = "INSERT INTO pedido (idClientes, idPacoteViagem, data_pedido, valor_total) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedido.getIdCliente());
            stmt.setInt(2, pedido.getIdPacote());
            stmt.setDate(3, java.sql.Date.valueOf(pedido.getDataPedido()));
            stmt.setDouble(4, pedido.getValorTotal());  // Inserindo o valor total no banco

            stmt.executeUpdate();
            System.out.println("Pedido cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pedido: " + e.getMessage());
        }
    }
}


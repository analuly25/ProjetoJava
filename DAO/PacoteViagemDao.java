package br.agencia.dao;

import br.agencia.model.PacoteViagem;
import br.agencia.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class PacoteViagemDao {
    public void inserir(PacoteViagem pacote) {
        String sql = "INSERT INTO PacoteViagem (nome, destino, descricao, duracao_dias, preco, tipo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pacote.getNome());
            stmt.setString(2, pacote.getDestino());
            stmt.setString(3, pacote.getDescricao());
            stmt.setInt(4, pacote.getDuracaoDias());
            stmt.setDouble(5, pacote.getPreco());
            stmt.setString(6, pacote.getTipo());

            stmt.executeUpdate();
            System.out.println("Pacote cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir pacote: " + e.getMessage());
        }
    }

    public PacoteViagem buscarPorId(int id) {
        PacoteViagem pacote = null;
        String sql = "SELECT * FROM PacoteViagem WHERE idPacoteViagem = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pacote = new PacoteViagem();
                pacote.setIdPacoteViagem(rs.getInt("idPacoteViagem"));
                pacote.setNome(rs.getString("nome")); // ou outros atributos
                pacote.setPreco(rs.getDouble("preco"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar pacote: " + e.getMessage());
        }
        return pacote;
    }
}
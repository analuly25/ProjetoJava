package br.agencia.util;

import java.sql.Connection;
import java.sql.SQLException;

public class testeconexao {
    public static void main(String[] args) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            System.out.println(" Conexão com MySQL estabelecida com sucesso!");
            conn.close();
        } catch (SQLException e) {
            System.out.println(" Erro na conexão: " + e.getMessage());
        }
    }
}
package br.agencia.view;

import br.agencia.dao.*;
import br.agencia.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // === Cadastro de Cliente ===
        ClienteDao clienteDao = new ClienteDao();
        System.out.println("=== Cadastro de Cliente ===");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Nacionalidade (NACIONAL/ESTRANGEIRO): ");
        String tipo = sc.nextLine().toUpperCase();

        Cliente cliente = null;
        if (tipo.equals("NACIONAL")) {
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            cliente = new ClienteNacional(nome, telefone, email, cpf);
        } else if (tipo.equals("ESTRANGEIRO")) {
            System.out.print("Passaporte: ");
            String passaporte = sc.nextLine();
            cliente = new ClienteEstrangeiro(nome, telefone, email, passaporte);
        } else {
            System.out.println("Tipo inválido.");
            return;
        }

        clienteDao.inserir(cliente);

        // === Cadastro de Pacote de Viagem ===
        PacoteViagemDao pacoteDao = new PacoteViagemDao();
        System.out.println("\n=== Cadastro de Pacote de Viagem ===");
        System.out.print("Nome do pacote: ");
        String nomePacote = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Duração (dias): ");
        int duracao = sc.nextInt();
        sc.nextLine();
        System.out.print("Preço: ");
        double precoPacote = sc.nextDouble();
        sc.nextLine();
        System.out.print("Tipo do pacote:(NACIONAL/ESTRANGEIRO): ");
        String tipoPacote = sc.nextLine();

        PacoteViagem pacote = new PacoteViagem(nomePacote, destino, descricao, duracao, precoPacote, tipoPacote);
        pacoteDao.inserir(pacote);

        // === Cadastro de Pedido ===
        PedidoDao pedidoDao = new PedidoDao();
        System.out.println("\n=== Cadastro de Pedido ===");
        System.out.print("ID do Cliente: ");
        int idCliente = sc.nextInt();
        System.out.print("ID do Pacote: ");
        int idPacote = sc.nextInt();
        sc.nextLine();

        LocalDate dataPedido = LocalDate.now();
        double valorTotal = precoPacote;

        // === Cadastro de Serviços Adicionais (vários) ===
        List<ServicoAdicional> servicos = new ArrayList<>();
        ServicoAdicionalDAO servicoDao = new ServicoAdicionalDAO();

        while (true) {
            System.out.println("\nDeseja adicionar um serviço adicional? (s/n): ");
            String resposta = sc.nextLine();
            if (!resposta.equalsIgnoreCase("s")) break;

            System.out.print("Nome do serviço: ");
            String nomeServico = sc.nextLine();
            System.out.print("Descrição do serviço: ");
            String descServico = sc.nextLine();
            System.out.print("Preço do serviço: ");
            double precoServico = sc.nextDouble();
            sc.nextLine();

            ServicoAdicional servico = new ServicoAdicional(nomeServico, descServico, precoServico);
            servicoDao.inserir(servico);
            servicos.add(servico);
            valorTotal += precoServico;
        }

        // === Salvar Pedido com valor total ===
        Pedido pedido = new Pedido(idCliente, idPacote, dataPedido, valorTotal);
        pedidoDao.cadastrar(pedido);

        System.out.println("\nPedido cadastrado com sucesso! Valor total: R$ " + valorTotal);
        sc.close();
    }
}







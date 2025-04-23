package br.agencia.view;

import br.agencia.dao.ClienteDao;
import br.agencia.model.*;

import br.agencia.dao.PacoteViagemDao;
import br.agencia.model.PacoteViagem;

import br.agencia.dao.PedidoDao;
import br.agencia.model.Pedido;

import java.time.LocalDate;

import br.agencia.model.ServicoAdicional;
import br.agencia.dao.ServicoAdicionalDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClienteDao dao = new ClienteDao();

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

        dao.inserir(cliente);

        System.out.println("\n=== Cadastro de Pacote ===");
        System.out.print("Nome do pacote: ");
        String nomePacote = sc.nextLine();

        System.out.print("Destino: ");
        String destino = sc.nextLine();

        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        System.out.print("Duração (dias): ");
        int duracao = sc.nextInt();
        sc.nextLine(); // limpa buffer

        System.out.print("Preço: ");
        double preco = sc.nextDouble();
        sc.nextLine();

        System.out.print("Tipo ('Nacional ou International'): ");
        String tipoPacote = sc.nextLine();

        PacoteViagem pacote = new PacoteViagem(nomePacote, destino, descricao, duracao, preco, tipoPacote);
        PacoteViagemDao pacoteDAO = new PacoteViagemDao();
        pacoteDAO.inserir(pacote);

        System.out.println("\n=== Cadastro de Pedido ===");

        System.out.print("ID do Cliente: ");
        int idCliente = sc.nextInt();

        System.out.print("ID do Pacote: ");
        int idPacote = sc.nextInt();
        sc.nextLine();

        LocalDate dataPedido = LocalDate.now();

        Pedido pedido = new Pedido(idCliente, idPacote, dataPedido);
        PedidoDao pedidoDAO = new PedidoDao();
        pedidoDAO.cadastrar(pedido);

        System.out.println("\n=== Cadastro de Serviço Adicional ===");

        System.out.print("ID do Pedido: ");
        int idPedido = sc.nextInt();
        sc.nextLine(); // limpa buffer

        System.out.print("Descrição do serviço: ");
        String descricaoServico = sc.nextLine();

        System.out.print("Preço do serviço: ");
        double precoServico = sc.nextDouble();
        sc.nextLine(); // limpa buffer

        ServicoAdicional servico = new ServicoAdicional(idPedido, descricao, preco);
        ServicoAdicionalDAO servicoDAO = new ServicoAdicionalDAO();
        servicoDAO.inserir(servico);

        sc.close();
    }
}
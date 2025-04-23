
import br.agencia.dao.*;
        import br.agencia.model.*;

        import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        // Menu principal com loop até o usuário escolher sair
        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Menu de Clientes");
            System.out.println("2. Menu de Pacotes de Viagem");
            System.out.println("3. Menu de Serviços");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar o buffer do teclado

            switch (opcao) {
                case 1 -> menuClientes(sc); // chama menu de clientes
                case 2 -> menuPacotes(sc);  // chama menu de pacotes
                case 3 -> menuServicos(sc); // chama menu de serviços
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        sc.close(); // fecha o Scanner
    }

    // =================== MENU DE CLIENTES ===================
    public static void menuClientes(Scanner sc) {
        ClienteDao dao = new ClienteDao(); // Acesso ao banco de dados de clientes
        int opcao;

        do {
            System.out.println("\n--- Menu de Clientes ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Remover Cliente");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpa o buffer

            switch (opcao) {
                case 1 -> {
                    // Cadastro de cliente
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Nacionalidade (NACIONAL/ESTRANGEIRO): ");
                    String tipo = sc.nextLine().toUpperCase();

                    Cliente cliente;
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
                        continue;
                    }

                    dao.inserir(cliente); // salva o cliente
                    System.out.println("Cliente cadastrado!");
                }

                case 2 -> {
                    // Lista todos os clientes cadastrados
                    for (Cliente c : dao.listar()) {
                        System.out.println(c);
                    }
                }

                case 3 -> {
                    // Remove um cliente pelo ID
                    System.out.print("Informe o ID do cliente para remover: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    dao.remover(id);
                    System.out.println("Cliente removido.");
                }

                case 0 -> {} // Volta para o menu principal
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // =================== MENU DE PACOTES ===================
    public static void menuPacotes(Scanner sc) {
        PacoteViagemDao dao = new PacoteViagemDao();
        int opcao;

        do {
            System.out.println("\n--- Menu de Pacotes ---");
            System.out.println("1. Cadastrar Pacote");
            System.out.println("2. Listar Pacotes");
            System.out.println("3. Remover Pacote");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do pacote: ");
                    String nome = sc.nextLine();
                    System.out.print("Destino: ");
                    String destino = sc.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();
                    System.out.print("Duração (dias): ");
                    int duracao = sc.nextInt();
                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Tipo (Nacional ou Internacional): ");
                    String tipo = sc.nextLine();

                    PacoteViagem pacote = new PacoteViagem(nome, destino, descricao, duracao, preco, tipo);
                    dao.inserir(pacote);
                    System.out.println("Pacote cadastrado!");
                }

                case 2 -> {
                    // Lista todos os pacotes
                    for (PacoteViagem p : dao.listar()) {
                        System.out.println(p);
                    }
                }

                case 3 -> {
                    System.out.print("Informe o ID do pacote para remover: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    dao.remover(id);
                    System.out.println("Pacote removido.");
                }

                case 0 -> {}
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // =================== MENU DE SERVIÇOS ===================
    public static void menuServicos(Scanner sc) {
        PedidoDao pedidoDao = new PedidoDao();
        ServicoAdicionalDAO servicoDao = new ServicoAdicionalDAO();
        int opcao;

        do {
            System.out.println("\n--- Menu de Serviços ---");
            System.out.println("1. Cadastrar Pedido + Serviço");
            System.out.println("2. Listar Serviços");
            System.out.println("3. Remover Serviço");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("ID do Cliente: ");
                    int idCliente = sc.nextInt();
                    System.out.print("ID do Pacote: ");
                    int idPacote = sc.nextInt();
                    sc.nextLine();

                    // Cadastra pedido com data atual
                    LocalDate data = LocalDate.now();
                    Pedido pedido = new Pedido(idCliente, idPacote, data);
                    pedidoDao.cadastrar(pedido);

                    System.out.print("ID do Pedido: ");
                    int idPedido = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Descrição do serviço: ");
                    String descricao = sc.nextLine();
                    System.out.print("Preço do serviço: ");
                    double preco = sc.nextDouble();
                    sc.nextLine();

                    ServicoAdicional servico = new ServicoAdicional(idPedido, descricao, preco);
                    servicoDao.inserir(servico);
                    System.out.println("Serviço adicional cadastrado!");
                }

                case 2 -> {
                    for (ServicoAdicional s : servicoDao.listar()) {
                        System.out.println(s);
                    }
                }

                case 3 -> {
                    System.out.print("Informe o ID do serviço para remover: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    servicoDao.remover(id);
                    System.out.println("Serviço removido.");
                }

                case 0 -> {}
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}

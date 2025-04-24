// Pacote principal da interface gráfica da agência de viagens
package br.agencia.view;

// Importações dos pacotes DAO (acesso a dados) e model (entidades)
import br.agencia.dao.*;
import br.agencia.model.*;

// Importações da biblioteca Swing e utilitários
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Classe principal da interface gráfica, herdando de JFrame
public class MainGUI extends JFrame {
    // Objetos DAO para manipulação de dados
    ClienteDao clienteDao = new ClienteDao();
    PacoteViagemDao pacoteDao = new PacoteViagemDao();
    PedidoDao pedidoDao = new PedidoDao();

    public MainGUI() {
        // Configurações da janela principal
        setTitle("Sistema de Agência de Viagens");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação de abas para cliente, pacote e pedido
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Cliente", criarPainelCliente());
        tabbedPane.add("Pacote", criarPainelPacote());
        tabbedPane.add("Pedido", criarPainelPedido());

        // Adiciona o painel de abas à janela principal
        add(tabbedPane);
        setVisible(true);
    }

    // Painel da aba Cliente
    private JPanel criarPainelCliente() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(5, 2));

        // Campos do formulário de cliente
        JTextField nomeField = new JTextField();
        JTextField telefoneField = new JTextField();
        JTextField emailField = new JTextField();
        JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"NACIONAL", "ESTRANGEIRO"});
        JTextField docField = new JTextField();

        // Adiciona os campos ao formulário
        form.add(new JLabel("Nome:")); form.add(nomeField);
        form.add(new JLabel("Telefone:")); form.add(telefoneField);
        form.add(new JLabel("Email:")); form.add(emailField);
        form.add(new JLabel("Tipo:")); form.add(tipoCombo);
        form.add(new JLabel("CPF/Passaporte:")); form.add(docField);

        // Botão para salvar cliente
        JButton salvarBtn = new JButton("Salvar Cliente");
        salvarBtn.addActionListener(e -> {
            // Recupera dados dos campos
            String nome = nomeField.getText();
            String telefone = telefoneField.getText();
            String email = emailField.getText();
            String tipo = (String) tipoCombo.getSelectedItem();
            String doc = docField.getText();

            // Cria cliente conforme o tipo
            Cliente cliente;
            if ("NACIONAL".equals(tipo)) {
                cliente = new ClienteNacional(nome, telefone, email, doc);
            } else {
                cliente = new ClienteEstrangeiro(nome, telefone, email, doc);
            }

        });

        // Tabela para exibir os clientes
        JTable tabela = new JTable(new DefaultTableModel(new Object[]{"ID", "Nome", "Tipo"}, 0));
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Painel com os botões
        JPanel botoesPanel = new JPanel();
        botoesPanel.add(salvarBtn);


        // Adiciona os componentes ao painel principal
        panel.add(form, BorderLayout.NORTH);
        panel.add(botoesPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        tabelaClientes = tabela; // Guarda referência para atualizar depois

        return panel;
    }

    // Painel da aba Pacote de Viagem
    private JPanel criarPainelPacote() {
        JPanel panel = new JPanel(new GridLayout(7, 2));

        // Campos do formulário de pacote
        JTextField nomeField = new JTextField();
        JTextField destinoField = new JTextField();
        JTextField descField = new JTextField();
        JTextField duracaoField = new JTextField();
        JTextField precoField = new JTextField();
        JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"NACIONAL", "ESTRANGEIRO"});

        // Botão para salvar pacote
        JButton salvarBtn = new JButton("Salvar Pacote");
        salvarBtn.addActionListener(e -> {
            // Recupera dados dos campos
            String nome = nomeField.getText();
            String destino = destinoField.getText();
            String desc = descField.getText();
            int duracao = Integer.parseInt(duracaoField.getText());
            double preco = Double.parseDouble(precoField.getText());
            String tipo = (String) tipoCombo.getSelectedItem();

            // Cria e salva o pacote
            PacoteViagem pacote = new PacoteViagem(nome, destino, desc, duracao, preco, tipo);
            pacoteDao.inserir(pacote);
            JOptionPane.showMessageDialog(this, "Pacote salvo com sucesso!");
        });

        // Adiciona os campos e botão ao painel
        panel.add(new JLabel("Nome:")); panel.add(nomeField);
        panel.add(new JLabel("Destino:")); panel.add(destinoField);
        panel.add(new JLabel("Descrição:")); panel.add(descField);
        panel.add(new JLabel("Duração:")); panel.add(duracaoField);
        panel.add(new JLabel("Preço:")); panel.add(precoField);
        panel.add(new JLabel("Tipo:")); panel.add(tipoCombo);
        panel.add(salvarBtn);

        return panel;
    }

    // Painel da aba Pedido
    private JPanel criarPainelPedido() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        // Campos para IDs de cliente e pacote
        JTextField clienteIdField = new JTextField();
        JTextField pacoteIdField = new JTextField();

        // Botão para salvar pedido
        JButton salvarBtn = new JButton("Salvar Pedido");
        salvarBtn.addActionListener(e -> {
            int idCliente = Integer.parseInt(clienteIdField.getText());
            int idPacote = Integer.parseInt(pacoteIdField.getText());
            double preco = pacoteDao.buscarPorId(idPacote).getPreco();

            Pedido pedido = new Pedido(idCliente, idPacote, LocalDate.now(), preco);
            pedidoDao.cadastrar(pedido);
            JOptionPane.showMessageDialog(this, "Pedido salvo com sucesso!");
        });

        // Adiciona os campos e botão ao painel
        panel.add(new JLabel("ID Cliente:")); panel.add(clienteIdField);
        panel.add(new JLabel("ID Pacote:")); panel.add(pacoteIdField);
        panel.add(salvarBtn);

        return panel;
    }

    // Referência para a tabela de clientes
    private JTable tabelaClientes;



    // Método main para iniciar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}

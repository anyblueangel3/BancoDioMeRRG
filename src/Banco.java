import model.Cliente;
import model.Conta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Banco {

    public List<Cliente> clientes;

    public static Banco bb;

    public Banco() {

        this.clientes = new ArrayList<>();

    }

    public static void main(String[] args) {

        bb = new Banco();

        bb.menuOpcoes();

    }

    private void menuOpcoes() {

        // Cria a janela principal
        JFrame frame = new JFrame("Menu de Opções");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela

        // Cria um painel para os botões usando BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // Adiciona um espaçamento à esquerda e à direita
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 0, 0));

        // Cria os botões
        JButton btnDepositar = new JButton("1 - Depositar");
        JButton btnSacar = new JButton("2 - Sacar");
        JButton btnTransferir = new JButton("3 - Transferir");
        JButton btnAbrirConta = new JButton("4 - Abrir conta");
        JButton btnFecharPrograma = new JButton("5 - Fechar programa");

        // Adiciona ação aos botões
        btnDepositar.addActionListener(e -> {

            int conta = 0;
            double valor = 0;

            try {
                conta = Integer
                        .parseInt(JOptionPane.showInputDialog
                                (null, "Digite o número da conta do cliente:"));
                valor = Double
                        .parseDouble(JOptionPane.showInputDialog
                                (null, "Digite o valor do depósito:"));
            } catch (Exception erro) {
                System.out.println("Errrrroooooo...: " + erro.toString());
            }

            if(conta != 0) {
                depositar(conta, valor);
            }

        });

        btnSacar.addActionListener(e -> {

            int conta = 0;
            double valor = 0;

            try {
                conta = Integer
                        .parseInt(JOptionPane.showInputDialog
                                (null, "Digite o número da conta do cliente:"));
                valor = Double
                        .parseDouble(JOptionPane.showInputDialog
                                (null, "Digite o valor do saque:"));
            } catch (Exception erro) {
                System.out.println("Errrrroooooo...: " + erro.toString());
            }
            if(conta != 0) {
                sacar(conta, valor);
            }

        });

        btnTransferir.addActionListener(e -> {

            int contaSacado = 0;
            int contaCreditado = 0;
            double valor = 0;

            try {
                contaSacado = Integer
                        .parseInt(JOptionPane.showInputDialog
                                (null, "Digite o número da conta do cliente que será sacado: "));

                contaCreditado = Integer
                        .parseInt(JOptionPane.showInputDialog
                                (null, "Digite o número da conta do cliente que será creditado: "));

                valor = Double
                        .parseDouble(JOptionPane.showInputDialog
                                (null, "Digite o valor da Transferência: "));
            } catch (Exception erro) {
                System.out.println("Errrrroooooo...: " + erro.toString());
            }

            if(contaSacado != 0 && contaCreditado != 0) {
                transferir(contaSacado, contaCreditado, valor);
            } else { JOptionPane.showMessageDialog(null,
                    "Número de conta inválido.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            };

        });

        btnAbrirConta.addActionListener(e -> {

            final int numeroCliente;
            final int conta;
            int auxNumeroCliente = 0;
            int auxConta = 0;
            String nomeCliente = "";

            try {
                auxNumeroCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número do cliente: "));
                auxConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da conta: "));
                nomeCliente = JOptionPane.showInputDialog(null, "Digite o nome do cliente:");
            } catch (Exception erro){
                System.out.println("Errrrroooooo...: " + erro.toString());
            }

            if (auxNumeroCliente != 0 && auxConta !=0) {
                numeroCliente = auxNumeroCliente;
                conta = auxConta;
            } else return;

            boolean contaExiste = bb.clientes.stream()
                    .flatMap(cliente -> cliente.getContas().stream())
                    .anyMatch(c -> c.getNumeroConta() == conta);

            boolean numeroClienteExiste = bb.clientes.stream()
                    .anyMatch(cliente -> cliente.getNumeroCliente() == numeroCliente);

            if (contaExiste) {
                JOptionPane.showMessageDialog(null, "Conta já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (numeroClienteExiste) {
                bb.abrirConta(conta, numeroCliente);
            } else {
                bb.criarCliente(nomeCliente);
                int auxDoisNumeroCliente = 0;
                bb.abrirConta(conta, auxDoisNumeroCliente);
            }

        });

        btnFecharPrograma.addActionListener(e -> fecharPrograma());

        // Adiciona os botões ao painel com uma linha vazia entre eles
        panel.add(btnDepositar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnSacar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnTransferir);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnAbrirConta);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnFecharPrograma);

        // Adiciona o painel à janela
        frame.getContentPane().add(panel);

        // Torna a janela visível
        frame.setVisible(true);
    }

    public void depositar(int numeroConta, double valor) {
        Deposito dp = new Deposito();
        bb.clientes = dp.fazerDeposito(bb, numeroConta, valor);
    }

    public void sacar(int numeroConta, double valor) {
        Saque sq = new Saque();
        bb.clientes = sq.fazerSaque(bb, numeroConta, valor);
    }

    public void transferir(int numeroContaSacar, int numeroContaDepositar, double valor) {
        Saque sq = new Saque();
        bb.clientes = sq.fazerSaque(bb, numeroContaSacar, valor);

        Deposito dp = new Deposito();
        bb.clientes = dp.fazerDeposito(bb, numeroContaDepositar, valor);
    }

    public void criarCliente(String nomeCliente) {
        // TODO aqui aqui aqui
        Cliente cliente = new Cliente(nomeCliente);
        bb.clientes.add(cliente);
    }

    public void abrirConta(int conta, int numeroCliente) {
        clientes.stream()
            .filter(cliente -> cliente.getNumeroCliente() == numeroCliente)
            .findFirst()
            .ifPresent(cliente -> cliente.getContas().add(new Conta(conta)));
    }

    public Conta getNumeroConta(int numeroConta) {
        for (Cliente cliente : clientes) {
            Optional<Conta> contaOptional = cliente.getContas().stream()
                    .filter(conta -> conta.getNumeroConta() == numeroConta)
                    .findFirst();
            if (contaOptional.isPresent()) {
                return contaOptional.get();
            }
        }
        return null; // Retorna null se a conta não for encontrada
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    // Método para verificar se existe um cliente com o número de conta especificado
    public boolean existeClienteComNumeroConta(int numeroConta) {
        return clientes.stream()
                .flatMap(cliente -> cliente.getContas().stream())
                .anyMatch(conta -> conta.getNumeroConta() == numeroConta);
    }

    public void fecharPrograma() {
        System.exit(0);
    }

}

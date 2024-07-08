/*
     Este projeto foi desenvolvido por mim assistido pelo Chat GPT da OpenAI,
     Quase todas as classes tem trechos de código desenvolvidos pelo Chat GPT
     sob minha orientação. As idéias do projeto são minhas, as classes que
     estão dentro do pacote model também mas com alguns métodos desenvolvidos
     pelo Chat GPT. Muitas das expressões lambda foram montadas sob minha so-
     licitação e orientação, ou seja, caracterizei o que as streans e as ex-
     pressões lambda teriam que fazer e o Chat GPT implementou. Eu conferi
     o funcionamento do sistema, testando e usando o auxílio do Chat GPT.
 */



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
    private final int PRIMEIRO_CLIENTE = 1;
    public static Banco bb;

    public Banco() {

        this.clientes = new ArrayList<>();

    }

    public List<Cliente> getClientes() {
        return clientes;
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
        JButton btnRelatorio = new JButton("5 - Relatório de Lançamentos"); // feito por extraterrestre
        JButton btnFecharPrograma = new JButton("6 - Fechar programa");

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

                contaSacado = Integer.parseInt(
                        JOptionPane.showInputDialog(
                        null,
                        "Digite o número da conta do cliente que será sacado: "));

                contaCreditado = Integer.parseInt(
                        JOptionPane.showInputDialog(
                        null,
                        "Digite o número da conta do cliente que será creditado: "));

                valor = Double.parseDouble(
                        JOptionPane.showInputDialog(
                        null,
                        "Digite o valor da Transferência: "));

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
            String auxZeroNumeroCliente = "";
            int auxNumeroCliente = 0;
            int auxConta = 0;
            String nomeCliente = "";

            try {

                auxZeroNumeroCliente = JOptionPane.showInputDialog(
                                null,
                                "Digite o número do cliente: ");

                if(auxZeroNumeroCliente.equalsIgnoreCase("")) auxNumeroCliente = 0;
                else auxNumeroCliente = Integer.parseInt(auxZeroNumeroCliente);

                auxConta = Integer.parseInt(
                        JOptionPane.showInputDialog(
                                null,
                                "Digite o número da conta: "));


            } catch (Exception erro){
                System.out.println("Errrrroooooo...: " + erro.toString());
            }

            if (auxConta !=0) {
                numeroCliente = auxNumeroCliente;
                conta = auxConta;
            } else return;

            boolean numeroClienteExiste = bb.clientes.stream()
                    .anyMatch(cliente -> cliente.getNumeroCliente() == numeroCliente);

            boolean contaExiste;

            if (!numeroClienteExiste) {
                contaExiste = false;
                nomeCliente = JOptionPane.showInputDialog(
                        null,
                        "Digite o nome do cliente:");
            } else  {
                contaExiste = bb.clientes.stream()
                        .flatMap(cliente -> cliente.getContas().stream())
                        .anyMatch(c -> c.getNumeroConta() == conta);
            }



            if (contaExiste) {
                JOptionPane.showMessageDialog(null, "Conta já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (numeroClienteExiste && numeroCliente != 0) {
                bb.abrirConta(conta, numeroCliente);
            } else {
                bb.criarCliente(nomeCliente);
                int auxDoisNumeroCliente = 0;
                bb.abrirConta(conta, PRIMEIRO_CLIENTE);
                System.out.println("Chego-oooou 3!!!");
            }

        });

        btnRelatorio.addActionListener(e -> RelatorioLancamentos.gerarRelatorio(bb));

        btnFecharPrograma.addActionListener(e -> fecharPrograma());

        // Adiciona os botões ao painel com uma linha vazia entre eles
        panel.add(btnDepositar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnSacar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnTransferir);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnAbrirConta);
        panel.add(Box.createVerticalStrut(10));     // feito por extraterrestre
        panel.add(btnRelatorio);                           // feito por extraterrestre
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
        Cliente cliente = new Cliente(nomeCliente);
        bb.clientes.add(cliente);
    }

    public void abrirConta(int conta, int numeroCliente) {
        bb.clientes.stream()
            .filter(cliente -> cliente.getNumeroCliente() == numeroCliente)
            .findFirst()
            .ifPresent(cliente -> cliente.getContas().add(new Conta(conta)));
    }

    public void fecharPrograma() {
        System.exit(0);
    }

}

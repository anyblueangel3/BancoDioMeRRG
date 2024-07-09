/*
     Este projeto foi desenvolvido por mim assistido pelo Chat GPT da OpenAI,
     Quase todas as classes tem trechos de código desenvolvidos pelo Chat GPT
     sob minha orientação. As idéias do projeto são minhas, as classes que
     estão dentro do pacote model também mas com alguns métodos desenvolvidos
     pelo Chat GPT. Muitas das expressões lambda foram montadas sob minha so-
     licitação e orientação, ou seja, caracterizei o que as streans e as ex-
     pressões lambda teriam que fazer e o Chat GPT implementou. Eu conferi
     o funcionamento do sistema, testando e usando o auxílio do Chat GPT.

     Hávia dito que usaria integralmente as classes do projeto que está no
     GitHub no endereço <https://github.com/falvojr/lab-banco-digital-oo> mas
     teria muitas dificuldades em fazer as rotinas de entrada de dados e criação
     das contas de forma que resolvi aproveitar muitos atributos e criar minhas
     próprias classes. Porém o projeto serviu de referência.
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

    private List<Cliente> clientes;
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
        frame.setSize(400, 350);
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
        JButton btnRelatorio = new JButton("5 - Relatório de lançamentos"); // feito por extraterrestre
        JButton btnFecharPrograma = new JButton("6 - Fechar programa");
        JButton btnRelatorio2 = new JButton("7 - Relatório de clientes");

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

            int numeroCliente;
            int conta = 0;
            String auxNumeroCliente = "";
            String auxConta;
            String nomeCliente = "";

            try {

                auxNumeroCliente = JOptionPane.showInputDialog(
                                null,
                                "Digite o número do cliente: ");

                if(auxNumeroCliente.equals("")) auxNumeroCliente = "0";

                numeroCliente = Integer.parseInt(auxNumeroCliente);
                System.out.println("*****************************");
                System.out.println("auxNumeroCliente:" + auxNumeroCliente);
                System.out.println("numeroCliente:" + numeroCliente);
                System.out.println("***************************");


                auxConta = JOptionPane.showInputDialog(
                        null,
                        "Digite o número da conta: ");

                if(auxConta.equals("")) auxConta = "0";
                conta = Integer.parseInt(auxConta);

            } catch (Exception erro){
                System.out.println("Errrrroooooo...: " + erro.toString());
                return;
            }

            if (conta == 0) return;

            int[] auxDoisNumeroCliente = new int[] { numeroCliente };

            boolean numeroClienteExiste = bb.clientes.stream()
                    .anyMatch((cliente) -> cliente.getNumeroCliente2() == auxDoisNumeroCliente[0]);

            boolean contaExiste;

            int[] auxDoisConta = new int[] { conta };

            contaExiste = bb.clientes.stream()
                    .flatMap(cliente -> cliente.getContas().stream())
                    .anyMatch(c -> c.getNumeroConta() == auxDoisConta[0]);

            if (!numeroClienteExiste && !contaExiste) {
                nomeCliente = JOptionPane.showInputDialog(
                        null,
                        "Digite o nome do cliente:");
                if (nomeCliente == null || nomeCliente.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Nome do Cliente está vazio. Cliente não criado.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (contaExiste) {
                JOptionPane.showMessageDialog(null, "Conta já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (numeroClienteExiste && numeroCliente != 0) {
                numeroCliente = bb.clientes.stream()
                        .filter(cliente -> cliente.getContas().stream()
                                .anyMatch(c -> c.getNumeroConta() == auxDoisConta[0]))
                        .mapToInt(Cliente::getNumeroCliente2)
                        .findFirst()
                        .orElse(0);
                bb.abrirConta(conta, numeroCliente);
            } else {
                numeroCliente = bb.criarCliente(nomeCliente);
                bb.abrirConta(conta, numeroCliente);
                System.out.println("Chego-oooou 3!!!");
            }

        });

        btnRelatorio.addActionListener(e -> RelatorioLancamentos.gerarRelatorio(bb));
        btnRelatorio2.addActionListener(e -> RelatorioClientes.gerarRelatorio(bb));

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
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnRelatorio2);

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

    public int criarCliente(String nomeCliente) {
        Cliente cliente = new Cliente(nomeCliente);
        bb.clientes.add(cliente);
        JOptionPane.showMessageDialog(null,
                "Esse cliente acaba de ser criado: "
                        + cliente.getNumeroCliente2(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        
        return cliente.getNumeroCliente2();
    }

//    public void abrirConta(int conta, int numeroCliente) {
//        Conta objetoConta = new Conta(conta);
//
//        System.out.println("Veja se localiza o problema?");
//        System.out.println("-------------------------------------");
//        System.out.println("Número da conta: " + objetoConta.getNumeroConta());
//        System.out.println("Saldo da conta: " + objetoConta.getSaldo());
//
//        bb.clientes.stream()
//            .filter(cliente -> cliente.getNumeroCliente() == numeroCliente)
//            .findFirst()
//            .ifPresent(cliente -> cliente.getContas().add(objetoConta));
//
//        for(Cliente cliente : bb.getClientes()) {
//            System.out.println("Cliente: "
//                    + cliente.getNomeCliente()
//                    + " Número do cliente: "
//                    + cliente.getNumeroCliente2());
//            for(Conta contaFor: cliente.getContas()) {
//                System.out.println("       Conta número: " + contaFor.getNumeroConta());
//            }
//        }
//
//        System.out.println("O problema está aqui. Aqui está o problema." +
//                " Você está olhando para ele. Enxergue!");
//    }

    public void abrirConta(int conta, int numeroCliente) {
        System.out.println(String.format("Abrindo conta... numero: %d cliente: %d", conta, numeroCliente));
        Optional<Cliente> clienteOptional = bb.clientes.stream()
                .filter(cliente -> cliente.getNumeroCliente() == numeroCliente)
                .findFirst();

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.getContas().add(new Conta(conta));
            System.out.println("Conta adicionada: " + conta + " ao cliente: " + numeroCliente);
        } else {
            System.out.println("Cliente não encontrado: " + numeroCliente);
        }

        for(Cliente cliente : bb.getClientes()) {
            System.out.println("Cliente: "
                    + cliente.getNomeCliente()
                    + " Número do cliente: "
                    + cliente.getNumeroCliente2());
            for(Conta contaFor: cliente.getContas()) {
                System.out.println("       Conta número: " + contaFor.getNumeroConta());
            }
        }

    }

    public void fecharPrograma() {
        System.exit(0);
    }

}

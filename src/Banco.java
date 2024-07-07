import Telas.TelaDeposita;
import model.Cliente;
import model.Conta;
import model.Lancamento;

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
        bb = new Banco();

    }

    public static void main(String[] args) {

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
        btnDepositar.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            TelaDeposita td = new TelaDeposita();
            JFrame frame2 = new JFrame("Depositar");
            frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame2.add(td.getPanel());
            frame2.pack();
            frame2.setLocationRelativeTo(null); // Centraliza o JFrame na tela
            frame2.setVisible(true);

            int conta = Integer.parseInt(td.getConta());
            double valor = Double.parseDouble(td.getValor());

            depositar(conta, valor);
        }));

        btnSacar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //sacar();
            }
        });

        btnTransferir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //transferir();
            }
        });

        btnAbrirConta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //abrirConta();
            }
        });

        btnFecharPrograma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fecharPrograma();
            }
        });

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

    public void fecharPrograma() {
        System.exit(0);
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



}

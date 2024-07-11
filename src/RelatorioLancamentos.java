import model.Cliente;
import model.Conta;
import model.Lancamento;

import javax.swing.*;
        import java.awt.*;
        import java.util.List;

public class RelatorioLancamentos {

    public static void gerarRelatorio(Banco banco) {
        JFrame frame = new JFrame("Relatório de Lançamentos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        String tipoTexto;

        for (Cliente cliente : banco.getClientes()) {
            for (Conta conta : cliente.getContas()) {
                textArea.append("Nome Cliente: " + cliente.getNomeCliente() +
                        ", Número Conta: " + conta.getNumeroConta() + "\n");
                for (Lancamento lancamento : conta.getLancamentos()) {
                    tipoTexto = (lancamento.getTipoLancamento() == 1) ? "depósito" : "saque";
                    textArea.append("Número Lançamento: " + lancamento.getNumeroLancamento2() +
                            ", Valor Lançamento: " + lancamento.getValorLancamento() +
                            ", Tipo Lançamento: " + lancamento.getTipoLancamento() +" " + tipoTexto + "\n");
                }
            }
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Fechar janela");
        closeButton.addActionListener(e -> frame.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
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

        for (Cliente cliente : banco.getClientes()) {
            for (Conta conta : cliente.getContas()) {
                for (Lancamento lancamento : conta.getLancamentos()) {
                    String tipoLancamento = lancamento.getTipoLancamento() == 1 ? "Depósito" : "Saque";
                    textArea.append("Nome Cliente: " + cliente.getNomeCliente() +
                            ", Número Conta: " + conta.getNumeroConta() +
                            ", Número Lançamento: " + lancamento.getNumeroLancamento() +
                            ", Valor Lançamento: " + lancamento.getValorLancamento() +
                            ", Tipo Lançamento: " + tipoLancamento + "\n");
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
package Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaDeposita {

    private JPanel panel;
    private JTextField campoConta;
    private JTextField campoValor;
    private JLabel labelConta;
    private JLabel labelValor;

    public TelaDeposita() {
        criarInterface();
    }

    private void criarInterface() {
        // Cria o painel principal com BorderLayout
        panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 300));

        // Título "Depositar" acima dos campos
        JLabel tituloLabel = new JLabel("Depositar", JLabel.CENTER);
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(tituloLabel, BorderLayout.NORTH);

        // Painel para os campos com GridLayout
        JPanel camposPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // 2 linhas, 2 colunas
        camposPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10)); // Espaçamento interno

        // Labels e campos de texto
        labelConta = new JLabel("Conta:");
        campoConta = new JTextField(20);
        labelValor = new JLabel("Valor:");
        campoValor = new JTextField(20);

        // Adiciona os componentes ao painel de campos
        camposPanel.add(labelConta);
        camposPanel.add(campoConta);
        camposPanel.add(labelValor);
        camposPanel.add(campoValor);

        // Adiciona o painel de campos ao painel principal
        panel.add(camposPanel, BorderLayout.CENTER);

        // Botão "Fim da operação" com lambda para fechar a janela
        JButton btnFimOperacao = new JButton("Fim da operação");
        btnFimOperacao.addActionListener(e -> fecharJanela());
        panel.add(btnFimOperacao, BorderLayout.SOUTH);
    }

    private void fecharJanela() {
        // Fecha a janela
        Window window = SwingUtilities.getWindowAncestor(panel);
        if (window != null) {
            window.dispose();
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    // Método para acessar o campo de texto "Conta"
    public String getConta() {
        return campoConta.getText();
    }

    // Método para acessar o campo de texto "Valor"
    public String getValor() {
        return campoValor.getText();
    }

}
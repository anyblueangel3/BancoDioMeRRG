import model.Cliente;
import model.Conta;
import model.Lancamento;

import javax.swing.*;
        import java.util.List;
import java.util.Optional;

public class Saque {

    public List<Cliente> fazerSaque(Banco bb, int numeroConta, double valor) {
        Optional<Conta> contaOptional = bb.getClientes().stream()
                .flatMap(cliente -> cliente.getContas().stream())
                .filter(conta -> conta.getNumeroConta() == numeroConta)
                .findFirst();

        if (contaOptional.isPresent()) {
            Conta conta = contaOptional.get();

            // Verifica se a conta tem saldo suficiente
            if (conta.getSaldo() >= valor) {
                // Cria um novo lançamento de saque
                Lancamento lancamento = new Lancamento(2, valor); // Tipo 2 para saque

                // Adiciona o lançamento à lista de lançamentos da conta
                conta.getLancamentos().add(lancamento);

                // Atualiza o saldo da conta
                double novoSaldo = conta.getSaldo() - valor;
                conta.setSaldo(novoSaldo);

                // Exibe mensagem de sucesso (opcional)
                System.out.println("Saque de R$" + valor + " realizado com sucesso.");
            } else {
                // Exibe mensagem de saldo insuficiente (opcional)
                JOptionPane.showMessageDialog(null,
                        "Saldo insuficiente.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Exibe mensagem de conta não encontrada (opcional)
            JOptionPane.showMessageDialog(null,
                    "Conta não encontrada.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

        return bb.getClientes();
    }
}
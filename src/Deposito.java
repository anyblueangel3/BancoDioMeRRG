import model.Cliente;
import model.Conta;
import model.Lancamento;

import java.util.List;
import java.util.Optional;

public class Deposito {

    public List<Cliente> fazerDeposito(Banco bb, int numeroConta, double valor) {
        Optional<Conta> contaOptional = bb.getClientes().stream()
                .flatMap(cliente -> cliente.getContas().stream())
                .filter(conta -> conta.getNumeroConta() == numeroConta)
                .findFirst();

        if (contaOptional.isPresent()) {
            Conta conta = contaOptional.get();

            // Cria um novo lançamento de depósito
            Lancamento lancamento = new Lancamento(1, valor); // Tipo 1 para depósito

            // Adiciona o lançamento à lista de lançamentos da conta
            conta.getLancamentos().add(lancamento);

            // Atualiza o saldo da conta
            double novoSaldo = conta.getSaldo() + valor;
            conta.setSaldo(novoSaldo);

            // Exibe mensagem de sucesso (opcional)
            System.out.println("Depósito de R$" + valor + " realizado com sucesso.");

        } else {
            // Exibe mensagem de conta não encontrada (opcional)
            System.out.println("Conta não encontrada.");
        }

        return bb.getClientes();
    }
}
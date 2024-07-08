package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    public static int numeroCliente = 1;
    private List<Conta> contas;
    private String nomeCliente;

    public Cliente(String nomeCliente) {
        this.contas = new ArrayList<>();
        this.nomeCliente = nomeCliente;
        this.numeroCliente++;
    }

    public List<Conta> getContas() {
        return this.contas;
    }

    public int getNumeroCliente() {
        return this.numeroCliente;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

}

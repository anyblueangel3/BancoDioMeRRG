package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    public static int numeroCliente = 0;
    private int numeroCliente2;
    private List<Conta> contas;
    private String nomeCliente;

    public Cliente(String nomeCliente) {
        this.contas = new ArrayList<>();
        this.nomeCliente = nomeCliente;
        this.numeroCliente++;
        this.numeroCliente2 = this.numeroCliente;
        System.out.println("numeroCliente: " + this.numeroCliente);
    }

    public List<Conta> getContas() {
        return this.contas;
    }

    public int getNumeroCliente() {
        return this.numeroCliente;
    }

    public int getNumeroCliente2() {
        return this.numeroCliente2;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

}

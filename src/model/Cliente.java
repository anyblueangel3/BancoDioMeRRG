package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    public static int numeroCliente = 0;
    private List<Conta> contas;
    private String nome;

    public Cliente(int conta, String nome) {
        this.contas = new ArrayList<>();
        this.nome = nome;
        this.numeroCliente += 1;
    }

    public List<Conta> getContas() {
        return contas;
    }

}

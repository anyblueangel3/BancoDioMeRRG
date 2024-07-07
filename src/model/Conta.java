package model;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private int numeroConta;
    private double saldo;
    private List<Lancamento> lancamentos;

    public Conta(int numeroConta) {
        setNumeroConta(numeroConta);
        setSaldo(0);
        this.lancamentos = new ArrayList<>();
    }

    public int getNumeroConta() {
        return this.numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public List<Lancamento> getLancamentos() {
        return this.lancamentos;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

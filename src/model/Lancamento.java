package model;

public class Lancamento {

    private static int numeroLancamento = 0;
    private int numeroLancamento2;
    private int tipoLancamento; // 1 para deposito
    private double valorLancamento;

    public Lancamento(int tipoLancamento, double valorLancamento) {
        this.numeroLancamento++;
        this.numeroLancamento2 = this.numeroLancamento;
        this.tipoLancamento = tipoLancamento;
        this.valorLancamento = valorLancamento;
    }

    public int getNumeroLancamento2() {
        return this.numeroLancamento2;
    }

    public void setValor(double valorLancamento) {
        this.valorLancamento = valorLancamento;
    }

    public void setNumeroLancamento(int numeroLancamento) {
        this.numeroLancamento = numeroLancamento;
    }

    public void setTipoLancamento(int tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public double getValorLancamento() {
        return this.valorLancamento;
    }

    public int getNumeroLancamento() {
        return this.numeroLancamento;
    }

    public int getTipoLancamento() {
        return this.tipoLancamento;
    }

}

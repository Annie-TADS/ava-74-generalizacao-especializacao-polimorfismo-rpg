package model;

public abstract class Escudo extends Equipamento {
    protected final int taxaAbsorcao;
    protected final int taxaDeteriorizacao;
    protected int integridade = 100;

    protected Escudo(int taxaAbsorcao, int taxaDeteriorizacao, String nome) {
        super(nome);
        this.taxaAbsorcao = taxaAbsorcao;
        this.taxaDeteriorizacao = taxaDeteriorizacao;
    }

    public int getTaxaAbsorcao() {
        return taxaAbsorcao;
    }

    public int getTaxaDeteriorizacao() {
        return taxaDeteriorizacao;
    }

    public int getIntegridade() {
        return integridade;
    }

    public int reduzirImpacto(int danoLevado) {
        deterioraEscudo(danoLevado);
        return (int)Math.floor(danoLevado * (taxaAbsorcao / 10d));
    }

    private void deterioraEscudo(int danoLevado) {
        integridade -= (int)Math.ceil(danoLevado * ((10 + taxaDeteriorizacao) / 10d));

        if (integridade <= 0) {
            //System.out.println(this + " quebrou");
            this.getDono().largaEscudo();
        }
    }
}

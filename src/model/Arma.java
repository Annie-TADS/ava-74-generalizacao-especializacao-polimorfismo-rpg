package model;

import enums.TipoDano;

public abstract class Arma extends Equipamento {
    private final int dano;
    private final TipoDano tipoDano;

    protected Arma(int dano, TipoDano tipoDano, String nome) {
        super(nome);
        this.tipoDano = tipoDano;
        this.dano = dano;
    }
    
    public int getDano() {
        return dano;
    }

    public int calcularDano() {
        switch (tipoDano) {
            case FISICO:
                return (int)Math.ceil(getDano() * (getDono().getForca() / 10d));
            case DISTANTE:
                return (int)Math.ceil(getDano() * (getDono().getPontaria() / 10d));
             case MAGICO:
                return (int)Math.ceil(getDano() * (getDono().getMagica() / 10d));
             default:
                return 0;
        }
    }
}

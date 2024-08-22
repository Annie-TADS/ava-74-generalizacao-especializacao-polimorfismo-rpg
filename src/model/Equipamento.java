package model;

public abstract class Equipamento {
    private final String nome;
    private Personagem dono;

    protected Equipamento(String nome) {
        this.nome = nome;
    }

    public void setDono(Personagem dono) {
        this.dono = dono;
    }

    public void serLargado() {
        this.dono = null;
    }

    protected Personagem getDono() {
        return this.dono;
    }

    public boolean temDono() {
        return (this.dono != null);
    }

    @Override
    public String toString() {
        return nome;
    }
    
}

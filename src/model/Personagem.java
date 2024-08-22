package model;

import enums.Pronome;

public abstract class Personagem {
    private final int forca;
    private final int pontaria;
    private final int magica;
    private final String classe; 
    private final Pronome pronome; 
    protected double saude = 100d;
    protected Arma armaPrincipal;
    protected Arma armaSecundaria;
    protected Escudo escudo;

    protected Personagem(int forca, int pontaria, int magica, String classe, Pronome pronome) {
        this.forca = forca;
        this.pontaria = pontaria;
        this.magica = magica;
        this.classe = classe;
        this.pronome = pronome;
    }

    public int getForca() {
        return forca;
    }

    public int getPontaria() {
        return pontaria;
    }

    public int getMagica() {
        return magica;
    }

    public double getSaude() {
        return saude;
    }

    public Arma getArmaPrincipal() {
        return armaPrincipal;
    }

    public Arma getArmaSecundaria() {
        return armaSecundaria;
    }

    public Escudo getEscudo() {
        return escudo;
    }
    
    public boolean estaVivo() {
        return saude > 0d;
    }
    
    public void pegaArma(Arma arma) {
        if (arma.temDono()) {
            return;
        }

        if (estaDesarmado()) {
            //System.out.println(classe + " pega " + arma);
            armaPrincipal = arma;
            arma.setDono(this);
        } else if (!armaPrincipal.equals(arma)) {
            //System.out.println(classe + " pega " + arma + ", guardando: " + armaPrincipal);
            armaSecundaria = armaPrincipal;
            armaPrincipal = arma;
            arma.setDono(this);
        }
    }
    
    public void pegaEscudo(Escudo escudo) {
        if (escudo.temDono() || escudo.getIntegridade() <= 0) {
            return;
        }

        if (this.escudo == null) {
            //System.out.println(classe + " pega " + escudo);
            this.escudo = escudo;
            escudo.setDono(this);
        }
    }

    public void trocaArma() {
            if (armaPrincipal != null && armaSecundaria != null) {
                //System.out.println(classe + " troca para " + armaSecundaria + ", guardando: " + armaPrincipal);
                Arma armaTemp = armaPrincipal;

                armaPrincipal = armaSecundaria;
                armaSecundaria = armaTemp;
        }
    }

    public void largaArma() {
        if (armaPrincipal != null) {
            //System.out.println(classe + " larga " + armaPrincipal + (armaSecundaria == null ? "" : ", equipando: " + armaSecundaria));
            armaPrincipal.serLargado();
        }

        armaPrincipal = armaSecundaria;
        armaSecundaria = null;
    }

    public void largaEscudo() {
        if (escudo != null) {
            //System.out.println(classe + " larga " + escudo);
            escudo.serLargado();
        }

        escudo = null;
    }

    public void ataca(Personagem personagem) {
        if (estaDesarmado()) {
            return;
        }
        int danoCausado = armaPrincipal.calcularDano();

        //System.out.println(classe + " ataca " + personagem.classe + " com " + armaPrincipal);
        personagem.perderVida(danoCausado);
    }

    protected void perderVida(int danoRecebido) {
        int danoLevado = danoRecebido;

        if (this.escudo != null) {
            danoLevado = escudo.reduzirImpacto(danoRecebido);
        }
        this.saude -= danoLevado;
        if (this.saude > 0) {
            //System.out.println(classe + " perde " + danoLevado + " pontos de vida (" + (this.saude) + "/100)");
        } else {
            //System.out.println(classe + " foi mort"+getLetraPronome());
        }
    }

    public boolean estaDesarmado() {
        return (armaPrincipal == null);
    }

    @Override
    public String toString() {
        String escudoTexto = " sem escudo";
        if (escudo != null) {
            escudoTexto = " com " + escudo;
        }

        char letraPronome = getLetraPronome();

        String armaTexto = "desarmad"+letraPronome;
        if (armaPrincipal != null) {
            armaTexto = "armad"+ letraPronome + " com " + armaPrincipal;
        }

        return classe + escudoTexto + " e " + armaTexto;
    }
    
    private char getLetraPronome() {
        switch (pronome) {
            case MASCULINO:
                return 'o';
            case FEMININO:
                return 'a';
            default:
                return 'e';
        }
    }    
}
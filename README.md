# 7.4 // Generalização, Especialização e Polimorfismo // RPG

Use este link do GitHub Classroom para ter sua cópia alterável deste repositório: <https://classroom.github.com/a/hX1Fu_GJ>

Implementar respeitando os fundamentos de Orientação a Objetos.

**Tópicos desta atividade:** generalização, super classes, classes abstratas, especialização, classes concretas e polimorfismo.

---

As regras apresentadas nos casos de teste aprensentam conceitos de herança e agregação. Algumas classes são estendidas (`extends`) subclassificando comportamentos padrão e outras são agregadas e desagregadas (associações temporárias). Cada objeto tem características próprias. A classe `Personagem` e suas subclasses não podem estar acopladas (fazer menção) as classes concretas de `Escudo` e `Arma`, tais como `EscudoFerro`, ..., `Martelo`, ..., porém podem ter referência às classes abstratas `Escudo` e `Arma`. Vale o mesmo para as classes novas que forem criadas. Considere os seguintes casos de teste:


```java
Personagem ogro = new Ogro();
Personagem arqueiro = new Arqueiro();
Personagem feiticeira = new Feiticeira();

// cada personagem se destaca em certa habilidade
// somado sempre 10
System.out.println(ogro.getForca() == 7);
System.out.println(ogro.getPontaria() == 2);
System.out.println(ogro.getMagica() == 1);

System.out.println(arqueiro.getForca() == 3);
System.out.println(arqueiro.getPontaria() == 5);
System.out.println(arqueiro.getMagica() == 2);

System.out.println(feiticeira.getForca() == 2);
System.out.println(feiticeira.getPontaria() == 2);
System.out.println(feiticeira.getMagica() == 6);

Personagem[] personagens = new Personagem[]{ogro,arqueiro,feiticeira};

System.out.println(ogro.toString().equals("Ogro sem escudo e desarmado"));
System.out.println(arqueiro.toString().equals("Arqueiro sem escudo e desarmado"));
System.out.println(feiticeira.toString().equals("Feiticeira sem escudo e desarmada"));

// todos os personagens partem com 100% de saúde, sem armas e escudos
for (Personagem p : personagens) System.out.println(p.getSaude() == 100);
for (Personagem p : personagens) System.out.println(p.estaVivo() == true);
for (Personagem p : personagens) System.out.println(p.getArmaPrincipal() == null);
for (Personagem p : personagens) System.out.println(p.getArmaSecundaria() == null);
for (Personagem p : personagens) System.out.println(p.getEscudo() == null);

// três exemplares de armas
Arma martelo  = new Martelo();
Arma arco     = new Arco();
Arma poMagico = new PoMagico();

// cada arma causa certo dano // sempre entre 10 e 20
System.out.println(martelo.getDano() == 19);
System.out.println(arco.getDano() == 13);
System.out.println(poMagico.getDano() == 15);

// personagem pega uma arma
ogro.pegaArma(martelo);

// sempre é principal
System.out.println(ogro.getArmaPrincipal() == martelo);
System.out.println(ogro.getArmaSecundaria() == null);
System.out.println(ogro.toString().equals("Ogro sem escudo e armado com Martelo"));

// não pode pegar duas vezes a mesma arma
ogro.pegaArma(martelo);

System.out.println(ogro.getArmaPrincipal() == martelo);
System.out.println(ogro.getArmaSecundaria() == null);

// como não há arma secundária não pode trocar
ogro.trocaArma();

System.out.println(ogro.getArmaPrincipal() == martelo);
System.out.println(ogro.getArmaSecundaria() == null);

// pode pegar outra arma que passa a ser a principal
ogro.pegaArma(arco);

System.out.println(ogro.getArmaPrincipal() == arco);
System.out.println(ogro.getArmaSecundaria() == martelo);
System.out.println(ogro.toString().equals("Ogro sem escudo e armado com Arco"));

// com duas armas pode-se inverter
ogro.trocaArma();

System.out.println(ogro.getArmaPrincipal() == martelo);
System.out.println(ogro.getArmaSecundaria() == arco);

// sempre é largada a principal e a secundária passa para principal
ogro.largaArma();

System.out.println(ogro.getArmaPrincipal() == arco);
System.out.println(ogro.getArmaSecundaria() == null);

// pode largar todas as armas (sem armas não muda o estado)
ogro.largaArma();

System.out.println(ogro.getArmaPrincipal() == null);
System.out.println(ogro.getArmaSecundaria() == null);

// sem arma não faz diferença
ogro.largaArma();

System.out.println(ogro.getArmaPrincipal() == null);
System.out.println(ogro.getArmaSecundaria() == null);

// Ogro equipado com Martelo
ogro.pegaArma(arco);
ogro.pegaArma(martelo);

System.out.println(ogro.getArmaPrincipal() == martelo);
System.out.println(ogro.getArmaSecundaria() == arco);

// Personagem ataca outro
System.out.println(arqueiro.getSaude() == 100);
ogro.ataca(arqueiro);

// Ogro causa 70% de dano 19 do Martelo
// sempre arrendondado para cima: 0.7 * 19 = 13.3 = 14
// logo Arqueiro cai 14 em saúde
System.out.println(arqueiro.getSaude() == 86);
ogro.trocaArma(); // Ogro está com Arco agora
ogro.ataca(arqueiro);

// Ogro causa 20% de dano 13 do Arco: 0.2 * 13 = 2.6 = 3
// logo Arqueiro cai 3 em saúde
System.out.println(arqueiro.getSaude() == 83);

// impossível pergar arma 1, já está com outro Personagem
System.out.println(arqueiro.estaDesarmado() == true);
arqueiro.pegaArma(martelo);
System.out.println(arqueiro.getArmaPrincipal() == null);
System.out.println(arqueiro.getArmaSecundaria() == null);
System.out.println(arqueiro.estaDesarmado() == true);

// a arma 3 está disponível
arqueiro.pegaArma(poMagico);
System.out.println(arqueiro.estaDesarmado() == false);
System.out.println(arqueiro.getArmaPrincipal() == poMagico);
System.out.println(arqueiro.getArmaSecundaria() == null);
System.out.println(arqueiro.toString().equals("Arqueiro sem escudo e armado com Pó Mágico"));

// os escudos
Escudo escudoFerro = new EscudoFerro();
Escudo escudoMadeira = new EscudoMadeira();
Escudo escudoMagico = new EscudoMagico();

System.out.println(escudoFerro.getTaxaAbsorcao() == 5); // absorve 50% impacto
System.out.println(escudoMadeira.getTaxaAbsorcao() == 6); // absorve 60% impacto
System.out.println(escudoMagico.getTaxaAbsorcao() == 7); // absorve 70% impacto

// todos são íntegros inicialmente
System.out.println(escudoFerro.getIntegridade() == 100);
System.out.println(escudoMadeira.getIntegridade() == 100);
System.out.println(escudoMagico.getIntegridade() == 100);

// taxa de deriorização (queda da integridade com a força do impacto)
System.out.println(escudoFerro.getTaxaDeteriorizacao() == 5); // deteriora 150% do dano
System.out.println(escudoMadeira.getTaxaDeteriorizacao() == 7); // deteriora 170% do dano
System.out.println(escudoMagico.getTaxaDeteriorizacao() == 10); // deteriora 200% do dano

// nenhum personagem têm escudo inicialmente
for (Personagem p : personagens) System.out.println(p.getEscudo() == null);

// personagem pega escudo
System.out.println(ogro.getEscudo() == null);
ogro.pegaEscudo(escudoFerro);
System.out.println(ogro.getEscudo() == escudoFerro);
System.out.println(ogro.toString().equals("Ogro com Escudo de Ferro e armado com Arco"));

// outro personagem não pode pegar o mesmo escudo
System.out.println(feiticeira.getEscudo() == null);
feiticeira.pegaEscudo(escudoFerro);
System.out.println(feiticeira.getEscudo() == null);

// personagem larga escudo
System.out.println(ogro.getEscudo() == escudoFerro);
ogro.largaEscudo();
System.out.println(ogro.getEscudo() == null);

// agora outro pode pegar
System.out.println(feiticeira.getEscudo() == null);
feiticeira.pegaEscudo(escudoFerro);
System.out.println(feiticeira.getEscudo() == escudoFerro);

// Ogro ataca (com Arco) Feiticeira (com Escudo de Ferro)
// Ogro causa 20% de dano 13 do Arco: 0.2 * 13 = 2.6 = DANO == 3
// Como Feiticeira tem um Escudo de Ferro que absorve 50%,
// recebe 50% do Dano 3 = 1.5
// quando há escudo sempre arredonda para baixo, caindo 1 em saúde
ogro.ataca(feiticeira);
System.out.println(feiticeira.getSaude() == 99);

// o Escudo de Ferro deteriora 150% do Dano esperado de 3
// 1.5 * 3 = 4.5 = 5 (arredonda para cima sempre)
// decaíndo 5 pontos de integridade no escudo
System.out.println(feiticeira.getEscudo().getIntegridade() == 95);

// simulando batalha
Arma[] armas = new Arma[]{martelo, arco, poMagico};
Escudo[] escudos = new Escudo[]{escudoFerro, escudoMagico, escudoMagico};
int personagensVivos = personagens.length;
Personagem vencedor = null;
while (personagensVivos > 1) {
  Personagem p1 = personagens[(int)(Math.random() * personagens.length)];
  Personagem p2 = p1;
  while (p1 == p2) p2 = personagens[(int)(Math.random() * personagens.length)];
  p1.ataca(p2);
  if (Math.random() < 0.5) p1.largaArma(); // chance de 50% de largar a arma
  if (Math.random() < 0.5) p2.largaArma();
  if (Math.random() < 0.5) p1.pegaArma(armas[(int)(Math.random() * armas.length)]);
  if (Math.random() < 0.5) p2.pegaArma(armas[(int)(Math.random() * armas.length)]);
  if (Math.random() < 0.5) p1.largaEscudo();
  if (Math.random() < 0.5) p2.largaEscudo();
  if (Math.random() < 0.5) p1.pegaEscudo(escudos[(int)(Math.random() * escudos.length)]);
  if (Math.random() < 0.5) p2.pegaEscudo(escudos[(int)(Math.random() * escudos.length)]);
  personagensVivos = 0;
  for (Personagem p : personagens) if (p.estaVivo()) {
    vencedor = p;
    personagensVivos++;
  }
}
System.out.println(vencedor);

```


Implementar novas classes de objetos que realizem alguma interação no jogo. É necessário especificar pelo menos uma nova superclasse com pelo menos três filhas. Também é necessário definir novas agregações e suas regras. Além disso, também escrever os testes que mostrem o comportamento e estado esperado quando esses novos objetos são criados e usados, mostrando o estado a cada interação.



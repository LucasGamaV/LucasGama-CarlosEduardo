package jogo;

import java.util.Random;

public class Player {
    private int pontos_vida, pontos_dano, pontos_escudo;
    private Random gerador = new Random();
    private Room sala_atual;
    private boolean in_combat;

    public Player() {
        pontos_dano = 1;
        pontos_vida = 10;
        in_combat = false;
    }

    // overloading do construtor: Opcionalmente criamos com uma sala inicial
    public Player(Room sala_inicial) {
        pontos_dano = 1;
        pontos_vida = 10;
        sala_atual = sala_inicial;
        in_combat = false;
    }

    public Room getSala_atual() {
        return sala_atual;
    }

    public void entra_combate() {
        if (sala_atual.temMonstro()) {
            Monster monstro = sala_atual.getMonstro();
            if (monstro.getPontos_vida() <= 0)
                System.out.println("O monstro dessa sala já está morto.");
            else{
                System.out.println(ataque(monstro));
                System.out.println(monsterAtaque(monstro));
            }
        } else
            System.out.println("Não há monstros nessa sala.");
    }

    private String ataque(Monster monstro) {
        in_combat = true;
        String resultado = new String();
        // possível acerto
        if (gerador.nextBoolean()) {
            // possível crítico
            if (gerador.nextBoolean()) {
                monstro.sofre_dano(pontos_dano * 2);
                resultado = "Seu ataque foi crítico! Ele deu o dobro de dano (" + pontos_dano * 2 + ")";
            } else {
                monstro.sofre_dano(pontos_dano);
                resultado = "Seu ataque acertou o monstro!";
            }
            if (monstro.getPontos_vida() <= 0) {
                resultado += "\nO seu ataque matou o monstro.";
                in_combat = false;
            }
        } else
            resultado = "Que pena, seu ataque não acertou o monstro!";
        return resultado;
    }

    private String monsterAtaque(Monster monstro){
        String resultado = new String();
        // possível acerto
        if (gerador.nextBoolean()) {
            // possível crítico
            if (gerador.nextBoolean()) {
                sofre_dano(monstro.getPontos_dano() * 2);
                resultado = "O ataque do monstro foi crítico! Ele deu o dobro de dano (" + monstro.getPontos_dano() * 2 + ")";
            } else {
                sofre_dano(monstro.getPontos_dano());
                resultado = "O ataque do monstro te acertou!";
            }
            if (getPontos_vida() <= 0) {
                resultado += "\nVoce foi derrotado!.";
                in_combat = false;
            }
        } else
        resultado = "O monstro errou o ataque!";
        return resultado;
    }
    
    // Método que subtrai o dano sofrido dos pontos de vida do monstro
    public void sofre_dano(int dano_sofrido) {
        pontos_vida -= dano_sofrido;
    }

    public void recebe_cura(int quant_cura) {
        pontos_vida += quant_cura;
        if (pontos_vida > 10)
            // limitamos a quantidade máxima de HP a 10
            pontos_vida = 10;
    }

    public void aumenta_escudo(int armadura) {
        pontos_escudo += armadura;
        if (pontos_escudo > 4)
            pontos_escudo = 4;
    }

    /**
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    public void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Ir aonde?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = sala_atual.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Não há porta nessa direção!");
        } else if (in_combat) {
            System.out.println("Nada de fugir. É preciso primeiro derrotar o monstro!");
        } else if (sala_atual.temMonstro() && sala_atual.getMonstro().getPontos_vida()>0) {
            System.out.println("Há um monstro pra derrotar aqui!");
        } else {
            sala_atual = nextRoom;
            System.out.println(sala_atual.getLongDescription());
        }
    }

    /*
     * Método que retorna se o jogador está em combate
     * Um jogador está em combate quando existe um monstro vivo na mesma sala que
     * ele
     */
    public boolean isInCombat() {
        return in_combat;
    }

    public int getPontos_dano() {
        return pontos_dano;
    }

    public int getPontos_vida() {
        return pontos_vida;
    }

    public int getPontos_escudo() {
        return pontos_escudo;
    }

}
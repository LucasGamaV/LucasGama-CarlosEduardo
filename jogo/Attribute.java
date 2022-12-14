package jogo;

import java.util.HashMap;

public enum Attribute {
    MONSTER("Monstro"), CHEFE("Chefe"), CURA("Cura"), QUIZ("Quiz"), ARMADILHA("Armadilha"),
    NONE("Nada");

    private String valor;
    private String descricao;
    // As perguntas são um hash map cuja chave é uma pergunta e o valor é a resposta
    // correta;
    private HashMap<String, String> perguntas;

    Attribute(String valor) {
        this.valor = valor;
        setDescricao();
    }

    private void setDescricao() {
        switch (valor) {
            case "Monstro":
                descricao = "Você encontrou um monstro nessa sala! Ele tem 3 de vida e 1 de dano.\n Só após derrotar ele você pode sair dessa sala";
                break;
            case "Chefe":
                descricao = "Você encontrou o chefe! Para obter a safira, você precisa derrotá-lo!\nO chefe tem 8 de vida e 2 de dano! Tome cuidado";
                break;
            case "Cura":
                descricao = "Essa sala possui uma aura curadora!\nVocê automaticamente recebeu uma pequena quantia de cura!";
                break;
            case "Nada":
                descricao = "Uma sala comum com nada de especial";
                break;
            case "Quiz":
                perguntas = new HashMap<>();
                perguntas.put("O que é que todo mês tem, menos abril?", "a letra o");

                descricao = "Essa sala possui um erudita! Responda corretamente à charada e ganhe um feitiço de bola de fogo!";
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public String toString() {
        return valor;
    }
}
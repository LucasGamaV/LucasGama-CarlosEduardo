# Dungeon Run Aventure

## Alunos

* [Lucas Gama](https://github.com/LucasGamaV)
* [Carlos Eduardo]

## Mapa
<div align="center">
  <img src = "https://user-images.githubusercontent.com/114373261/192185899-2f604c91-f0ee-40eb-916f-2840468bfca9.jpeg" width = "600px">
</div>

## Descrição do jogo

O jogo descreve a aventura do jogador enquanto ele explora o Castelo Poxim em busca de uma safira. Ao longo dessa aventura, ele entra em diversas salas, e algumas delas contêm características especiais: Monstros, armadilhas, cura, quiz e o Chefão. O jogador só consegue a Safira após derrotar o Chefão. Algumas observações fundamentais sobre o jogo:
* Os comandos de movimentação são "ir" + "leste|oeste|norte|sul". O comando de ataque é "atacar".
* O jogador tem 10 de vida e 1 de dano. Os monstros tem 3 de vida e 1 de dano. O Chefão tem 8 de vida e 2 de dano.
* O combate jogador X monstro é baseado em turnos: O jogador ataca, podendo acertar ou errar (e se acertar, podendo dar dano crítico ou não) e, em seguida, o monstro daquela sala ataca, podendo acertar ou errar, mas sem chance de crítico.
* Um jogador só pode sair de uma sala que possui um monstro se o monstro da sala já tiver sido derrotado.
* Se o monstro de uma dada sala já foi derrotado, ele não vai spawnar mais.
* Existem salas com cura. Quando o jogador entra nelas, automaticamente recebe um cura, mas apenas na primeira vez que visitou tal sala.
* O limite superior da vida do jogador é 10. Nunca ultrapassa esse limite.
* A sala de armadilha sempre dá dano automático no momento que um jogador entra nela.
* A sala de quiz garante que, se o jogador acertar a pergunta, ele receberá uma armadura que ajuda a se proteger mais dos ataques de monstros.

## Lista de Tarefas

* Geral
  * Implementar o comportamento de outras salas com atributos: Quiz, Cura, Armadilha.
  * Implementar o sistemas de armadura na classe Player.
  * Finalizar a adaptação da classe `Game`
  * ~~Criar o mapa da Dungeon (Sugeito a mudanças)~~
  * ~~Implementar funcionalidade para o jogador só poder sair duma sala desde que não esteja mais em combate (Ou seja, derrotou o monstro daquela sala)~~
  * ~~Criar testes~~
  * ~~Criar classe Monster~~
  * ~~Criar classe Player~~
  * ~~Traduzir e adaptar textos do jogo~~
  * ~~Criar case para o comando ATTACK~~
  * ~~Implementar os atributos/caracteristicas especiais da sala como um ENUM~~
  * ~~Mover o comportamento da classe Game pra a classe Player~~

## Referências
* Objects First with Java: A Practical Introduction using BlueJ

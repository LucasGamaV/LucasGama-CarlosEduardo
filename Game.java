/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game. Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game. It also evaluates and
 * executes the commands that the parser returns.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
package jogo;

public class Game {
    private Parser parser;
    // private Room currentRoom;
    // Caso queiramos generalizar o jogo para N jogadores, poderíamos implementar
    // uma ArrayList aqui
    private Player jogador;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room entrada, sala_principal, corredor, biblioteca, tumba, arsenal, jardim, sala_espelhos, sala_escrituras,
                aquario, armazem_comida, igrejaAntiga, corredor2;

        // create the rooms
        entrada = new Room("na entrada da masmorra", Attribute.NONE);
        sala_principal = new Room("em uma grande sala logo após a entrada", Attribute.NONE);
        corredor = new Room("em um corredor", Attribute.MONSTER);
        corredor2 = new Room("em um corredor", Attribute.NONE);
        tumba = new Room("em uma tumba escura", Attribute.ARMADILHA);
        arsenal = new Room("em um arsenal com diversas armas", Attribute.MONSTER);
        jardim = new Room("em um jardim dentro de uma caverna", Attribute.CURA);
        sala_espelhos = new Room("em uma sala cheia de espelhos", Attribute.QUIZ);
        sala_escrituras = new Room("em uma sala cheia de símbolos e marcas", Attribute.QUIZ);
        aquario = new Room("em uma sala com o que parece ser um grande aquário", Attribute.CURA);
        armazem_comida = new Room("em um armazém de comida", Attribute.ARMADILHA);
        igrejaAntiga = new Room("em uma igreja semi-destruída", Attribute.CHEFE);
        biblioteca = new Room("em uma biblioteca abandonada há muito tempo", Attribute.MONSTER);

        // initialise room exits
        entrada.setExit("leste", sala_principal);

        sala_principal.setExit("sul", biblioteca);
        sala_principal.setExit("norte", tumba);
        sala_principal.setExit("leste", corredor2);
        sala_principal.setExit("oeste", entrada);

        biblioteca.setExit("sul", aquario);
        biblioteca.setExit("norte", sala_principal);
        biblioteca.setExit("leste", sala_espelhos);

        sala_espelhos.setExit("leste", corredor);
        sala_espelhos.setExit("oeste", biblioteca);

        aquario.setExit("norte", biblioteca);

        corredor2.setExit("leste", sala_escrituras);
        corredor2.setExit("oeste", sala_principal);

        sala_escrituras.setExit("oeste", corredor2);

        tumba.setExit("norte", arsenal);
        tumba.setExit("sul", sala_principal);

        arsenal.setExit("norte", jardim);
        arsenal.setExit("sul", tumba);
        arsenal.setExit("leste", armazem_comida);

        armazem_comida.setExit("oeste", arsenal);
        armazem_comida.setExit("leste", igrejaAntiga);

        jardim.setExit("sul", arsenal);

        igrejaAntiga.setExit("oeste", armazem_comida);

        jogador = new Player(entrada);
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Obrigado por jogar! Até.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Bem vindo ao Castelo Poxim");
        System.out.println("Castelo Poxim é um jogo room-to-room de aventura baseado no terminal");
        System.out.println("Digite '" + CommandWord.HELP + "' se precisar de ajuda.");
        System.out.println();
        System.out.println(jogador.getSala_atual().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("Não entendi o que você quis dizer...");
                break;

            case HELP:
                printHelp();
                break;

            case ATTACK:
                jogador.entra_combate();
                break;

            case GO:
                jogador.goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("Você está perdido, sozinho e caminha pelo castelo.");
        System.out.println();
        System.out.println("Seus comandos são:");
        parser.showCommands();
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Sair da onde?");
            return false;
        } else {
            return true; // sinaliza que queremos sair
        }
    }
}
package main;

import checker.Checker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import checker.CheckerConstants;
import fileio.CardInput;
import fileio.DecksInput;
import fileio.GameInput;
import fileio.Input;
import main.cards.Card;
import main.game.Deck;
import main.game.Game;
import main.game.PlayerInfo;
import main.heroes.Hero;
import main.utils.GameUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.CollationElementIterator;
import java.util.*;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(CheckerConstants.TESTS_PATH + filePath1),
                Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        Game.playerTwoWins = 0;
        Game.playerOneWins = 0;
        for (int i = 0; i < inputData.getGames().size(); i++) {
            GameInput gameInput = inputData.getGames().get(i);
            int deck1Index = gameInput.getStartGame().getPlayerOneDeckIdx();
            int deck2Index = gameInput.getStartGame().getPlayerTwoDeckIdx();

            ArrayList<CardInput> deckFirstPlayer = inputData.getPlayerOneDecks().getDecks().get(deck1Index);
            ArrayList<CardInput> deckSecondPlayer = inputData.getPlayerTwoDecks().getDecks().get(deck2Index);


            ArrayList<Card> playerOneDeck = GameUtils.cardsInputToCards(deckFirstPlayer);
            ArrayList<Card> playerTwoDeck = GameUtils.cardsInputToCards(deckSecondPlayer);

            Collections.shuffle(playerOneDeck, new Random(gameInput.getStartGame().getShuffleSeed()));
            Collections.shuffle(playerTwoDeck, new Random(gameInput.getStartGame().getShuffleSeed()));

            Hero playerOneHero = GameUtils.getHero(gameInput.getStartGame().getPlayerOneHero());
            Hero playerTwoHero = GameUtils.getHero(gameInput.getStartGame().getPlayerTwoHero());

            int playerTurn = gameInput.getStartGame().getStartingPlayer();

            Deck deck1 = new Deck(playerOneDeck, playerOneHero);
            Deck deck2 = new Deck(playerTwoDeck, playerTwoHero);

            PlayerInfo playerOne = new PlayerInfo(deck1, new ArrayList<>());
            PlayerInfo playerTwo = new PlayerInfo(deck2, new ArrayList<>());

            Game game = new Game(playerOne, playerTwo, playerTurn, gameInput.getActions());

            game.playGame(output);
        }

        //TODO add here the entry point to your implementation

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}

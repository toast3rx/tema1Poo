package main.game;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import lombok.Data;
import main.card.minion.Minion;
import main.output.ActionOutput;
import main.output.manager.ActionManager;
import java.util.ArrayList;

@Data
public class Game {

    private static int playerOneWins = 0;
    private static int playerTwoWins = 0;

    public static final int ROW_NUMBERS = 4;
    public static final int MAX_CARDS_ON_ROW = 5;

    public static final int PLAYER_ONE_FRONT_ROW = 2;
    public static final int PLAYER_ONE_BACK_ROW = 3;

    public static final int PLAYER_TWO_FRONT_ROW = 1;
    public static final int PLAYER_TWO_BACK_ROW = 0;

    public static final int HERO_HEALTH = 30;

    public static final int MAX_MANA = 10;

    private PlayerInfo playerOne;
    private PlayerInfo playerTwo;

    private ArrayList<ArrayList<Minion>> board;
    private ArrayList<ActionsInput> actions;
    private int playerTurn;

    private int roundNo = 0;


    public Game(final PlayerInfo playerOne,
                final PlayerInfo playerTwo,
                final int playerTurn,
                final ArrayList<ActionsInput> actions) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerTurn = playerTurn;
        this.actions = actions;

        this.board = new ArrayList<>();
        for (int i = 0; i < ROW_NUMBERS; i++) {
            this.board.add(new ArrayList<>());
        }
    }

    public static int getPlayerOneWins() {
        return playerOneWins;
    }

    public static void setPlayerOneWins(final int playerOneWins) {
        Game.playerOneWins = playerOneWins;
    }

    public static int getPlayerTwoWins() {
        return playerTwoWins;
    }

    public static void setPlayerTwoWins(final int playerTwoWins) {
        Game.playerTwoWins = playerTwoWins;
    }


    /**
     * Start a new game.
     * @param output jackson array node for printing the output
     */
    public void playGame(final ArrayNode output) {

        startNewRound();
        ActionManager actionManager = new ActionManager(this);

        for (ActionsInput action : actions) {
            ActionOutput actionOutput = actionManager.manageAction(action, output);

            if ((actionOutput.getGameEnded() == null)
                    && actionOutput.getCommand().equals(ActionCode.EMPTY)) {
                continue;
            }

            output.addPOJO(actionOutput);
        }
    }

    /**
     * Start a new round in current game
     */
    public void startNewRound() {
        this.roundNo++;

        this.playerOne.setEndedTurn(false);
        this.playerTwo.setEndedTurn(false);


        this.playerOne.drawCard();
        this.playerTwo.drawCard();

        this.playerOne.setMana(this.playerOne.getMana() + Math.min(roundNo, MAX_MANA));
        this.playerTwo.setMana(this.playerTwo.getMana() + Math.min(roundNo, MAX_MANA));
    }

}

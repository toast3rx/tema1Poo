package main.game;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.Coordinates;
import lombok.Data;
import main.Exceptions.InvalidPlacementException;
import main.cards.Card;
import main.cards.enviroment.EnvironmentCard;
import main.cards.minion.Minion;
import main.cards.minion.SpecialMinion.SpecialMinion;
import main.output.*;
import main.output.manager.ActionManager;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static main.game.ActionCode.*;


@Data
public class Game {

    public static int playerOneWins = 0;
    public static int playerTwoWins = 0;

    public final int ROW_NUMBERS = 4;
    public final int MAX_CARDS_ON_ROW = 5;

    private PlayerInfo playerOne;
    private PlayerInfo playerTwo;

    private ArrayList<ArrayList<Minion>> board;
    private ArrayList<ActionsInput> actions;
    private int playerTurn;

    private int roundNo = 0;


    public Game(PlayerInfo playerOne, PlayerInfo playerTwo, int playerTurn, ArrayList<ActionsInput> actions) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerTurn = playerTurn;
        this.actions = actions;

        this.board = new ArrayList<>();
        for (int i = 0; i < ROW_NUMBERS; i++) {
            this.board.add(new ArrayList<>());
        }
    }


    public void playGame(ArrayNode output) {

        startNewRound();
        ActionManager actionManager = new ActionManager(this);

        for (ActionsInput action : actions) {
            ActionOutput actionOutput = actionManager.manageAction(action, output);

            if ((actionOutput.getGameEnded() == null) && actionOutput.getCommand().equals(EMPTY)) {
                continue;
            }

            output.addPOJO(actionOutput);
        }
    }

    public void startNewRound() {
        this.roundNo++;

        this.playerOne.setEndedTurn(false);
        this.playerTwo.setEndedTurn(false);


        this.playerOne.drawCard();
        this.playerTwo.drawCard();

        this.playerOne.setMana(this.playerOne.getMana() + Math.min(roundNo, 10));
        this.playerTwo.setMana(this.playerTwo.getMana() + Math.min(roundNo, 10));
    }

}





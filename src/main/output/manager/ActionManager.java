package main.output.manager;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.Coordinates;
import lombok.Data;
import main.Exceptions.HeroDiedException;
import main.Exceptions.InvalidPlacementException;
import main.cards.Card;
import main.cards.enviroment.EnvironmentCard;
import main.cards.minion.Minion;
import main.cards.minion.SpecialMinion.SpecialMinion;
import main.game.Game;
import main.game.PlayerInfo;
import main.output.ActionOutput;

import java.util.ArrayList;

import static main.game.ActionCode.ATTACK_CARD_COMMAND;
import static main.game.ActionCode.ATTACK_HERO_COMMAND;
import static main.game.ActionCode.EMPTY;
import static main.game.ActionCode.END_TURN_COMMAND;
import static main.game.ActionCode.GAMES_PLAYED_COMMAND;
import static main.game.ActionCode.GET_CARDS_IN_HAND_COMMAND;
import static main.game.ActionCode.GET_CARDS_ON_TABLE;
import static main.game.ActionCode.GET_CARD_AT_INDEX_COMMAND;
import static main.game.ActionCode.GET_ENVIRONMENT_CARDS_IN_HAND_COMMAND;
import static main.game.ActionCode.GET_FROZEN_CARDS_COMMAND;
import static main.game.ActionCode.GET_HERO_PLAYER_COMMAND;
import static main.game.ActionCode.GET_PLAYER_DECK_COMMAND;
import static main.game.ActionCode.GET_PLAYER_MANA_COMMAND;
import static main.game.ActionCode.GET_PLAYER_TURN;
import static main.game.ActionCode.PLACE_CARD_COMMAND;
import static main.game.ActionCode.PLAYER_ONE_WINS_COMMAND;
import static main.game.ActionCode.PLAYER_TWO_WINS_COMMAND;
import static main.game.ActionCode.USE_CARD_ABILITY_COMMAND;
import static main.game.ActionCode.USE_ENVIRONMENT_COMMAND;
import static main.game.ActionCode.USE_HERO_ABILITY_COMMAND;


@Data
public class ActionManager {

    private Game game;

    public ActionManager(final Game game) {
        this.game = game;
    }

    /**
     * Return output of the selected action.
     * @param action The action to be executed.
     * @param output Jackson ArrayNode to add the output to.
     * @return The output of the action.
     */
    public ActionOutput manageAction(final ActionsInput action,
                                     final ArrayNode output) {

        ActionOutput actionOutput = null;

        switch (action.getCommand()) {
            case GET_PLAYER_DECK_COMMAND:
                PlayerInfo player = action.getPlayerIdx() == 1
                        ? game.getPlayerOne()
                        : game.getPlayerTwo();
                return new ActionOutput(action.getCommand(),
                        player.getDeck().getCards(),
                        action.getPlayerIdx());
            case GET_HERO_PLAYER_COMMAND:
                player = action.getPlayerIdx() == 1
                        ? game.getPlayerOne()
                        : game.getPlayerTwo();
                return new ActionOutput(action.getCommand(),
                        action.getPlayerIdx(),
                        player.getDeck().getHero());
            case GET_PLAYER_TURN:
                return new ActionOutput(action.getCommand(), game.getPlayerTurn());
            case PLACE_CARD_COMMAND:
                return placeCardAction(action.getHandIdx());
            case END_TURN_COMMAND:
                return endTurnAction();
            case GET_CARDS_IN_HAND_COMMAND:
                player = action.getPlayerIdx() == 1 ? game.getPlayerOne() : game.getPlayerTwo();
                return new ActionOutput(action.getCommand(),
                        player.getHand(),
                        action.getPlayerIdx());
            case GET_PLAYER_MANA_COMMAND:
                player = action.getPlayerIdx() == 1 ? game.getPlayerOne() : game.getPlayerTwo();
                return new ActionOutput(action.getCommand(),
                        player.getMana(),
                        action.getPlayerIdx());
            case GET_CARDS_ON_TABLE:
                return new ActionOutput(action.getCommand(), game.getBoard());
            case GET_ENVIRONMENT_CARDS_IN_HAND_COMMAND:
                return getEnvironmentCardsInHand(action.getPlayerIdx());
            case GET_FROZEN_CARDS_COMMAND:
                return getFrozenCardOnTable();
            case ATTACK_CARD_COMMAND:
                return attackCardAction(action.getCardAttacker(), action.getCardAttacked());
            case USE_CARD_ABILITY_COMMAND:
                return useAbilityAction(action.getCardAttacker(), action.getCardAttacked());
            case USE_ENVIRONMENT_COMMAND:
                return useEnvironmentAction(action.getHandIdx(), action.getAffectedRow());
            case GET_CARD_AT_INDEX_COMMAND:
                return getCardAtIndexAction(action.getX(), action.getY());
            case ATTACK_HERO_COMMAND:
                return attackHeroAction(action.getCardAttacker());
            case USE_HERO_ABILITY_COMMAND:
                return useHeroAbility(action.getAffectedRow());
            case GAMES_PLAYED_COMMAND:
                return getGamesPlayed();
            case PLAYER_ONE_WINS_COMMAND:
                return getPlayerWins(1);
            case PLAYER_TWO_WINS_COMMAND:
                return getPlayerWins(2);
            default:
                return new ActionOutput(action.getCommand());
        }
    }

    /**
     * Get the number of wins for a player.
     * @param playerIdx The player index.
     * @return The number of wins.
     */
    private ActionOutput getPlayerWins(final int playerIdx) {
        String playerWinsCmd = playerIdx == 1 ? PLAYER_ONE_WINS_COMMAND : PLAYER_TWO_WINS_COMMAND;
        ActionOutput actionOutput = new ActionOutput(playerWinsCmd);
        int playerWins = playerIdx == 1 ? Game.getPlayerOneWins() : Game.getPlayerTwoWins();
        actionOutput.setOutput(playerWins);
        return actionOutput;
    }

    /** Get the number of games played.
     *
     * @return The number of games played.
     */
    private ActionOutput getGamesPlayed() {
        ActionOutput actionOutput = new ActionOutput(GAMES_PLAYED_COMMAND);
        actionOutput.setOutput(Game.getPlayerOneWins() + Game.getPlayerTwoWins());

        return actionOutput;
    }

    /**
     * Use the hero ability on the specified row
     * @param affectedRow The row to use the ability on.
     * @return The output of the action.
     */
    private ActionOutput useHeroAbility(final int affectedRow) {

        PlayerInfo currentPlayer = game.getPlayerTurn() == 1
                ? game.getPlayerOne()
                : game.getPlayerTwo();
        try {
            currentPlayer.useHeroAbility(game, affectedRow);
            return new ActionOutput(EMPTY);
        } catch (Exception exception) {
            ActionOutput actionOutput = new ActionOutput(USE_HERO_ABILITY_COMMAND);
            actionOutput.setAffectedRow(affectedRow);
            actionOutput.setError(exception.getMessage());
            return actionOutput;
        }
    }

    /**
     * Attack the hero of the opponent.
     * @param cardAttacker The card that is attacking.
     * @return The output of the action.
     */
    private ActionOutput attackHeroAction(final Coordinates cardAttacker) {
        try {
            Minion attacker = game.getBoard().get(cardAttacker.getX()).get(cardAttacker.getY());
            attacker.attackHero(game);
            return new ActionOutput(EMPTY);
        } catch (HeroDiedException e) {
            int playerTurn = game.getPlayerTurn();
            if (playerTurn == 1) {
                Game.setPlayerOneWins(Game.getPlayerOneWins() + 1);
            } else {
                Game.setPlayerTwoWins(Game.getPlayerTwoWins() + 1);

            }
            ActionOutput endGame = new ActionOutput();
            System.out.println(e.getMessage());
            endGame.setGameEnded(e.getMessage());
            return endGame;
        } catch (Exception e) {

            return new ActionOutput(ATTACK_HERO_COMMAND, cardAttacker, e.getMessage());
        }
    }

    /**
     * Get all frozen cards from board
     * @return The output of the action.
     */
    private ActionOutput getFrozenCardOnTable() {
        ArrayList<Minion> frozenCards = new ArrayList<>();
        for (ArrayList<Minion> row : game.getBoard()) {
            for (Minion minion : row) {
                if (minion.isFrozen()) {
                    frozenCards.add(minion);
                }
            }
        }
        return new ActionOutput(GET_FROZEN_CARDS_COMMAND, frozenCards);
    }

    /**
     * Get the environment cards from a player's hand.
     * @param playerIdx The player index.
     * @return The output of the action.
     */
    private ActionOutput getEnvironmentCardsInHand(final int playerIdx) {
        ArrayList<Card> environmentCards = new ArrayList<>();

        if (playerIdx == 1) {
            for (Card card : game.getPlayerOne().getHand()) {
                if (card instanceof EnvironmentCard) {
                    environmentCards.add(card);
                }
            }
        } else {
            for (Card card : game.getPlayerTwo().getHand()) {
                if (card instanceof EnvironmentCard) {
                    environmentCards.add(card);
                }
            }
        }

        return new ActionOutput(GET_ENVIRONMENT_CARDS_IN_HAND_COMMAND,
                environmentCards,
                playerIdx);
    }

    /**
     * Get the card at the specified coordinates from board.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return The output of the action.
     */
    public ActionOutput getCardAtIndexAction(final int x, final int y) {

        try {
            Minion minion = game.getBoard().get(x).get(y);
            return new ActionOutput(GET_CARD_AT_INDEX_COMMAND, x, y, minion);
        } catch (IndexOutOfBoundsException exception) {
            return new ActionOutput(GET_CARD_AT_INDEX_COMMAND,
                    "No card available at that position.",
                    x,
                    y);
        }
    }

    /**
     * Place a card on the board
     * @param handIdx The index of the card in the hand.
     * @return The output of the action.
     */
    public ActionOutput placeCardAction(final int handIdx) {

        String error = null;

        PlayerInfo player = game.getPlayerTurn() == 1 ? game.getPlayerOne() : game.getPlayerTwo();


        try {
            player.placeCard(game, handIdx);
        } catch (InvalidPlacementException exception) {
            error = exception.getMessage();
        }

        if (error == null) {
            return new ActionOutput(EMPTY);
        }

        return new ActionOutput(PLACE_CARD_COMMAND, handIdx, error);
    }

    /**
     * Attack an opponent's minion.
     * @param cardAttacker The card that is attacking.
     * @param cardAttacked The card that is being attacked.
     * @return The output of the action.
     */
    public ActionOutput attackCardAction(final Coordinates cardAttacker,
                                         final Coordinates cardAttacked) {
        String error = "";

        Minion minionAttacker = game.getBoard().get(cardAttacker.getX()).get(cardAttacker.getY());

        try {
            minionAttacker.attack(game, cardAttacked.getX(), cardAttacked.getY());
            return new ActionOutput(EMPTY);
        } catch (Exception exception) {
            error = exception.getMessage();
            return new ActionOutput(ATTACK_CARD_COMMAND, cardAttacker, cardAttacked, error);
        }
    }

    /**
     * Use a card's ability.
     * @param cardAttacker The card that is using the ability.
     * @param cardAttacked The card that is being affected by the ability.
     * @return The output of the action.
     */
    public ActionOutput useAbilityAction(final Coordinates cardAttacker,
                                         final Coordinates cardAttacked) {
        String error = "";

        SpecialMinion minionAttacker =
                (SpecialMinion) game.getBoard().get(cardAttacker.getX()).get(cardAttacker.getY());

        try {
            minionAttacker.useAbility(game, cardAttacked.getX(), cardAttacked.getY());
            return new ActionOutput(EMPTY);
        } catch (Exception exception) {
            error = exception.getMessage();
            return new ActionOutput(USE_CARD_ABILITY_COMMAND, cardAttacker, cardAttacked, error);
        }
    }

    /**
     * Use an environment card from hand
     * @param handIdx The index of the card in the hand.
     * @param affectedRow The row that is affected by the card.
     * @return The output of the action.
     */
    public ActionOutput useEnvironmentAction(final int handIdx, final int affectedRow) {
        PlayerInfo player = game.getPlayerTurn() == 1 ? game.getPlayerOne() : game.getPlayerTwo();

        String error;

        try {
            player.useEnvironmentCard(game, handIdx, affectedRow);
            return new ActionOutput(EMPTY);
        } catch (Exception exception) {
            error = exception.getMessage();
            return new ActionOutput(USE_ENVIRONMENT_COMMAND, handIdx, affectedRow, error);
        }
    }

    /**
     * End a player's turn.
     * @return The output of the action.
     */
    public ActionOutput endTurnAction() {
        if (game.getPlayerTurn() == 1) {
            game.getPlayerOne().setEndedTurn(true);
            game.setPlayerTurn(2);
            game.getPlayerOne().getDeck().getHero().setUsedTurn(false);

            for (Minion minion : game.getBoard().get(Game.PLAYER_ONE_BACK_ROW)) {
                minion.setFrozen(false);
                minion.setUsedTurn(false);
            }


            for (Minion minion : game.getBoard().get(Game.PLAYER_ONE_FRONT_ROW)) {
                minion.setFrozen(false);
                minion.setUsedTurn(false);
            }


        } else {
            game.getPlayerTwo().setEndedTurn(true);
            game.setPlayerTurn(1);
            game.getPlayerTwo().getDeck().getHero().setUsedTurn(false);

            for (Minion minion : game.getBoard().get(Game.PLAYER_TWO_BACK_ROW)) {
                minion.setFrozen(false);
                minion.setUsedTurn(false);
            }

            for (Minion minion : game.getBoard().get(Game.PLAYER_TWO_FRONT_ROW)) {
                minion.setFrozen(false);
                minion.setUsedTurn(false);
            }
        }

        if (game.getPlayerOne().isEndedTurn() && game.getPlayerTwo().isEndedTurn()) {
            game.startNewRound();
        }

        return new ActionOutput(EMPTY);
    }
}

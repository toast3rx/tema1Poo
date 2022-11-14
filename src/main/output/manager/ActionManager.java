package main.output.manager;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.Coordinates;
import lombok.Data;
import main.Exceptions.InvalidPlacementException;
import main.cards.Card;
import main.cards.enviroment.EnvironmentCard;
import main.cards.minion.Minion;
import main.cards.minion.SpecialMinion.SpecialMinion;
import main.game.Game;
import main.game.PlayerInfo;
import main.output.ActionOutput;

import java.util.ArrayList;

import static main.game.ActionCode.*;

public @Data
class ActionManager {

    private Game game;

    public ActionManager(Game game) {
        this.game = game;
    }

    public ActionOutput manageAction(ActionsInput action, ArrayNode output) {

        ActionOutput actionOutput = null;

        switch (action.getCommand()) {
            case GET_PLAYER_DECK_COMMAND:
                return new ActionOutput(action.getCommand(), action.getPlayerIdx() == 1 ? game.getPlayerOne().getDeck().getCards() : game.getPlayerTwo().getDeck().getCards(), action.getPlayerIdx());
            case GET_HERO_PLAYER_COMMAND:
                return new ActionOutput(action.getCommand(), action.getPlayerIdx(), action.getPlayerIdx() == 1 ? game.getPlayerOne().getDeck().getHero() : game.getPlayerTwo().getDeck().getHero());
            case GET_PLAYER_TURN:
                return new ActionOutput(action.getCommand(), game.getPlayerTurn());
            case PLACE_CARD_COMMAND:
                return placeCardAction(action.getHandIdx());
            case END_TURN_COMMAND:
                return endTurnAction();
            case GET_CARDS_IN_HAND_COMMAND:
                return new ActionOutput(action.getCommand(), action.getPlayerIdx() == 1 ? game.getPlayerOne().getHand() : game.getPlayerTwo().getHand(), action.getPlayerIdx());
            case GET_PLAYER_MANA_COMMAND:
                return new ActionOutput(action.getCommand(), action.getPlayerIdx() == 1 ? game.getPlayerOne().getMana() : game.getPlayerTwo().getMana(), action.getPlayerIdx());
            case GET_CARDS_ON_TABLE:
                return new ActionOutput(action.getCommand(), game.getBoard());
            case GET_ENVIRONMENT_CARDS_IN_HAND_COMMAND:
                return getEnvironmentCardsInHand(action.getPlayerIdx());
            case GET_FROZEN_CARDS_COMMAND:
                return getFrozenCardOnTable();
            case ATTACK_CARD_COMMAND:
                return attackCardAction(action.getCardAttacker(), action.getCardAttacked());
            case USE_CARD_ABILITY_COMMAND:
                return  useAbilityAction(action.getCardAttacker(), action.getCardAttacked());
            case USE_ENVIRONMENT_COMMAND:
                return useEnvironmentAction(action.getHandIdx(), action.getAffectedRow());
            case GET_CARD_AT_INDEX_COMMAND:
                return getCardAtIndexAction(action.getX(), action.getY());
            default:
                return new ActionOutput(action.getCommand());
        }
    }

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

    private ActionOutput getEnvironmentCardsInHand(int playerIdx) {
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

        return new ActionOutput(GET_ENVIRONMENT_CARDS_IN_HAND_COMMAND, environmentCards, playerIdx);
    }

    public ActionOutput getCardAtIndexAction(int x, int y) {

        try {
            Minion minion = game.getBoard().get(x).get(y);
            return new ActionOutput(GET_CARD_AT_INDEX_COMMAND, x, y, minion);
        } catch (IndexOutOfBoundsException exception) {
            return new ActionOutput(GET_CARD_AT_INDEX_COMMAND, "No card available at that position.", x, y);
        }
    }
    public ActionOutput placeCardAction(int handIdx) {

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

    public ActionOutput attackCardAction(Coordinates cardAttacker, Coordinates cardAttacked) {
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

    public ActionOutput useAbilityAction(Coordinates cardAttacker, Coordinates cardAttacked) {
        String error = "";

        SpecialMinion minionAttacker = (SpecialMinion) game.getBoard().get(cardAttacker.getX()).get(cardAttacker.getY());

        try {
            minionAttacker.useAbility(game, cardAttacked.getX(), cardAttacked.getY());
            return new ActionOutput(EMPTY);
        } catch (Exception exception) {
            error = exception.getMessage();
            return new ActionOutput(USE_CARD_ABILITY_COMMAND, cardAttacker, cardAttacked, error);
        }
    }

    public ActionOutput useEnvironmentAction(int handIdx, int affectedRow) {
        PlayerInfo player = game.getPlayerTurn() == 1 ? game.getPlayerOne() : game.getPlayerTwo();

        String error = null;

        try {
            player.useEnvironmentCard(game, handIdx, affectedRow);
            return new ActionOutput(EMPTY);
        } catch (Exception exception) {
            error = exception.getMessage();
            return new ActionOutput(USE_ENVIRONMENT_COMMAND, handIdx, affectedRow, error);
        }
    }

    public ActionOutput endTurnAction() {
        if (game.getPlayerTurn() == 1) {
            game.getPlayerOne().setEndedTurn(true);
            game.setPlayerTurn(2);

            for (Minion minion : game.getBoard().get(3)) {
                minion.setFrozen(false);
                minion.setUsedTurn(false);
            }

            for (Minion minion : game.getBoard().get(2)) {
                minion.setFrozen(false);
                minion.setUsedTurn(false);
            }


        } else {
            game.getPlayerTwo().setEndedTurn(true);
            game.setPlayerTurn(1);

            for (Minion minion : game.getBoard().get(0)) {
                minion.setFrozen(false);
                minion.setUsedTurn(false);
            }

            for (Minion minion : game.getBoard().get(1)) {
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


package main.game;

import lombok.Data;
import main.Exceptions.InvalidPlacementException;
import main.cards.Card;
import main.cards.enviroment.EnvironmentCard;
import main.cards.minion.BackRow;
import main.cards.minion.Minion;
import main.heroes.Hero;

import java.util.ArrayList;

@Data
public class PlayerInfo {
    private Deck deck;
    private ArrayList<Card> hand;
    private int mana = 0;

    private boolean endedTurn = false;

    public PlayerInfo(final Deck deck, final ArrayList<Card> hand) {
        this.deck = deck;
        this.hand = hand;
    }

    /**
     * Use an environment card from hand.
     * @param game Game containing all the information.
     * @param handIndex Index of the card in hand.
     * @param rowIndex Index of the row that will be affected.
     * @throws Exception If the card cannot be used
     */
    public void useEnvironmentCard(final Game game,
                                   final int handIndex,
                                   final int rowIndex) throws Exception {
        Card card = getHand().get(handIndex);

        if (!(card instanceof EnvironmentCard environmentCard)) {
            throw new Exception("Chosen card is not of type environment.");
        }

        if (card.getMana() > getMana()) {
            throw new Exception("Not enough mana to use environment card.");
        }


        // get front row based on player
        int frontRowIndex = game.getPlayerTurn() == 1
                ? Game.PLAYER_ONE_FRONT_ROW
                : Game.PLAYER_TWO_FRONT_ROW;

        //get back row based on player
        int backRowIndex = game.getPlayerTurn() == 1
                ? Game.PLAYER_ONE_BACK_ROW
                : Game.PLAYER_TWO_BACK_ROW;

        if (rowIndex == frontRowIndex || rowIndex == backRowIndex) {
            throw new Exception("Chosen row does not belong to the enemy.");
        }

        try {
            environmentCard.useAbility(game, rowIndex);
            getHand().remove(environmentCard);
            setMana(getMana() - card.getMana());
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }

    }

    /**
     * Place a minion on the board
     * @param game Game containing all the information.
     * @param handIndex Index of the card in hand.
     * @throws InvalidPlacementException If the minion cannot be placed.
     */
    public void placeCard(final Game game,
                          final int handIndex) throws InvalidPlacementException {

        Card card = getHand().get(handIndex);

        if (card instanceof EnvironmentCard) {
            throw new InvalidPlacementException("Cannot place environment card on table.");
        }

        if (card.getMana() > this.getMana()) {
            throw new InvalidPlacementException("Not enough mana to place card on table.");
        }

        ArrayList<ArrayList<Minion>> board = game.getBoard();


        // get front row based on player
        int frontRowIndex = game.getPlayerTurn() == 1
                ? Game.PLAYER_ONE_FRONT_ROW
                : Game.PLAYER_TWO_FRONT_ROW;

        //get back row based on player
        int backRowIndex = game.getPlayerTurn() == 1
                ? Game.PLAYER_ONE_BACK_ROW
                : Game.PLAYER_TWO_BACK_ROW;

        // get row based on card placement
        int rowIndex = card instanceof BackRow ? backRowIndex : frontRowIndex;

        if (board.get(rowIndex).size() == Game.MAX_CARDS_ON_ROW) {
            throw new InvalidPlacementException("Cannot place card on table since row is full.");
        }

        setMana(getMana() - card.getMana());
        board.get(rowIndex).add((Minion) card);
        hand.remove(handIndex);
    }

    /**
     * Draw a card from the deck in hand.
     */
    public void drawCard() {

        try {
        Card card = deck.getCards().remove(0);
        hand.add(card);

        } catch (Exception e) {
            System.out.println("Deck is empty");
        }
    }

    /**
     * Use hero special ability.
     * @param game Game containing all the information.
     * @param rowIdx Index of the row that will be affected.
     * @throws Exception If the hero cannot use the ability.
     */
    public void useHeroAbility(final Game game, final int rowIdx) throws Exception {
        Hero hero  = getDeck().getHero();
        if (hero.getMana() > getMana()) {
            throw new Exception("Not enough mana to use hero's ability.");
        }

        if (hero.isUsedTurn()) {
            throw new Exception("Hero has already attacked this turn.");
        }

        try {
            hero.useAbility(game, rowIdx);
            hero.setUsedTurn(true);
            setMana(getMana() - hero.getMana());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Get hand of cards
     * @return ArrayList of cards
     */
    public ArrayList<Card> getHand() {
        return this.hand;
    }
}

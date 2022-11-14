package main.game;

import lombok.Data;
import main.Exceptions.InvalidPlacementException;
import main.cards.Card;
import main.cards.enviroment.EnvironmentCard;
import main.cards.minion.BackRow;
import main.cards.minion.Minion;

import java.util.ArrayList;

@Data
public class PlayerInfo {
    private Deck deck;
    private ArrayList<Card> hand;
    private int mana = 0;

    private boolean endedTurn = false;

    public PlayerInfo(Deck deck, ArrayList<Card> hand) {
        this.deck = deck;
        this.hand = hand;
    }


    public void useEnvironmentCard(Game game, int handIndex, int rowIndex) throws Exception {
        Card card = getHand().get(handIndex);

        if (! (card instanceof EnvironmentCard environmentCard)) {
            throw new Exception("Chosen card is not of type environment.");
        }

        if (card.getMana() > getMana())
            throw new Exception("Not enough mana to use environment card.");


        // get front row based on player
        int frontRowIndex = game.getPlayerTurn() == 1 ? 2 : 1;

        //get back row based on player
        int backRowIndex = game.getPlayerTurn() == 1 ? 3 : 0;

        if (rowIndex == frontRowIndex || rowIndex == backRowIndex)
            throw new Exception("Chosen row does not belong to the enemy.");

        try {
            environmentCard.useAbility(game, rowIndex);
            getHand().remove(environmentCard);
            setMana(getMana() - card.getMana());
        }
        catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }

    }

    public void placeCard(Game game, int handIndex) throws InvalidPlacementException {

        Card card = getHand().get(handIndex);

        if (card instanceof EnvironmentCard)
            throw new InvalidPlacementException("Cannot place environment card on table.");

        if (card.getMana() > this.getMana()) {
            throw new InvalidPlacementException("Not enough mana to place card on table.");
        }

        ArrayList<ArrayList<Minion>> board = game.getBoard();


        // get front row based on player
        int frontRowIndex = game.getPlayerTurn() == 1 ? 2 : 1;

        //get back row based on player
        int backRowIndex = game.getPlayerTurn() == 1 ? 3 : 0;

        // get row based on card placement
        int rowIndex = card instanceof BackRow ? backRowIndex : frontRowIndex;

        if (board.get(rowIndex).size() == game.MAX_CARDS_ON_ROW) {

            throw new InvalidPlacementException("Cannot place card on table since row is full.");
        }

        setMana(getMana() - card.getMana());
        board.get(rowIndex).add((Minion) card);
        hand.remove(handIndex);
    }

    public void drawCard() {

        try {
        Card card = deck.getCards().remove(0);
        hand.add(card);

        }catch (Exception e){
            System.out.println("Deck is empty");
        }
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }


    public void setMana(int mana) {
        this.mana = mana;
    }
}

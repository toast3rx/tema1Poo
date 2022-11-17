package main.game;

import lombok.Data;
import main.cards.Card;
import main.heroes.Hero;
import java.util.ArrayList;

@Data
public class Deck {

    private ArrayList<Card> cards;
    private Hero hero;

    public Deck(final ArrayList<Card> cards, final Hero hero) {
        this.cards = cards;
        this.hero = hero;
    }
}

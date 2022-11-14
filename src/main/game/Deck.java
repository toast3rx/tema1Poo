package main.game;

import lombok.Data;
import main.cards.Card;
import main.heroes.Hero;

import java.util.ArrayList;

@Data
public class Deck {

    ArrayList<Card> cards;
    Hero hero;

    public Deck(ArrayList<Card> cards, Hero hero) {
        this.cards = cards;
        this.hero = hero;
    }
}

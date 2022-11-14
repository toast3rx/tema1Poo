package main.utils;

import fileio.CardInput;
import fileio.GameInput;
import fileio.Input;
import main.cards.Card;
import main.cards.enviroment.Firestorm;
import main.cards.enviroment.HeartHound;
import main.cards.enviroment.Winterfell;
import main.cards.minion.Berserker;
import main.cards.minion.Goliath;
import main.cards.minion.Sentinel;
import main.cards.minion.SpecialMinion.Disciple;
import main.cards.minion.SpecialMinion.Miraj;
import main.cards.minion.SpecialMinion.TheCursedOne;
import main.cards.minion.SpecialMinion.TheRipper;
import main.cards.minion.Warden;
import main.heroes.*;

import java.util.ArrayList;

public class GameUtils {

    public void initGame() {

    }

    /**
     * Return new hero based on its name
     * @param cardInput
     * @return
     */
    public static Hero getHero(CardInput cardInput) {
        switch (cardInput.getName()) {
            case "Lord Royce":
                return new LordRoyce(cardInput.getName(), cardInput.getDescription(), cardInput.getColors(), cardInput.getMana());
            case "Empress Thorina":
                return new EmpressThorina(cardInput.getName(), cardInput.getDescription(), cardInput.getColors(), cardInput.getMana());
            case "King Mudface":
                return new KingMudface(cardInput.getName(), cardInput.getDescription(), cardInput.getColors(), cardInput.getMana());
            case "General Kocioraw":
                return new GeneralKocioraw(cardInput.getName(), cardInput.getDescription(), cardInput.getColors(), cardInput.getMana());
        }

        return null;
    }

    public static Card getCard(CardInput cardInput) {
        switch (cardInput.getName()) {
            case "Sentinel":
                return new Sentinel(cardInput.getName(), cardInput.getMana(), cardInput.getDescription(), cardInput.getColors(), cardInput.getAttackDamage(), cardInput.getHealth());
            case "Berserker":
                return new Berserker(cardInput.getName(), cardInput.getMana(), cardInput.getDescription(), cardInput.getColors(), cardInput.getAttackDamage(), cardInput.getHealth());
            case "Goliath":
                return new Goliath(cardInput.getName(), cardInput.getMana(), cardInput.getDescription(), cardInput.getColors(), cardInput.getAttackDamage(), cardInput.getHealth());
            case "Warden":
                return new Warden(cardInput.getName(), cardInput.getMana(), cardInput.getDescription(), cardInput.getColors(), cardInput.getAttackDamage(), cardInput.getHealth());
            case "The Ripper":
                return new TheRipper(cardInput.getName(), cardInput.getMana(), cardInput.getDescription(), cardInput.getColors(), cardInput.getAttackDamage(), cardInput.getHealth());
            case "Miraj":
                return new Miraj(cardInput.getName(), cardInput.getMana(), cardInput.getDescription(), cardInput.getColors(), cardInput.getAttackDamage(), cardInput.getHealth());
            case "The Cursed One":
                return new TheCursedOne(cardInput.getName(), cardInput.getMana(), cardInput.getDescription(), cardInput.getColors(), cardInput.getAttackDamage(), cardInput.getHealth());
            case "Disciple":
                return new Disciple(cardInput.getName(), cardInput.getMana(), cardInput.getDescription(), cardInput.getColors(), cardInput.getAttackDamage(), cardInput.getHealth());
            case "Firestorm":
                return new Firestorm(cardInput.getName(), cardInput.getMana(), cardInput.getDescription(), cardInput.getColors());
            case "Winterfell":
                return new Winterfell(cardInput.getName(), cardInput.getMana(), cardInput.getDescription(), cardInput.getColors());
            case "Heart Hound":
                return new HeartHound(cardInput.getName(), cardInput.getMana(), cardInput.getDescription(), cardInput.getColors());
        }
        return null;
    }

    public static ArrayList<Card> cardsInputToCards(ArrayList<CardInput> cards) {
        ArrayList<Card> cardsRes = new ArrayList<>();
        for (CardInput card : cards) {
            cardsRes.add(getCard(card));
        }

        return cardsRes;
    }
}

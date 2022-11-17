package main.utils;

import fileio.CardInput;
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
import main.heroes.EmpressThorina;
import main.heroes.GeneralKocioraw;
import main.heroes.Hero;
import main.heroes.KingMudface;
import main.heroes.LordRoyce;

import java.util.ArrayList;

public final class GameUtils {

    private GameUtils() {
    }

    /**
     * Return new hero based on its name
     * @param cardInput The card input
     * @return The hero
     */
    public static Hero getHero(final CardInput cardInput) {
        return switch (cardInput.getName()) {
            case "Lord Royce" ->
                    new LordRoyce(cardInput.getName(),
                            cardInput.getDescription(),
                            cardInput.getColors(),
                            cardInput.getMana());
            case "Empress Thorina" ->
                    new EmpressThorina(cardInput.getName(),
                            cardInput.getDescription(),
                            cardInput.getColors(),
                            cardInput.getMana());
            case "King Mudface" ->
                    new KingMudface(cardInput.getName(),
                            cardInput.getDescription(),
                            cardInput.getColors(),
                            cardInput.getMana());
            case "General Kocioraw" ->
                    new GeneralKocioraw(cardInput.getName(),
                            cardInput.getDescription(),
                            cardInput.getColors(),
                            cardInput.getMana());
            default -> null;
        };

    }

    /**
     * Get card based on its name
     * @param cardInput The card input
     * @return The card
     */
    public static Card getCard(final CardInput cardInput) {
        return switch (cardInput.getName()) {
            case "Sentinel" ->
                    new Sentinel(cardInput.getName(),
                            cardInput.getMana(),
                            cardInput.getDescription(),
                            cardInput.getColors(),
                            cardInput.getAttackDamage(),
                            cardInput.getHealth());
            case "Berserker" ->
                    new Berserker(cardInput.getName(),
                            cardInput.getMana(),
                            cardInput.getDescription(),
                            cardInput.getColors(),
                            cardInput.getAttackDamage(),
                            cardInput.getHealth());
            case "Goliath" ->
                    new Goliath(cardInput.getName(),
                            cardInput.getMana(),
                            cardInput.getDescription(),
                            cardInput.getColors(),
                            cardInput.getAttackDamage(),
                            cardInput.getHealth());
            case "Warden" ->
                    new Warden(cardInput.getName(),
                            cardInput.getMana(),
                            cardInput.getDescription(),
                            cardInput.getColors(),
                            cardInput.getAttackDamage(),
                            cardInput.getHealth());
            case "The Ripper" ->
                    new TheRipper(cardInput.getName(),
                            cardInput.getMana(),
                            cardInput.getDescription(),
                            cardInput.getColors(),
                            cardInput.getAttackDamage(),
                            cardInput.getHealth());
            case "Miraj" ->
                    new Miraj(cardInput.getName(),
                            cardInput.getMana(),
                            cardInput.getDescription(),
                            cardInput.getColors(),
                            cardInput.getAttackDamage(),
                            cardInput.getHealth());
            case "The Cursed One" ->
                    new TheCursedOne(cardInput.getName(),
                            cardInput.getMana(),
                            cardInput.getDescription(),
                            cardInput.getColors(),
                            cardInput.getAttackDamage(),
                            cardInput.getHealth());
            case "Disciple" ->
                    new Disciple(cardInput.getName(),
                            cardInput.getMana(),
                            cardInput.getDescription(),
                            cardInput.getColors(),
                            cardInput.getAttackDamage(),
                            cardInput.getHealth());
            case "Firestorm" ->
                    new Firestorm(cardInput.getName(),
                            cardInput.getMana(),
                            cardInput.getDescription(),
                            cardInput.getColors());
            case "Winterfell" ->
                    new Winterfell(cardInput.getName(),
                            cardInput.getMana(),
                            cardInput.getDescription(),
                            cardInput.getColors());
            case "Heart Hound" ->
                    new HeartHound(cardInput.getName(),
                            cardInput.getMana(),
                            cardInput.getDescription(),
                            cardInput.getColors());
            default -> null;
        };
    }

    /**
     * Convert CardInput to Card
     * @param cards input cards
     * @return converted cards
     */
    public static ArrayList<Card> cardsInputToCards(final ArrayList<CardInput> cards) {
        ArrayList<Card> cardsRes = new ArrayList<>();
        for (CardInput card : cards) {
            cardsRes.add(getCard(card));
        }

        return cardsRes;
    }
}

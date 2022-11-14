package main.cards.minion;

import main.cards.Card;
import main.game.PlayerInfo;

import java.util.ArrayList;

public class Berserker extends Minion implements BackRow {

    public Berserker(String name, int mana, String description, ArrayList<String> colors, int attackDamage, int health) {
        super(name, mana, description, colors, attackDamage, health);
    }


}


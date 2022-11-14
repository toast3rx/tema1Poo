package main.cards.minion;

import java.util.ArrayList;

public class Sentinel extends Minion implements BackRow{
    public Sentinel(String name, int mana, String description, ArrayList<String> colors, int attackDamage, int health) {
        super(name, mana, description, colors, attackDamage, health);
    }
}

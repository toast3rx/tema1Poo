package main.cards.minion;

import java.util.ArrayList;

public class Goliath extends Minion implements Tank, FrontRow{
    public Goliath(String name, int mana, String description, ArrayList<String> colors, int attackDamage, int health) {
        super(name, mana, description, colors, attackDamage, health);
    }
}

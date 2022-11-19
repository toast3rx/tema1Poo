package main.card.minion;

import java.util.ArrayList;

public class Goliath extends Minion implements Tank, FrontRow {
    public Goliath(final String name,
                   final int mana,
                   final String description,
                   final ArrayList<String> colors,
                   final int attackDamage,
                   final int health) {
        super(name, mana, description, colors, attackDamage, health);
    }
}

package main.card.minion;

import java.util.ArrayList;

public class Berserker extends Minion implements BackRow {

    public Berserker(final String name,
                     final int mana,
                     final String description,
                     final ArrayList<String> colors,
                     final int attackDamage,
                     final int health) {
        super(name, mana, description, colors, attackDamage, health);
    }
}

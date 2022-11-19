package main.card.minion;

import java.util.ArrayList;

public class Sentinel extends Minion implements BackRow {
    public Sentinel(final String name,
                    final int mana,
                    final String description,
                    final ArrayList<String> colors,
                    final int attackDamage,
                    final int health) {
        super(name, mana, description, colors, attackDamage, health);
    }
}

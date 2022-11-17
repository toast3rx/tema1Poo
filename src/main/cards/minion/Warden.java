package main.cards.minion;

import java.util.ArrayList;

public class Warden extends Minion implements Tank, FrontRow {
    public Warden(final String name,
                  final int mana,
                  final String description,
                  final ArrayList<String> colors,
                  final int attackDamage,
                  final int health) {
        super(name, mana, description, colors, attackDamage, health);
    }
}

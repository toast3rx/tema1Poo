package main.cards.minion;

import java.util.ArrayList;

public class Warden extends Minion implements Tank, FrontRow{
    public Warden(String name, int mana, String description, ArrayList<String> colors, int attackDamage, int health) {
        super(name, mana, description, colors, attackDamage, health);
    }
}

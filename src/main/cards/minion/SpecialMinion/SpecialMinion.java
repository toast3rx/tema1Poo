package main.cards.minion.SpecialMinion;

import main.cards.minion.Minion;

public abstract class SpecialMinion extends Minion {

    public SpecialMinion(String name, int mana, String description, String colors, int attackDamage, boolean isFrozen, int health) {
        super(name, mana, description, colors, attackDamage, isFrozen, health);
    }

    public abstract void specialPower(Minion targetedMinion);
}

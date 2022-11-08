package main.cards.minion.SpecialMinion;

import main.cards.minion.Minion;

public class Disciple extends SpecialMinion {
    public Disciple(String name, int mana, String description, String colors, int attackDamage, boolean isFrozen, int health) {
        super(name, mana, description, colors, attackDamage, isFrozen, health);
    }

    /**
     * Add +2 health to a friendly minion
     * @param targetedMinion
     */
    @Override
    public void specialPower(Minion targetedMinion) {
        targetedMinion.setHealth(targetedMinion.getHealth() + 2);
    }
}

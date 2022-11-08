package main.cards.minion.SpecialMinion;

import main.cards.minion.Minion;

public class Miraj extends SpecialMinion {


    public Miraj(String name, int mana, String description, String colors, int attackDamage, boolean isFrozen, int health) {
        super(name, mana, description, colors, attackDamage, isFrozen, health);
    }

    /**
     * Swap this card health with the targeted minion
     * @param targetedMinion
     */
    @Override
    public void specialPower(Minion targetedMinion) {
        int minionHealth = targetedMinion.getHealth();
        this.setHealth(targetedMinion.getHealth());
        targetedMinion.setHealth(minionHealth);
    }
}

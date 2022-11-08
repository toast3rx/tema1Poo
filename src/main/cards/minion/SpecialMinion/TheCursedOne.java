package main.cards.minion.SpecialMinion;

import main.cards.minion.Minion;

public class TheCursedOne extends SpecialMinion {
    public TheCursedOne(String name, int mana, String description, String colors, int attackDamage, boolean isFrozen, int health) {
        super(name, mana, description, colors, attackDamage, isFrozen, health);
    }

    /**
     * Swap targeted minion's health with attack damage
     * @param targetedMinion
     */
    @Override
    public void specialPower(Minion targetedMinion) {
        int attack = targetedMinion.getAttackDamage();
        targetedMinion.setAttackDamage(targetedMinion.getHealth());
        targetedMinion.setHealth(attack);
    }
}

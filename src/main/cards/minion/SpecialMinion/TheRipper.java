package main.cards.minion.SpecialMinion;

import main.cards.minion.Minion;

public class TheRipper extends SpecialMinion {
    public TheRipper(String name, int mana, String description, String colors, int attackDamage, boolean isFrozen, int health) {
        super(name, mana, description, colors, attackDamage, isFrozen, health);
    }

    @Override
    public void specialPower(Minion targetedMinion) {
        targetedMinion.setAttackDamage(targetedMinion.getAttackDamage() - 2);
    }
}

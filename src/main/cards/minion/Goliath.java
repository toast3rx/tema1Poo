package main.cards.minion;

public class Goliath extends Minion implements Tank{
    public Goliath(String name, int mana, String description, String colors, int attackDamage, boolean isFrozen, int health) {
        super(name, mana, description, colors, attackDamage, isFrozen, health);
    }
}

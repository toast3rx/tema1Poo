package main.cards.minion;

import lombok.Getter;
import main.cards.Card;

public class Minion extends Card {
    protected int attackDamage;
    protected boolean isFrozen;

    protected int health;

    public Minion(String name, int mana, String description, String colors, int attackDamage, boolean isFrozen, int health) {
        super(name, mana, description, colors);
        this.attackDamage = attackDamage;
        this.isFrozen = isFrozen;
        this.health = health;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public  void placeCard() {

    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }
}

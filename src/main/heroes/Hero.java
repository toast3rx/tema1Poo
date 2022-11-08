package main.heroes;

import main.cards.minion.Minion;

import java.util.ArrayList;

public abstract class Hero {
    protected String name;
    protected String description;
    protected String colors;
    protected int mana;
    protected int health = 30;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Hero(String name, String description, String colors, int mana, int health) {
        this.name = name;
        this.description = description;
        this.colors = colors;
        this.mana = mana;
        this.health = health;
    }

    public abstract void useAbility(ArrayList<Minion> selectedRow);
}

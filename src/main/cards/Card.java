package main.cards;

public abstract class Card {
    protected String name;
    protected int mana;
    protected String description;
    protected String colors;

    public Card(String name, int mana, String description, String colors) {
        this.name = name;
        this.mana = mana;
        this.description = description;
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
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
}

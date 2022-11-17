package main.cards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Card {
    protected int mana;
    protected String name;
    protected String description;
    protected ArrayList<String> colors;

    public Card(final String name,
                final int mana,
                final String description,
                final ArrayList<String> colors) {
        this.name = name;
        this.mana = mana;
        this.description = description;
        this.colors = colors;
    }
}

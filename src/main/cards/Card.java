package main.cards;

import lombok.Data;
import lombok.NoArgsConstructor;
import main.game.Game;
import main.game.PlayerInfo;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public abstract class Card {
    protected int mana;
    protected String name;
    protected String description;
    protected ArrayList<String> colors;

    public Card(String name, int mana, String description, ArrayList<String> colors) {
        this.name = name;
        this.mana = mana;
        this.description = description;
        this.colors = colors;
    }
}


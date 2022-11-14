package main.heroes;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.cards.minion.Minion;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"mana", "description", "colors", "name", "health"})
public abstract class Hero {
    protected String name;
    protected String description;
    protected ArrayList<String> colors;
    protected int mana;
    protected int health;


    public Hero(String name, String description, ArrayList<String> colors, int mana) {
        this.name = name;
        this.description = description;
        this.colors = colors;
        this.mana = mana;
        this.health = 30;
    }

    public abstract void useAbility(ArrayList<Minion> selectedRow);
}

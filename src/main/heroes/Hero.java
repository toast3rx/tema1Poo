package main.heroes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.cards.minion.Minion;
import main.game.Game;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"mana", "description", "colors", "name", "health"})
@JsonIgnoreProperties({"usedTurn"})
public abstract class Hero implements Cloneable{
    protected String name;
    protected String description;
    protected ArrayList<String> colors;
    protected int mana;
    protected int health;
    protected boolean usedTurn = false;


    public Hero(String name, String description, ArrayList<String> colors, int mana) {
        this.name = name;
        this.description = description;
        this.colors = colors;
        this.mana = mana;
        this.health = 30;
    }



    public abstract void useAbility(Game game, int rowIdx) throws Exception;

    @Override
    public Hero clone() {
        try {
            Hero clone = (Hero) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

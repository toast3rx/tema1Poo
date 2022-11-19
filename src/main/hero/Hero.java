package main.hero;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.game.Game;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"mana", "description", "colors", "name", "health"})
@JsonIgnoreProperties({"usedTurn"})
public abstract class Hero implements Cloneable {
    protected String name;
    protected String description;
    protected ArrayList<String> colors;
    protected int mana;
    protected int health;
    protected boolean usedTurn = false;


    public Hero(final String name,
                final String description,
                final ArrayList<String> colors,
                final int mana) {
        this.name = name;
        this.description = description;
        this.colors = colors;
        this.mana = mana;
        this.health = Game.HERO_HEALTH;
    }


    /**
     * Use the hero's special ability.
     * @param game The game containing all the information.
     * @param rowIdx The row to use the ability on.
     * @throws Exception If the ability cannot be used.
     */
    public abstract void useAbility(Game game, int rowIdx) throws Exception;

    /**
     * Clone the hero.
     * @return The cloned hero.
     */
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

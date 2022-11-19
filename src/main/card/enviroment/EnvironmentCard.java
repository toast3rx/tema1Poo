package main.card.enviroment;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import main.card.Card;
import main.game.Game;
import java.util.ArrayList;

@JsonPropertyOrder({"mana", "description", "colors", "name"})
public abstract class EnvironmentCard extends Card {

    public EnvironmentCard(final String name,
                           final int mana,
                           final String description,
                           final ArrayList<String> colors) {
        super(name, mana, description, colors);
    }

    /**
     * Use the ability of the card.
     * @param game The game containing all the information
     * @param row The row card will affect
     * @throws Exception If the card can't be used
     */
    public abstract void useAbility(Game game, int row) throws Exception;
}

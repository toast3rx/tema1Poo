package main.cards.enviroment;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import main.cards.Card;
import main.cards.minion.Minion;
import main.game.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;

@JsonPropertyOrder({"mana", "description", "colors", "name"})
public abstract class EnvironmentCard extends Card {

    public EnvironmentCard(String name, int mana, String description, ArrayList<String> colors) {
        super(name, mana, description, colors);
    }

    public abstract void useAbility(Game game, int row) throws Exception;
}
